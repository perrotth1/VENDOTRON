package com.lol.vendotron.dao;

import com.lol.vendotron.dto.Egg;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.math.BigDecimal;
import java.math.RoundingMode;

import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;

/**
 *
 * @author Henry
 */
public class VendotronDaoFileImpl implements VendotronDao {
    private Map<Integer, Egg> eggs = new HashMap<>();
    private BigDecimal balance = new BigDecimal("0").setScale(2, RoundingMode.HALF_UP);
    
    private final String DATA_FILE;
    private final String DELIMITER = "::";
    
    public VendotronDaoFileImpl(){
        this.DATA_FILE = "DATA_FILE.txt";
    }
    public VendotronDaoFileImpl(String _dataFile){
        this.DATA_FILE = _dataFile;
    }
    
    @Override
    public List<Egg> getAllEggs() throws VendotronDaoFileException {
        load();
        return new ArrayList(this.eggs.values());
    }

    @Override
    public Egg getEgg(int _id) throws VendotronDaoFileException {
        load();
        return this.eggs.get(_id);
    }

    @Override
    public void decrementStock(int _id) throws VendotronDaoFileException {
        load();
        this.eggs.get(_id).decrement();
        store();
    }
    
    public String marshall(Egg _egg){
        String line = _egg.getId() + DELIMITER + _egg.getName() + DELIMITER
                        + _egg.getOrigin() + DELIMITER + _egg.getCost() + DELIMITER
                        + _egg.getStock();
        return line;
    }
    
    public Egg unmarshall(String _line){
        String[] tokens = _line.split(DELIMITER);
        int id = Integer.parseInt(tokens[0]);
        String name = tokens[1], origin = tokens[2];
        BigDecimal cost = new BigDecimal(tokens[3]).setScale(2, RoundingMode.HALF_UP);
        int stock = Integer.parseInt(tokens[4]);
        
        Egg newEgg = new Egg(id,name,origin,cost,stock);
        return newEgg;
    }
    
    public void store() throws VendotronDaoFileException{
        PrintWriter out;
        
        try{
            out = new PrintWriter( new FileWriter(DATA_FILE) );
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
    
    public void load() throws VendotronDaoFileException{
        Scanner in;
        
        try{
            in = new Scanner( new BufferedReader( new FileReader(DATA_FILE)));
        }
        catch(java.io.FileNotFoundException e){
            throw new VendotronDaoFileException("Could not write to file", e);
        }
        
        while(in.hasNextLine()){
            String line = in.nextLine();
            if(line.trim().length() > 0){
                Egg egg = unmarshall(line);
                eggs.put(egg.getId(), egg);
            }
        }   
        in.close();
    }

    @Override
    public BigDecimal getBalance() {
        return this.balance;
    }

    @Override
    public BigDecimal addToBalance(BigDecimal _amount) {
        this.balance = this.balance.add(_amount);
        return this.balance;
    }

    @Override
    public void clearBalance() {
        this.balance = new BigDecimal("0").setScale(2, RoundingMode.HALF_UP);
    }
}
