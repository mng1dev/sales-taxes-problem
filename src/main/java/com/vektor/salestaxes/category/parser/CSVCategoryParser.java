package com.vektor.salestaxes.category.parser;

import com.vektor.salestaxes.exceptions.InvalidInputException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class CSVCategoryParser implements CategoryParser {

    private static final Pattern HEADER = Pattern.compile("^\\s*item\\s*,\\s*category\\s*$");

    @Override
    public Map<String, String> parse(String filePath) {
        TreeMap<String, String> categories = new TreeMap<>();

        try (Stream<String> inputLines = Files.lines(Paths.get(filePath))) {

            inputLines.forEach(line -> {
                if (HEADER.matcher(line).find() || line.isEmpty())
                    return;

                String[] row = line.split(",");

                if (row.length != 2)
                    throw new InvalidInputException("Malformed row");

                String item = row[0].trim();
                String category = row[1].trim();

                if (validateString(item) && validateString(category))
                    categories.put(row[0], row[1]);

            });

        } catch (IOException e) {
            String errorMessage = String.format("File not found - %s", filePath);
            throw new InvalidInputException(errorMessage);
        }

        if (categories.size() == 0)
            throw new InvalidInputException("File is empty");

        return Collections.unmodifiableMap(categories);
    }

    private boolean validateString(String toValidate) {
        return toValidate != null && !toValidate.isEmpty();
    }

}
