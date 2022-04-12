package com.lol.vendotron.dto;

/**
 *
 * @author Jeonghoon
 */
public enum CoinType {
    QUARTER(25),
    DIME(10),
    NICKEL(5),
    PENNY(1);

    private final int value;

    private CoinType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
