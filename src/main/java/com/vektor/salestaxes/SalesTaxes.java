package com.vektor.salestaxes;

import com.vektor.salestaxes.category.parser.CSVCategoryParser;
import com.vektor.salestaxes.purchase.model.PurchaseItem;
import com.vektor.salestaxes.purchase.model.PurchaseList;
import com.vektor.salestaxes.purchase.parser.TextFilePurchaseParser;
import com.vektor.salestaxes.receipt.model.Receipt;
import com.vektor.salestaxes.tax.TaxCalculator;

import java.math.BigDecimal;
import java.util.*;

public class SalesTaxes {

    private static final Integer BASIC_SALES_TAX = 10;
    private static final Integer IMPORT_SALES_TAX = 5;

    private Map<String, String> categories;
    private PurchaseList purchaseList;

    private TreeSet<String> excludedCategories;

    public void loadExcludedCategories(String [] excludedCategories) {
        this.excludedCategories = new TreeSet<>(Arrays.asList(excludedCategories));
    }

    public void loadCategories(String filePath) {
        categories = new CSVCategoryParser().parse(filePath);
    }

    public Map<String, String> getCategories() {
        return categories;
    }

    public void loadPurchaseList(String filePath) {
        purchaseList = new TextFilePurchaseParser().parse(filePath);
    }

    public PurchaseList getPurchaseList() {
        return purchaseList;
    }

    public Receipt generateReceipt() {
        Receipt receipt = new Receipt();

        if (purchaseList != null && categories != null) {
            purchaseList.getPurchases().forEach(purchaseItem -> {

                Integer taxPercentage = getTaxIncrement(purchaseItem);
                BigDecimal salesTaxes = TaxCalculator.calculateSalesTaxes(
                        purchaseItem.getPrice(),
                        taxPercentage
                );
                receipt.addPurchase(purchaseItem, salesTaxes);

            });
        }

        return receipt;
    }

    private Integer getTaxIncrement(PurchaseItem purchaseItem) {
        boolean isExcludedCategory =
                categories.containsKey(purchaseItem.getName())
                && excludedCategories.contains(categories.get(purchaseItem.getName()));
        return (isExcludedCategory ? 0 : BASIC_SALES_TAX) + (purchaseItem.isImported() ? IMPORT_SALES_TAX : 0);
    }

}
