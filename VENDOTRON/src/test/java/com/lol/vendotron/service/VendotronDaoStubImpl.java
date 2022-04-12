/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lol.vendotron.service;

import com.lol.vendotron.dao.VendotronDao;
import com.lol.vendotron.dao.VendotronDaoFileException;
import com.lol.vendotron.dto.Egg;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jeonghoon
 */
public class VendotronDaoStubImpl implements VendotronDao {

    private Egg onlyOneEgg;
    private BigDecimal balance;

    public VendotronDaoStubImpl() {
        balance = new BigDecimal("0").setScale(2, RoundingMode.HALF_UP);
        onlyOneEgg = new Egg(
                1, // id
                "Egg", // name
                "Mixed Origin", // origin
                new BigDecimal("1.25"), // cost
                1);                     // stock
    }

    @Override
    public List<Egg> getAllEggs() throws VendotronDaoFileException {
        List<Egg> eggList = new ArrayList<>();
        eggList.add(onlyOneEgg);
        return eggList;
    }

    @Override
    public Egg getEgg(int _id) throws VendotronDaoFileException {
        if (onlyOneEgg.getId() == _id) {
            return onlyOneEgg;
        } else {
            return null;
        }
    }

    @Override
    public void decrementStock(int _id) throws VendotronDaoFileException {
        if (onlyOneEgg.getId() == _id) {
            if (onlyOneEgg.getStock() > 0) {
                onlyOneEgg.decrement();
            }
        }
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public BigDecimal addToBalance(BigDecimal _amount) {
        balance = this.balance.add(_amount);
        return this.balance;
    }

    @Override
    public void clearBalance() {
        balance = new BigDecimal("0").setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal subtractBalance(BigDecimal _amount) {
        // not implemented
        return balance;
    }

}
