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

        try {
            while (keepGoing) {
                try {
                    menuSelection = getMenuSelection();

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
                    view.displayError(e.getMessage());
                    view.pressEnterContinue();
                }
            }
            exitService();
        } catch (VendotronDaoFileException e) {
            view.displayError(e.getMessage());
            view.pressEnterContinue();
        }
    }

    // display main menu and current balance
    // return user's selection
    private int getMenuSelection() throws VendotronDaoFileException {
        List<Egg> itemList = service.getAllItems();

        view.displayAllItems(itemList);
        view.displayCurrentBalance(service.getCurrentBalance());

        return view.getMenuSelection();
    }

    // get money amount from user
    // min: 0.1, max: 20.0
    private void putMoneyToBuy() {
        BigDecimal min = new BigDecimal("0.10");
        BigDecimal max = new BigDecimal("20.00");
        BigDecimal moneyAmount = view.displayAddMoney(min, max);
        service.addMoney(moneyAmount);
    }

    private void selectItem() throws InsufficientFundsException, NoItemInventoryException, VendotronDaoFileException {
        int selectedId = view.getItemSelection(1, service.getAllItems().size());
        Egg egg = service.giveItemToUser(selectedId);
        view.displayDispensingItem(egg);
        view.pressEnterContinue();
    }

    // return changes to user and back to main menu.
    private void cancel() {
        Map<CoinType, Integer> changes = service.returnChanges();

        view.displayChanges(changes);
        view.pressEnterContinue();
    }

    private void unknownCommand() {
        view.displayUnknownCommand();
    }

    private void exitService() {
        // return user's money before exit.
        Map<CoinType, Integer> changes = service.returnChanges();

        view.displayChanges(changes);
        view.displayExitMessage();
    }
}
