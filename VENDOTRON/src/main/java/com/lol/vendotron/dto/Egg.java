package com.lol.vendotron.dto;

import java.math.BigDecimal;
/**
 *
 * @author nicolemagpantay
 */
public class Egg {
    private String name;
    private BigDecimal cost;
    private int stock;
    
    public Egg(String _name, BigDecimal _cost, int _stock){
        this.name = _name;
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
        return(this.name + " : " + this.cost + " : [x" + this.stock + "]");
    }
    
}
