/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lol.vendotron.ui;

import java.util.List;
//import com.lol.vendotron.dao.VendotronDaoFileException;
import com.lol.vendotron.dto.Egg;

/**
 *
 * @author Nicole
 */
public class VendotronView {

    private UserIO io;

    // Constructor
    public VendotronView(UserIO io) {
        this.io = io;
    }

    public int getMenuSelection() {
        io.print("===== MAIN MENU =====");
        io.print("1. Put Money To Buy");
        io.print("2. Select Item");
        io.print("3. Cancel");
        io.print("4. Exit");

        return io.readInt("Please choose one of the options above:", 1, 4);
    }

    public void displayAllItems(List<Egg> itemList) {
        io.print("-------------- VENDING MACHINE ITEMS ----------------");
        itemList.stream().forEach(item -> {
            String priceLabel = (item.getStock()==0) ? "[OUT OF STOCK]" : "$"+item.getCost();
            io.print(item.getId() + " : " + item.getName() + " - "+priceLabel);
        }
        );
        io.print("-----------------------------------\n");
    }

// Display
    public void displayselectItem() {
//        io.print();
    }

// Display Banners
    public void displayAddMoney() {
        io.print("---Buy an Item---");
    }

//    public void displayNoMoney() {
//        io.print("---Insufficient Funds---");
//    }

    public void displayCancel() {
        io.print("---Change---");
    }

    public void displayunknownCommand() {
        io.print("---Unknown Command---");
    }

    public void displayexitMessage() {
        io.print("---Bye!---");
    }
}
