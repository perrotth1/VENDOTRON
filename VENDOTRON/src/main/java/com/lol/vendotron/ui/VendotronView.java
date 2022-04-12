/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lol.vendotron.ui;

import java.util.List;
//import com.lol.vendotron.dao.VendotronDaoFileException;
import com.lol.vendotron.dto.Egg;
import com.lol.vendotron.utils.CoinType;
import java.math.BigDecimal;
import java.util.Map;

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
        io.print("======= MAIN MENU =======");
        io.print("1. Put Money To Buy");
        io.print("2. Select Item");
        io.print("3. Cancel & Return Money");
        io.print("4. Exit");
        io.print("=========================");

        return io.readInt("Please choose one of the options above:", 1, 4);
    }

    // This method was made by Jeonghoon by the way
    public void displayAllItems(List<Egg> itemList) {
        io.print("\n-------------- VENDING MACHINE ITEMS ----------------");
        itemList.stream().forEach(item -> {
            String priceLabel = (item.getStock() == 0)
                    ? "[OUT OF STOCK]"
                    : "$" + item.getCost();
            io.print(item.getId() + " : " + item.getName() + " - " + priceLabel);
        });
        io.print("-----------------------------------------------------\n");
    }

    // Display
    public int getItemSelection(int min, int max) {
        return io.readInt("Please choose one of the items (enter number):", min, max);
    }

    // Display Banners
    public BigDecimal displayAddMoney(BigDecimal min, BigDecimal max) {
        return io.readBigDecimal("Please enter amount of money: ", min, max);
    }

    public void displayCurrentBalance(BigDecimal balance) {
        io.print("** Current balance: " + balance + " **\n");
    }

    public void displayDispensingItem(Egg egg) {
        io.print("-----------------------------------------------------");
        io.print("Now dispensing: " + egg.getName());
        io.print(egg.getCaption());
        io.print("-----------------------------------------------------");
    }

    public void displayChanges(Map<CoinType, Integer> changes) {
        if (changes.isEmpty()) {
            io.print("--- NO Money to Return --\n");
        } else {
            io.print("-------- Changes --------");
            changes.entrySet()
                    .stream()
                    .forEach(coin -> {
                        io.print(coin.getKey() + ": " + coin.getValue());
                    });
            io.print("-------------------------\n");
        }
    }

    public void displayUnknownCommand() {
        io.print("-------- Unknown Command ---------");
    }
    public void displayError(String e) {
        io.print("++++++++++++++++++++++++++++++++++");
        io.print("+ ERROR: " + e);
        io.print("++++++++++++++++++++++++++++++++++");
    }

    public void displayExitMessage() {
        io.print("-- Thank you for using service! --");
    }

    public void pressEnterContinue(){
        io.readString("Press enter to continue...");
    }
}
