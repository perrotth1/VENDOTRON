package com.lol.vendotron.utils;

import com.lol.vendotron.dao.*;
import com.lol.vendotron.dto.Egg;

import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.math.BigDecimal;
import java.math.RoundingMode;
/**
 *
 * @author Henry
 */
public class InitInventory {
    public static Map<Integer,Egg> build(){
        Map<Integer, Egg> eggs = new HashMap<>();
        
        Egg egg = new Egg(1, "Egg", "Mixed Origin", new BigDecimal("1.00").setScale(2, RoundingMode.HALF_UP), 5);
        eggs.put(egg.getId(), egg);
        
        egg = new Egg(2, "Golden Egg", "Mixed Origin", new BigDecimal("20.00").setScale(2, RoundingMode.HALF_UP), 1);
        eggs.put(egg.getId(), egg);
        
        egg = new Egg(3, "Rotten Egg", "Mixed Origin", new BigDecimal("0.25").setScale(2, RoundingMode.HALF_UP), 2);
        eggs.put(egg.getId(), egg);
        
        egg = new Egg(4, "Deviled Egg", "Mixed Origin", new BigDecimal("3.50").setScale(2, RoundingMode.HALF_UP), 5);
        eggs.put(egg.getId(), egg);
        
        egg = new Egg(5, "Tea Egg", "China", new BigDecimal("5.00").setScale(2, RoundingMode.HALF_UP), 3);
        eggs.put(egg.getId(), egg);
        
        egg = new Egg(6, "Century Egg", "China", new BigDecimal("7.00").setScale(2, RoundingMode.HALF_UP), 3);
        eggs.put(egg.getId(), egg);
        
        egg = new Egg(7, "Smoked Egg", "China", new BigDecimal("3.25").setScale(2, RoundingMode.HALF_UP), 3);
        eggs.put(egg.getId(), egg);
        
        egg = new Egg(8, "Shoyu Egg", "Japan", new BigDecimal("1.75").setScale(2, RoundingMode.HALF_UP), 10);
        eggs.put(egg.getId(), egg);
        
        egg = new Egg(9, "Black Hot-Spring Egg", "Japan", new BigDecimal("12.00").setScale(2, RoundingMode.HALF_UP), 3);
        eggs.put(egg.getId(), egg);
        
        egg = new Egg(10, "Pickled Egg", "Germany", new BigDecimal("1.25").setScale(2, RoundingMode.HALF_UP), 10);
        eggs.put(egg.getId(), egg);
        
        egg = new Egg(11, "Scotch Egg", "UK", new BigDecimal("5.50").setScale(2, RoundingMode.HALF_UP), 5);
        eggs.put(egg.getId(), egg);
        
        return eggs;
    }
    
    public static String marshall(Egg _egg){
        String DELIMITER = "::";
        String line = _egg.getId() + DELIMITER + _egg.getName() + DELIMITER
                        + _egg.getOrigin() + DELIMITER + _egg.getCost() + DELIMITER
                        + _egg.getStock();
        return line;
    }
    
    public static void initFile(String _file) throws VendotronDaoFileException{
        Map<Integer, Egg> eggs = build();
        PrintWriter out;
        
        try{
            out = new PrintWriter( new FileWriter(_file) );
        }
        catch(java.io.IOException e){
            throw new VendotronDaoFileException("Could not write to file", e);
        }
        
        for(Integer id : eggs.keySet()){
            String line = marshall(eggs.get(id));
            out.println(line);
            out.flush();
        }
        
        out.close();
    }
    
    public static void main(String[] args) throws VendotronDaoFileException{
        initFile("DATA_FILE.txt");
    }
    
}
