/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lol.vendotron.controller;

import com.lol.vendotron.dao.VendotronDaoFileException;
import com.lol.vendotron.service.InsufficientFundsException;
import com.lol.vendotron.service.NoItemInventoryException;
import com.lol.vendotron.service.VendotronServiceLayer;
import com.lol.vendotron.ui.UserIO;
import com.lol.vendotron.ui.UserIOConsoleImpl;
import com.lol.vendotron.ui.VendotronView;
import java.math.BigDecimal;

/**
 *
 * @author nicolemagpantay
 */
public class VendotronController {

    // FOR TESTING
    UserIO io = new UserIOConsoleImpl();
    ////////////////////////////

    private VendotronServiceLayer service;
    private VendotronView view;

    public VendotronController(VendotronServiceLayer service) {
        this.service = service;
    }

    public VendotronController(VendotronServiceLayer service, VendotronView view) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection;

        // TODO: TRY CATCH
        try {
            while (keepGoing) {
                menuSelection = getMenuSelection();

                // list of item
                switch (menuSelection) {
                    case 1:
                        putMoneyToBuy();
                        break;
                    case 2:
                        selectItem();
                        break;
                    case 3:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitMessage();
        } catch (InsufficientFundsException | NoItemInventoryException e) {
//                VendotronDaoFileException e) {
            // TODO: should call a method in VIEW
            System.out.println(e.getMessage());
        }
    }

    private int getMenuSelection() {
        // for testing
        System.out.println("put money");
        System.out.println("selectItem");
        System.out.println("exit");
        return io.readInt("SELECT NUMBER: ");

        // TODO: shold call a method in VIEW
        // return view.printMenuAndGetSelection();
    }

    private void putMoneyToBuy() {
        System.out.println("PUT MONEY TO BUY");

        // TODO: Call service layer
        // display menu for adding money from VIEW
        service.addMoney(new BigDecimal("5.00"));
    }

    private void selectItem() throws InsufficientFundsException, NoItemInventoryException {
        System.out.println("SELECT ITEM");
        // TODO:
        // Display item menu
        service.giveItemToUser("test");
    }

    private void unknownCommand() {
        // TODO: should make a call to VIEW
        System.out.println("Unknown command");
    }

    private void exitMessage() {
        // TODO: should make a call to VIEW
        System.out.println("BYE!!!");
    }
}
