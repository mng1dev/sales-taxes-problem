package com.vektor.salestaxes.tax;

import org.junit.Test;
import static org.junit.Assert.*;

import java.math.BigDecimal;

public class TestTaxCalculator {

    @Test
    public void TestTaxCalculatorZero() {
        BigDecimal salesTaxes = TaxCalculator.calculateSalesTaxes(new BigDecimal("10.00"), 0);
        assertEquals(salesTaxes.doubleValue(), 0.00, 0.000001);
    }

    @Test
    public void TestTaxCalculatorImportTax() {
        BigDecimal salesTaxes = TaxCalculator.calculateSalesTaxes(new BigDecimal("11.25"), 5);
        assertEquals(salesTaxes.doubleValue(), 0.6, 0.000001);
    }

    @Test
    public void TestTaxCalculatorBasicTaxes() {
        BigDecimal salesTaxes = TaxCalculator.calculateSalesTaxes(new BigDecimal("14.99"), 10);
        assertEquals(salesTaxes.doubleValue(), 1.5, 0.000001);
    }
}
