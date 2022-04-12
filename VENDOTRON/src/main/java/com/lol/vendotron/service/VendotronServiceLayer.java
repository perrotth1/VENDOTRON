/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lol.vendotron.service;

import com.lol.vendotron.dto.Egg;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Henry
 */
public interface VendotronServiceLayer {

    void addMoney(BigDecimal moneyAmount);

    void subtractMoney(BigDecimal moneyAmount);

    void returnChange();

    List<Egg> getAllItems();

    Egg giveItemToUser(String title) throws InsufficientFundsException, NoItemInventoryException;
}
