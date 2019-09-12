package com.vektor.salestaxes.receipt.model;

import com.vektor.salestaxes.purchase.model.PurchaseItem;

import java.math.BigDecimal;

public class ReceiptItem {

    private Integer quantity;
    private String name;
    private BigDecimal priceAfterTaxes;
    private boolean imported;

    public ReceiptItem(PurchaseItem purchaseItem) {
        this(purchaseItem, BigDecimal.ZERO);
    }

    public ReceiptItem(PurchaseItem purchaseItem, BigDecimal salesTaxes){
        this.quantity = purchaseItem.getQuantity();
        this.name = purchaseItem.getName();
        this.priceAfterTaxes = purchaseItem.getPrice().add(salesTaxes);
        this.imported = purchaseItem.isImported();
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return (isImported()
                ? "imported "
                : "") + name;
    }

    public BigDecimal getPriceAfterTaxes() {
        return priceAfterTaxes.multiply(new BigDecimal(quantity));
    }

    public boolean isImported() {
        return imported;
    }
}