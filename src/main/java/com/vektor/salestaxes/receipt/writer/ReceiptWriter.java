package com.vektor.salestaxes.receipt.writer;

import com.vektor.salestaxes.receipt.model.Receipt;

public interface ReceiptWriter {
    public String write(Receipt receipt);
}
