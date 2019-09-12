package com.vektor.salestaxes.purchase.parser;

import com.vektor.salestaxes.exceptions.InvalidInputException;
import com.vektor.salestaxes.purchase.model.PurchaseList;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class TestTextFilePurchaseParser {

    @Test
    public void TestTextFilePurchaseParserParseNonExistingFile() {
        String path = "nonexistingfile";

        String expectedMessage = "Invalid input: File not found - nonexistingfile.";


        TextFilePurchaseParser parser = new TextFilePurchaseParser();
        try {
            parser.parse(path);
        } catch (InvalidInputException iie) {
            assertEquals(expectedMessage, iie.getMessage());
        }
    }

    @Test
    public void TestTextFilePurchaseParserParseEmptyFile() {
        String path = "src/test/resources/purchase_empty.txt";

        String expectedMessage = "Invalid input: File is empty.";

        TextFilePurchaseParser parser = new TextFilePurchaseParser();
        try {
            parser.parse(path);
        } catch (InvalidInputException iie) {
            assertEquals(expectedMessage, iie.getMessage());
        }
    }

    @Test
    public void TestTextFilePurchaseParserParseFileMalformed() {
        String path = "src/test/resources/purchase_malformed.txt";

        String expectedMessage = "Invalid input: Malformed row.";

        TextFilePurchaseParser parser = new TextFilePurchaseParser();
        try {
            parser.parse(path);
        } catch (InvalidInputException iie) {
            assertEquals(expectedMessage, iie.getMessage());
        }
    }

    @Test
    public void TestTextFilePurchaseParserParseFile() {
        String path = "src/test/resources/purchase_1.txt";

        TextFilePurchaseParser parser = new TextFilePurchaseParser();
        PurchaseList purchaseList = parser.parse(path);

        assertEquals(3, purchaseList.size());

        assertEquals(1, purchaseList.getPurchases().get(1).getQuantity());
        assertEquals("music CD", purchaseList.getPurchases().get(1).getName());
        assertEquals(14.99, purchaseList.getPurchases().get(1).getPrice().doubleValue(), 0.00001);
    }

    @Test
    public void TestTextFilePurchaseParserParseFileHasImportedGoods() {
        String path = "src/test/resources/purchase_2.txt";

        TextFilePurchaseParser parser = new TextFilePurchaseParser();
        PurchaseList purchaseList = parser.parse(path);

        assertEquals(2, purchaseList.size());

        assertEquals("box of chocolates", purchaseList.getPurchases().get(0).getName());
        assertTrue(purchaseList.getPurchases().get(0).isImported());
    }

    @Test
    public void TestTextFilePurchaseParserParseFileHasImportedGoodsUnstructuredLine() {
        String path = "src/test/resources/purchase_3.txt";

        TextFilePurchaseParser parser = new TextFilePurchaseParser();
        PurchaseList purchaseList = parser.parse(path);

        assertEquals(4, purchaseList.size());
        assertEquals("bottle of perfume", purchaseList.getPurchases().get(0).getName());
        assertTrue(purchaseList.getPurchases().get(0).isImported());
        assertEquals("box of chocolates", purchaseList.getPurchases().get(3).getName());
        assertTrue(purchaseList.getPurchases().get(3).isImported());
    }

}
