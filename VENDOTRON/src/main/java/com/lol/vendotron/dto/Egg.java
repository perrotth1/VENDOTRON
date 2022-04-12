package com.lol.vendotron.dto;

import java.math.BigDecimal;
import java.util.Random;
/**
 *
 * @author nicolemagpantay
 */
public class Egg {
    private String name, origin, caption;
    private BigDecimal cost;
    private int id, stock;
    private boolean isBroken;
    
    public Egg(int _id, String _name, String _origin, BigDecimal _cost, int _stock, String _caption){
        this.id = _id;
        this.name = _name;
        this.origin = _origin;
        this.cost = _cost;
        this.stock = _stock;
        this.caption = _caption;
        this.isBroken = false;
    }
    
    public String getName(){
        return this.name;
    }
    public BigDecimal getCost(){
        return this.cost;
    }
    public int getStock(){
        return this.stock;
    }
    public String getOrigin(){
        return this.origin;
    }
    public int getId(){
        return this.id;
    }
    
    public void setStock(int _stock){
        this.stock = _stock;
    }
    
    public void rollIfBreaks(){
        Random rg = new Random();
        if( rg.nextInt(10) > 5 ){
            this.isBroken = true;
        }
    }
    
    public String getCaption(){
        return (isBroken) 
                ? "Bad luck. This egg broke when it was dispensed from the machine." 
                : this.caption;
    }
    
    public int decrement(){
        if(this.stock > 0){
            this.stock--;
        }
        return stock;
    }
    
    @Override
    public String toString(){
        return(this.name 
                + " (from " + this.origin + ") : "
                + this.cost
                + " [x" + this.stock + "]" );
    }
    
}
