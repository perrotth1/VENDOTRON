/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lol.vendotron.controller;

import com.lol.vendotron.dao.VendotronDaoFileException;
import com.lol.vendotron.dto.Egg;
import com.lol.vendotron.service.InsufficientFundsException;
import com.lol.vendotron.service.NoItemInventoryException;
import com.lol.vendotron.service.VendotronServiceLayer;
import com.lol.vendotron.ui.VendotronView;
import com.lol.vendotron.utils.CoinType;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author nicolemagpantay
 */
public class VendotronController {

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
                try {
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
                            cancel();
                            break;
                        case 4:
                            keepGoing = false;
                            break;
                        default:
                            unknownCommand();
                    }
                } catch (InsufficientFundsException | NoItemInventoryException e) {
                    System.out.println(e.getMessage());
                }
            }
            exitService();
        } catch (VendotronDaoFileException e) {
            // TODO: should call a method in VIEW
            System.out.println(e.getMessage());
        }
    }

    private int getMenuSelection() throws VendotronDaoFileException {
        List<Egg> itemList = service.getAllItems();
        // TODO: display USING VIEW
        view.displayAllItems(itemList);
  //      view.displayNoMoney();

        return view.getMenuSelection();
    }

    private void putMoneyToBuy() {
        System.out.println("PUT MONEY TO BUY");

        // TODO: Call service layer
        // display menu for adding money from VIEW
        service.addMoney(new BigDecimal("1.20"));
    }

    private void selectItem() throws InsufficientFundsException, NoItemInventoryException, VendotronDaoFileException {
        // TODO:
        // Display item menu
        service.giveItemToUser(1);
    }

    private void cancel() {
        System.out.println("CANCEL");

        Map<CoinType, Integer> changes = service.returnChanges();
        // TODO:
        // display change with VIEW
        System.out.println(changes);
    }

    private void unknownCommand() {
        // TODO: should make a call to VIEW
        System.out.println("Unknown command");
    }

    private void exitService() {
        // return user's money before exit.
        Map<CoinType, Integer> changes = service.returnChanges();
        // TODO:
        // display change with VIEW
        System.out.println(changes);

        System.out.println("Thank you for using service!");
    }
}
