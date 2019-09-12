package com.vektor.salestaxes.tax;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaxCalculator {

    private final static BigDecimal HUNDRED = new BigDecimal("100");
    private final static BigDecimal ROUNDING = new BigDecimal("0.05");

    public static BigDecimal calculateSalesTaxes(BigDecimal price, int taxesPercentage) {
        return roundUp(
                price.multiply(new BigDecimal(taxesPercentage)).divide(HUNDRED)
        );
    }

    private static BigDecimal roundUp(BigDecimal value) {
        return value.divide(ROUNDING, 0, RoundingMode.UP).multiply(ROUNDING);
    }
}
