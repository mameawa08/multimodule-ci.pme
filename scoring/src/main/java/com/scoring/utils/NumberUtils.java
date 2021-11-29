package com.scoring.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberUtils {

    private static DecimalFormat decimalFormat = new DecimalFormat("#0.0");

    public static String formatWithPrecisionOne(BigDecimal num){
        return decimalFormat.format(num);
    }
}
