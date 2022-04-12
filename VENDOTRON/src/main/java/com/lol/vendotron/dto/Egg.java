package com.lol.vendotron.dto;

import java.math.BigDecimal;
/**
 *
 * @author nicolemagpantay
 */
public class Egg {
    private String name, origin;
    private BigDecimal cost;
    private int id, stock;
    
    public Egg(int _id, String _name, String _origin, BigDecimal _cost, int _stock){
        this.id = _id;
        this.name = _name;
        this.origin = _origin;
        this.cost = _cost;
        this.stock = _stock;
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
