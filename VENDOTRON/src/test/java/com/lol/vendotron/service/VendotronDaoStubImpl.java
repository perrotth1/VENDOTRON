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
import java.util.Optional;

/**
 *
 * @author Jeonghoon
 */
public class VendotronDaoStubImpl implements VendotronDao {

    List<Egg> itemList;
    private BigDecimal balance;

    private Egg egg1;
    private Egg egg2;

    public VendotronDaoStubImpl() {
        balance = new BigDecimal("0").setScale(2, RoundingMode.HALF_UP);

        egg1 = new Egg(1, "Egg", "USA", new BigDecimal("1.25"), 1);
        egg2 = new Egg(2, "Black Egg", "Canada", new BigDecimal("2.25"), 0);

        itemList = new ArrayList<>();
        itemList.add(egg1);
        itemList.add(egg2);
    }

    @Override
    public List<Egg> getAllEggs() throws VendotronDaoFileException {
        return itemList;
    }

    @Override
    public Egg getEgg(int _id) throws VendotronDaoFileException {
        Optional<Egg> selected = itemList.stream()
                .filter(egg -> egg.getId() == _id)
                .findFirst();

        if (selected.isEmpty()) {
            return null;
        }
        return selected.get();
    }

    @Override
    public void decrementStock(int _id) throws VendotronDaoFileException {
        Optional<Egg> selected = itemList.stream()
                .filter(egg -> egg.getId() == _id)
                .findFirst();

        if (!selected.isEmpty()) {
            if (selected.get().getStock() > 0) {
                selected.get().decrement();
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
