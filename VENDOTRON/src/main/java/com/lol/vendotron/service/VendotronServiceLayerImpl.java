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

    private VendotronDao dao;
    private VendotronAuditDao auditDao;

    public VendotronServiceLayerImpl(VendotronDao dao, VendotronAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public void addMoney(BigDecimal moneyAmount) {
        dao.addToBalance(moneyAmount);
        // for testing
        System.out.println(dao.getBalance());
    }

    @Override
    public List<Egg> getAllItems() throws VendotronDaoFileException {
        return dao.getAllEggs();
    }

    @Override
    public Egg giveItemToUser(int itemId) throws InsufficientFundsException,
            NoItemInventoryException,
            VendotronDaoFileException {
        // TODO: error handling
        Egg egg = dao.getEgg(itemId);

        // Check if the item has enough stock
        if (egg.getStock() < 1) {
            throw new NoItemInventoryException("There are no " + egg.getName() + ". Please select another item.");
        }
        // Check if the user has put in enough money before purchasing an item.
        if (dao.getBalance().compareTo(egg.getCost()) < 0) {
            throw new NoItemInventoryException("Insufficient fund. Please put more money.");
        }

        // Reduce stock with DAO
        dao.decrementStock(egg.getId());
        // update audit file
        String log = egg.getName() + " was sold.";
        auditDao.writeAuditEntry(log);

        // update audit file
        log = "Original amount of money: " + dao.getBalance().toString();
        auditDao.writeAuditEntry(log);

        // Reduce money amount
        dao.subtractBalance(egg.getCost());

        // update audit file after transaction.
        log = "After purchasing: " + dao.getBalance().toString();
        auditDao.writeAuditEntry(log);
        
        //Roll if egg shatters when it is dispensed
        egg.rollIfBreaks();

        return egg;
    }

    @Override
    public Map<CoinType, Integer> returnChanges() {
        // convert dollar amount to cents.
        int currentBalanceAsCents = dao.getBalance()
                .multiply(new BigDecimal("100"))
                .intValue();

        Map<CoinType, Integer> changes = Change.getCoins(currentBalanceAsCents);

        // clear balance
        dao.clearBalance();

        return changes;
    }

    @Override
    public BigDecimal getCurrentBalance() {
        return dao.getBalance();
    }
}
