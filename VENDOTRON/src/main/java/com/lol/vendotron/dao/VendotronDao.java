package com.lol.vendotron.dao;

import java.util.List;
import java.math.BigDecimal;
import com.lol.vendotron.dto.*;
/**
 *
 * @author Henry
 */
public interface VendotronDao {
    
    public List<Egg> getAllEggs() throws VendotronDaoFileException;
    
    public Egg getEgg(int _id) throws VendotronDaoFileException;
    
    public void decrementStock(int _id) throws VendotronDaoFileException;
    
    public BigDecimal getBalance();
    
    public BigDecimal addToBalance(BigDecimal _amount);
    
    public BigDecimal subtractBalance(BigDecimal _amount);
    
    public void clearBalance();

}
