package ru.bugmakers.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Ivan
 */
public class BigDecimalUtils {

    private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    /**
     * Сумма за вычетом комиссии
     *
     * @param sum сумма
     * @param fee комиссия в %
     * @return сумма за вычетом комиссии
     */
    public static BigDecimal withoutFee(BigDecimal sum, BigDecimal fee) {
        return sum.subtract(fee(sum, fee)).setScale(2, RoundingMode.DOWN);
    }

    /**
     * Вычисление комиссии в рублях
     *
     * @param sum сумма
     * @param fee комиссия в %
     * @return комиссия в рублях
     */
    public static BigDecimal fee(BigDecimal sum, BigDecimal fee) {
        return sum.multiply(fee).divide(ONE_HUNDRED, 2, RoundingMode.DOWN);
    }

}
