
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.lol.vendotron.utils;

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
