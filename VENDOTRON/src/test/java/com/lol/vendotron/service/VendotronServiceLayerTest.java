/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.lol.vendotron.service;

import com.lol.vendotron.dao.VendotronAuditDao;
import com.lol.vendotron.dao.VendotronDao;
import com.lol.vendotron.dao.VendotronDaoFileException;
import com.lol.vendotron.dto.Egg;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Jeonghoon
 */
public class VendotronServiceLayerTest {

    private VendotronServiceLayer service;

    public VendotronServiceLayerTest() {
        VendotronDao dao = new VendotronDaoStubImpl();
        VendotronAuditDao auditDao = new VendotronAuditDaoStubImpl();

        service = new VendotronServiceLayerImpl(dao, auditDao);
    }

    @Test
    public void testGetAllItems() throws VendotronDaoFileException {
        final int EXPECTED_SIZE = 2;
        // ACT & ASSERT
        assertEquals(EXPECTED_SIZE, service.getAllItems().size(),
                "Should only have one item.");
    }

    @Test
    public void testGiveItemToUserWithNotEnoughBalance() {
        final int SELECTED_ITEM_ID = 1;
        // select item in the itemList without enough money
        try {
            service.giveItemToUser(SELECTED_ITEM_ID);
            fail("Expected ValidationException was not thrown.");
        } catch (NoItemInventoryException | VendotronDaoFileException ex) {
            fail("Incorrect exception was thrown.");
        } catch (InsufficientFundsException ex) {
            assertTrue(true, "Should return InsufficientFundsException");
        }
    }

    @Test
    public void testGiveItemToUserWithEnoughBalance() {
        final int SELECTED_ITEM_ID = 1;
        service.addMoney(new BigDecimal("2.00"));
        // select item in the itemList with enough money
        Egg egg = null;
        try {
            egg = service.giveItemToUser(SELECTED_ITEM_ID);
        } catch (InsufficientFundsException | NoItemInventoryException | VendotronDaoFileException ex) {
            fail("Incorrect exception was thrown.");
        }
        assertNotNull(egg);
    }

    @Test
    public void testGiveItemToUserWithEnoughBalanceButEmptyStock() {
        final int SELECTED_ITEM_ID = 2;
        service.addMoney(new BigDecimal("10.00"));
        // select item in the itemList with enough money
        try {
            service.giveItemToUser(SELECTED_ITEM_ID);
            fail("Expected ValidationException was not thrown.");
        } catch (InsufficientFundsException | VendotronDaoFileException ex) {
            fail("Incorrect exception was thrown.");
        } catch (NoItemInventoryException ex) {
            assertTrue(true, "Should return NoItemInventoryException");
        }
    }
}
