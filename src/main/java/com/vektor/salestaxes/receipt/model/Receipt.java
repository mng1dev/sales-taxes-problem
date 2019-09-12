package com.vektor.salestaxes.receipt.model;

import com.vektor.salestaxes.purchase.model.PurchaseItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private BigDecimal total = BigDecimal.ZERO;
    private BigDecimal salesTaxes = BigDecimal.ZERO;
    private List<ReceiptItem> purchases;

    public Receipt() {
        purchases = new ArrayList<>();
    }

    public void addPurchase(PurchaseItem purchaseItem, BigDecimal salesTaxes) {
        ReceiptItem receiptItem = new ReceiptItem(purchaseItem, salesTaxes);
        purchases.add(receiptItem);

        this.total = this.total.add(receiptItem.getPriceAfterTaxes());
        this.salesTaxes = this.salesTaxes.add(
                salesTaxes.multiply(new BigDecimal(purchaseItem.getQuantity()))
        );
    }

    public BigDecimal getTotal() {
        return total;
    }

    public BigDecimal getSalesTaxes() {
        return salesTaxes;
    }

    public List<ReceiptItem> getPurchases() {
        return purchases;
    }

    public int size() {
        return purchases.size();
    }
}
