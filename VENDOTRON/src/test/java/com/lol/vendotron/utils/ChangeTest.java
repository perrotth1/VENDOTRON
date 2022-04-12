/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.lol.vendotron.utils;

import com.lol.vendotron.utils.CoinType;
import static com.lol.vendotron.utils.Change.getCoins;
import java.util.Map;
import java.util.TreeMap;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Jeonghoon
 */
public class ChangeTest {

    public ChangeTest() {
    }

    /*  Test Plan:
    **  getCoins(91) → {QUARTER=3, DIME=1, NICKEL=1, PENNY=1}
    **  getCoins(131)→ {QUARTER=5, NICKEL=1, PENNY=1}
    **  getCoins(7)  → {NICKEL=1, PENNY=2}
    **  getCoins(23) → {DIME=2, PENNY=3}
    **  getCoins(0)  → { }
     */
    @Test
    public void test91Cents() {
        // Arrange
        Map<CoinType, Integer> coins = new TreeMap<>();
        coins.put(CoinType.QUARTER, 3);
        coins.put(CoinType.DIME, 1);
        coins.put(CoinType.NICKEL, 1);
        coins.put(CoinType.PENNY, 1);

        // Act & Assert
        assertEquals(coins, getCoins(91));
    }

    @Test
    public void test131Cents() {
        // Arrange
        Map<CoinType, Integer> coins = new TreeMap<>();
        coins.put(CoinType.QUARTER, 5);
        coins.put(CoinType.NICKEL, 1);
        coins.put(CoinType.PENNY, 1);

        // Act & Assert
        assertEquals(coins, getCoins(131));
    }

    // alternative, simple way
    @Test
    public void test7Cents() {
        // Arrange & Act & Assert
        assertEquals("{NICKEL=1, PENNY=2}", getCoins(7).toString());
    }

    @Test
    public void test23Cents() {
        // Arrange & Act & Assert
        assertEquals("{DIME=2, PENNY=3}", getCoins(23).toString());
    }

    @Test
    public void test0Cents() {
        // Arrange & Act & Assert
        assertEquals("{}", getCoins(0).toString());
    }
}
