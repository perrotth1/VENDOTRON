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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Jeonghoon
 */
public class VendotronServiceLayerTest {

    private VendotronServiceLayer service;
    private Egg testClone;

    public VendotronServiceLayerTest() {
        VendotronDao dao = new VendotronDaoStubImpl();
        VendotronAuditDao auditDao = new VendotronAuditDaoStubImpl();

        service = new VendotronServiceLayerImpl(dao, auditDao);
    }

    @AfterEach
    public void tearDown() {
        testClone = new Egg(
                1, // id
                "Egg", // name
                "Mixed Origin", // origin
                new BigDecimal("1.25"), // cost
                1);
    }

    @Test
    public void testGetAllItems() throws VendotronDaoFileException {
        final int EXPECTED_SIZE = 1;
        // ACT & ASSERT
        assertEquals(EXPECTED_SIZE, service.getAllItems().size(),
                "Should only have one item.");
//        assertTrue(service.getAllItems().contains(testClone),
//                "The only item should be Egg");
    }

    @Test
    public void testGiveItemToUser() {
        // select item in the itemList

    }
}
