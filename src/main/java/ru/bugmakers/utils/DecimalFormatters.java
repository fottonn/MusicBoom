package ru.bugmakers.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Created by Ivan
 */
public class DecimalFormatters {

    /**
     * Денежный формат ###.##
     *
     * 12.00
     * 12.30
     * 12.03
     */
    public static final DecimalFormat MONEY_FORMATTER;

    static {
        MONEY_FORMATTER = new DecimalFormat();
        MONEY_FORMATTER.setMaximumFractionDigits(2);
        MONEY_FORMATTER.setMinimumFractionDigits(2);
        MONEY_FORMATTER.setRoundingMode(RoundingMode.DOWN);
        MONEY_FORMATTER.setGroupingUsed(false);
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        MONEY_FORMATTER.setDecimalFormatSymbols(dfs);
    }

    /**
     * Формат времени в часах ###.#
     *
     * 5
     * 5.3
     */
    public static final DecimalFormat HOURS_FORMATTER;

    static {
        HOURS_FORMATTER = new DecimalFormat();
        HOURS_FORMATTER.setMaximumFractionDigits(1);
        HOURS_FORMATTER.setGroupingUsed(false);
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        HOURS_FORMATTER.setDecimalFormatSymbols(dfs);
    }

}
