/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lol.vendotron.service;

import com.lol.vendotron.dao.VendotronDaoFileException;
import com.lol.vendotron.dto.Egg;
import com.lol.vendotron.utils.CoinType;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Henry
 */
public interface VendotronServiceLayer {

    void addMoney(BigDecimal moneyAmount);

    BigDecimal getCurrentBalance();

    Map<CoinType, Integer> returnChanges();

    List<Egg> getAllItems() throws VendotronDaoFileException;

    Egg giveItemToUser(int itemId) throws
            InsufficientFundsException,
            NoItemInventoryException,
            VendotronDaoFileException;
}
