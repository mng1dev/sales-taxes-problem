package com.vektor.salestaxes.receipt.writer;

import com.vektor.salestaxes.receipt.model.Receipt;

import java.math.BigDecimal;

public class StringReceiptWriter implements ReceiptWriter{

    @Override
    public String write(Receipt receipt) {
        StringBuilder sb = new StringBuilder();
        receipt.getPurchases().forEach(receiptItem -> {
            sb.append(receiptItem.getQuantity());
            sb.append(" ");
            sb.append(receiptItem.getName());
            sb.append(": ");
            sb.append(receiptItem.getPriceAfterTaxes().setScale(2, BigDecimal.ROUND_UP));
            sb.append("\n");
        });
        sb.append("Sales Taxes: ");
        sb.append(receipt.getSalesTaxes().setScale(2, BigDecimal.ROUND_UP));
        sb.append("\n");
        sb.append("Total: ");
        sb.append(receipt.getTotal().setScale(2, BigDecimal.ROUND_UP));
        return sb.toString();
    }
}
