package com.vektor.salestaxes.category.parser;

import com.vektor.salestaxes.exceptions.InvalidInputException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

import java.util.Map;

public class TestCSVCategoryParser {


    @Test
    public void TestCSVCategoryParserParseNonExistingFile() {
        String path = "nonexistingfile";

        String expectedMessage = "Invalid input: File not found - nonexistingfile.";

        CSVCategoryParser parser = new CSVCategoryParser();
        try {
            parser.parse(path);
        } catch (InvalidInputException iie) {
            assertEquals(expectedMessage, iie.getMessage());
        }
    }

    @Test
    public void TestCSVCategoryParserParseEmptyFile() {
        String path = "src/test/resources/categories_empty.csv";

        String expectedMessage = "Invalid input: File is empty.";

        CSVCategoryParser parser = new CSVCategoryParser();
        try {
            parser.parse(path);
        } catch (InvalidInputException iie) {
            assertEquals(expectedMessage, iie.getMessage());
        }
    }

    @Test
    public void TestCSVCategoryParserParseSkipHeader() {
        String path = "src/test/resources/categories.csv";

        CSVCategoryParser parser = new CSVCategoryParser();
        Map<String,String> categories = parser.parse(path);

        assertEquals(6, categories.size());
    }

    @Test
    public void TestCSVCategoryParserParseMalformed() {
        String path = "src/test/resources/categories_malformed.csv";

        String expectedMessage = "Invalid input: Malformed row.";

        CSVCategoryParser parser = new CSVCategoryParser();
        try {
            parser.parse(path);
        } catch (InvalidInputException iie) {
            assertEquals(expectedMessage, iie.getMessage());
        }
    }

    @Test
    public void TestCSVCategoryParserParse() {
        String path = "src/test/resources/categories_noheader.csv";

        CSVCategoryParser parser = new CSVCategoryParser();
        Map<String,String> categories = parser.parse(path);

        assertEquals(6, categories.size());
    }

}
