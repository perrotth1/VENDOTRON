package com.lol.vendotron.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.lol.vendotron.dto.*;
import static com.lol.vendotron.utils.InitInventory.initFile;

import java.io.FileWriter;
import java.util.List;
import java.math.BigDecimal;

/**
 *
 * @author Henry
 */
public class VendotronDaoFileImplTest {
    /**
     * TEST PLAN
     * test getAllEggs
     * test getEgg
     * test decrementGetStock
     * test addGetBalance
     * test addRemoveGetBalance
     * test addClearGetBalance
     */
    
    VendotronDao testDao;
    
    public VendotronDaoFileImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws Exception{
        //set up test dao
        
        String file = "TEST_FILE.txt";
        initFile(file);
        testDao = new VendotronDaoFileImpl(file);
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testGetAllEggs() throws Exception{
        //ARRANGE
        
        //ACT
        List<Egg> results = testDao.getAllEggs();
        
        //ASSERT
        assertEquals(11, results.size());
        
    }
    
    @Test
    public void testGetEgg() throws Exception{
        //ARRANGE
        int id = 5; 
        //ACT
        Egg result = testDao.getEgg(id);
        
        //ASSERT
        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Tea Egg", result.getName());
    }
    
    @Test
    public void testDecrementGetStock() throws Exception{
        //ARRANGE
        Egg testEgg = testDao.getEgg(5);
        int stock = testEgg.getStock();
        //ACT
        testDao.decrementStock(5);
        
        //ASSERT
        
        assertEquals(stock - 1, testDao.getEgg(5).getStock());
    }
    
    @Test
    public void testAddGetBalance() throws Exception{
        //ARRANGE
        BigDecimal added = new BigDecimal("5.50");
        testDao.addToBalance(added);
        
        //ACT
        BigDecimal result = testDao.getBalance();
        
        //ASSERT
        assertTrue( added.equals(result) );
    }
    
    @Test
    public void testAddRemoveGetBalance() throws Exception{
        //ARRANGE
        BigDecimal added = new BigDecimal("5.50");
        testDao.addToBalance(added);
        BigDecimal removed = new BigDecimal("2.00");
        BigDecimal expected = added.subtract(removed);
        
        //ACT
        testDao.subtractBalance(removed);
        BigDecimal result = testDao.getBalance();
        
        //ASSERT
        assertEquals( expected, result );
    }
    
    @Test
    public void testAddClearGetBalance() throws Exception{
        //ARRANGE
        BigDecimal added = new BigDecimal("5.50");
        testDao.addToBalance(added);
        BigDecimal expected = new BigDecimal("0.00");
        
        //ACT
        testDao.clearBalance();
        BigDecimal result = testDao.getBalance();
        
        //ASSERT
        assertEquals( expected, result );
    }
}
