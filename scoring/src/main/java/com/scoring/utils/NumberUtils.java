package com.scoring.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class NumberUtils {

    private static DecimalFormat decimalFormat = new DecimalFormat("#0.0");

    public static String formatWithPrecisionOne(BigDecimal num){
        return decimalFormat.format(num);
    }
    public static String formatWithPrecisionOne(Double num){
        return decimalFormat.format(num);
    }

    public static String formatWithSpaceSearator(Long num){
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.FRANCE);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();

        symbols.setGroupingSeparator(' ');
        formatter.setDecimalFormatSymbols(symbols);
        return formatter.format(num);
    }
}
