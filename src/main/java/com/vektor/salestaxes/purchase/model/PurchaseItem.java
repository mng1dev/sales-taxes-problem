package com.vektor.salestaxes.purchase.model;

import java.math.BigDecimal;

public class PurchaseItem {

    private Integer quantity;
    private String name;
    private BigDecimal price;
    private boolean imported;

    public PurchaseItem(Integer quantity, String name, BigDecimal price, boolean imported){
        this.quantity = quantity;
        this.name = name;
        this.price = price;
        this.imported = imported;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isImported() {
        return imported;
    }
}
