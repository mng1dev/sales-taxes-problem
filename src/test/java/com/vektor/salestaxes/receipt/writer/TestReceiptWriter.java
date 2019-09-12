package com.vektor.salestaxes.receipt.writer;

import com.vektor.salestaxes.purchase.model.PurchaseItem;
import com.vektor.salestaxes.receipt.model.Receipt;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class TestReceiptWriter {

    @Test
    public void TestReceiptWriterEmptyReceipt() {
        Receipt receipt = new Receipt();

        String writtenReceipt = new StringReceiptWriter().write(receipt);

        String expectedReceipt = "Sales Taxes: 0.00\n" +
                "Total: 0.00";

        assertEquals(expectedReceipt, writtenReceipt);
    }

    @Test
    public void TestReceiptWriterReceipt() {
        Receipt receipt = new Receipt();

        receipt.addPurchase(
                new PurchaseItem(3, "packet of headache pills", new BigDecimal("9.75"), false),
                new BigDecimal("0.6")
        );

        String writtenReceipt = new StringReceiptWriter().write(receipt);

        String expectedReceipt = "3 packet of headache pills: 31.05\n" +
                "Sales Taxes: 1.80\n" +
                "Total: 31.05";

        assertEquals(expectedReceipt, writtenReceipt);
    }
}
