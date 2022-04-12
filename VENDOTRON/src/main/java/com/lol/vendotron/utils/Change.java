
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lol.vendotron.utils;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Jeonghoon
 */
public class Change {

    public static Map<CoinType, Integer> getCoins(int amount) {
        Map<CoinType, Integer> changes = new TreeMap<>();

        int quotient;
        int remainder = amount;

        for (CoinType coin : CoinType.values()) {
            if (remainder == 0) {
                break;
            }
            if (coin != CoinType.PENNY) {
                quotient = remainder / coin.getValue();
                if (quotient > 0) {
                    changes.put(coin, quotient);
                }
                // calculate new remainder
                remainder -= quotient * coin.getValue();
            } else {
                changes.put(coin, remainder);
            }
        }
        return changes;
    }
}
