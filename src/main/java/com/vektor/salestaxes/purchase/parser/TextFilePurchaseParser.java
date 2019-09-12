package com.vektor.salestaxes.purchase.parser;

import com.vektor.salestaxes.exceptions.InvalidInputException;
import com.vektor.salestaxes.purchase.model.PurchaseItem;
import com.vektor.salestaxes.purchase.model.PurchaseList;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class TextFilePurchaseParser implements PurchaseParser {

    private final static Pattern PURCHASE_LINE_PATTERN = Pattern.compile("(\\d+)\\s+(.+)\\s+at\\s+(\\d+(\\.\\d+)?)");
    private final static String IMPORTED = "imported";

    @Override
    public PurchaseList parse(String filePath) {
        PurchaseList purchaseList = new PurchaseList();

        try (Stream<String> inputLines = Files.lines(Paths.get(filePath))) {

            inputLines.forEach(line -> {
                Matcher matcher = PURCHASE_LINE_PATTERN.matcher(line);
                if (matcher.find()) {

                    Integer quantity = Integer.valueOf(matcher.group(1));
                    String name = matcher.group(2);
                    BigDecimal price = new BigDecimal(matcher.group(3));
                    boolean imported = isImported(name);

                    name = imported
                            ? cleanProductName(name)
                            : name;

                    PurchaseItem purchase = new PurchaseItem(quantity, name, price, imported);

                    purchaseList.addPurchase(purchase);
                } else
                    throw new InvalidInputException("Malformed row");

            });

        } catch (IOException e) {
            String errorMessage = String.format("File not found - %s", filePath);
            throw new InvalidInputException(errorMessage);
        }

        if (purchaseList.size() == 0)
            throw new InvalidInputException("File is empty");

        return purchaseList;
    }

    private boolean isImported(String name) {
        return name.contains(IMPORTED);
    }

    private String cleanProductName(String productName) {
        return productName.replaceAll("\\s*imported\\s*", " ").trim();
    }
}
