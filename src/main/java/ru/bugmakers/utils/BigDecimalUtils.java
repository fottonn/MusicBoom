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
     * @param fee наша комиссия в %
     * @return сумма за вычетом комиссии
     */
    public static BigDecimal withoutFee(BigDecimal sum, BigDecimal fee) {
        return sum.subtract(fee(sum, fee)).setScale(2, RoundingMode.DOWN);
    }

    /**
     * Вычисление комиссии в рублях
     *
     * @param sum сумма
     * @param fee наша комиссия в %
     * @return комиссия в рублях
     */
    public static BigDecimal fee(BigDecimal sum, BigDecimal fee) {
        return sum.multiply(fee).divide(ONE_HUNDRED, 2, RoundingMode.UP);
    }

    /**
     * Наш доход от одной транзакции
     *
     * @param sum              сумма транзакции
     * @param fee              наша комиссия в рублях
     * @param paymentSystemFee комиссия платежной системы в %
     * @return чистый доход (без комиссии платежной системы)
     */
    public static BigDecimal profit(BigDecimal sum, BigDecimal fee, BigDecimal paymentSystemFee) {
        return fee.subtract(sum.multiply(paymentSystemFee).divide(ONE_HUNDRED, 2, RoundingMode.DOWN));
    }

    /**
     * Бонус по реферальной программе
     *
     * @param amount сумма заработка реферала
     * @param bonus бонус от заработка реферала в %
     * @return бонус от заработка реферала в рублях
     */
    public static BigDecimal referrerBonus(BigDecimal amount, BigDecimal bonus) {
        return amount.multiply(bonus).divide(ONE_HUNDRED, 2, RoundingMode.DOWN);
    }
}
