/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lol.vendotron.service;

import com.lol.vendotron.dao.VendotronAuditDao;
import com.lol.vendotron.dao.VendotronDao;
import com.lol.vendotron.dto.Egg;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Henry
 */
public class VendotronServiceLayerImpl implements VendotronServiceLayer {

    private BigDecimal moneyAmountInMachine;

    private VendotronDao dao;
    private VendotronAuditDao auditDao;

    // for testing.
    public VendotronServiceLayerImpl() {
        moneyAmountInMachine = new BigDecimal("0");
    }

//    public VendotronServiceLayerImpl(VendotronDao dao, VendotronAuditDao auditDao) {
//        userMoney = new BigDecimal("0");
//
//        this.dao = dao;
//        this.auditDao = auditDao;
//    }

    @Override
    public void addMoney(BigDecimal moneyAmount) {
        moneyAmountInMachine = moneyAmountInMachine.add(moneyAmount);

        // for testing
        System.out.println(moneyAmountInMachine);
    }

    @Override
    public void subtractMoney(BigDecimal moneyAmount) {
        moneyAmountInMachine = moneyAmountInMachine.subtract(moneyAmount);

        // for testing
        System.out.println(moneyAmountInMachine);
    }

    @Override
    public void returnChange() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Egg> getAllItems() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Egg giveItemToUser(String title) throws InsufficientFundsException, NoItemInventoryException {
        Egg egg = new Egg("egg", new BigDecimal("1.0"), 1);
        // TODO:
        // Get item from DAO

        // Check if the item has enough stock
        if (egg.getStock() < 1) {
            throw new NoItemInventoryException("There are no " + egg.getName() + ". Please select another item.");
        }

        // Check if the user has put in enough money before purchasing an item.
        if (moneyAmountInMachine.compareTo(egg.getCost()) < 0) {
            throw new NoItemInventoryException("Insufficient fund. Please put more money.");
        }

        return egg;
    }
}
