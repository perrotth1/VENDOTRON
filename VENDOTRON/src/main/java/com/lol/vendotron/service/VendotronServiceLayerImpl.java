/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lol.vendotron.service;

import com.lol.vendotron.dao.VendotronAuditDao;
import com.lol.vendotron.dao.VendotronDao;
import com.lol.vendotron.dao.VendotronDaoFileException;
import com.lol.vendotron.dto.Egg;
import com.lol.vendotron.utils.Change;
import com.lol.vendotron.utils.CoinType;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Henry
 */
public class VendotronServiceLayerImpl implements VendotronServiceLayer {

    private BigDecimal moneyAmountInMachine;

    private VendotronDao dao;
    private VendotronAuditDao auditDao;

    public VendotronServiceLayerImpl(VendotronDao dao, VendotronAuditDao auditDao) {
        moneyAmountInMachine = new BigDecimal("0");

        this.dao = dao;
        this.auditDao = auditDao;
    }

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
    public List<Egg> getAllItems() throws VendotronDaoFileException {
        return dao.getAllEggs();
    }

    @Override
    public Egg giveItemToUser(int itemId) throws InsufficientFundsException,
            NoItemInventoryException,
            VendotronDaoFileException {
//        Egg egg = new Egg(1, "egg", "test", new BigDecimal("1.0"), 1);

        // TODO: error handling
        Egg egg = dao.getEgg(itemId);

        // Check if the item has enough stock
        if (egg.getStock() < 1) {
            throw new NoItemInventoryException("There are no " + egg.getName() + ". Please select another item.");
        }
        // Check if the user has put in enough money before purchasing an item.
        if (moneyAmountInMachine.compareTo(egg.getCost()) < 0) {
            throw new NoItemInventoryException("Insufficient fund. Please put more money.");
        }

        // Reduce stock with DAO
        dao.decrementStock(egg.getId());
        // update audit file
        String log = egg.getName() + " was sold.";
        auditDao.writeAuditEntry(log);

        // update audit file
        log = "Original amount of money: " + moneyAmountInMachine.setScale(2).toString();
        auditDao.writeAuditEntry(log);

        // Reduce money amount
        moneyAmountInMachine = moneyAmountInMachine.subtract(egg.getCost());

        // update audit file after transaction.
        log += "After purchasing: " + moneyAmountInMachine.toString();
        auditDao.writeAuditEntry(log);

        return egg;
    }

    @Override
    public Map<CoinType, Integer> returnChanges() {
        // convert dollar amount to cents.
        Map<CoinType, Integer> changes
                = Change.getCoins(moneyAmountInMachine.multiply(new BigDecimal("100")).intValue());

        return changes;
    }
}
