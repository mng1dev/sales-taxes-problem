package com.vektor.salestaxes.purchase.parser;

import com.vektor.salestaxes.purchase.model.PurchaseList;

public interface PurchaseParser {
    public PurchaseList parse(String filePath);
}
