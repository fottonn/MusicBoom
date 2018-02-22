package ru.bugmakers.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Created by Ivan
 */
public class DecimalFormatters {

    public static final DecimalFormat MONEY_FORMATTER = getMoneyFormatter();
    public static final DecimalFormat HOURS_FORMATTER = getHoursFormatter();

    private static DecimalFormat getMoneyFormatter() {
        if (MONEY_FORMATTER != null) return MONEY_FORMATTER;
        DecimalFormat moneyFormatter = new DecimalFormat();
        moneyFormatter.setMaximumFractionDigits(2);
        moneyFormatter.setMinimumFractionDigits(2);
        moneyFormatter.setRoundingMode(RoundingMode.DOWN);
        moneyFormatter.setGroupingUsed(false);
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        moneyFormatter.setDecimalFormatSymbols(dfs);
        return moneyFormatter;
    }

    private static DecimalFormat getHoursFormatter() {
        if (HOURS_FORMATTER != null) return HOURS_FORMATTER;
        DecimalFormat hoursFormatter = new DecimalFormat();
        hoursFormatter.setMaximumFractionDigits(1);
        hoursFormatter.setGroupingUsed(false);
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        hoursFormatter.setDecimalFormatSymbols(dfs);
        return hoursFormatter;
    }







}
