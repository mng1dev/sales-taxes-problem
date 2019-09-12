package com.vektor.salestaxes.purchase.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PurchaseList {

    private List<PurchaseItem> purchases;

    public PurchaseList() {
        purchases = new ArrayList<PurchaseItem>();
    }

    public List<PurchaseItem> getPurchases() {
        return Collections.unmodifiableList(purchases);
    }

    public void addPurchase(PurchaseItem purchase) {
        purchases.add(purchase);
    }

    public int size() {
        return purchases.size();
    }
}
