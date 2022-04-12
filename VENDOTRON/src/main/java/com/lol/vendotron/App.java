/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lol.vendotron;

import com.lol.vendotron.controller.VendotronController;
import com.lol.vendotron.dao.VendotronAuditDao;
import com.lol.vendotron.dao.VendotronAuditDaoFileImpl;
import com.lol.vendotron.dao.VendotronDao;
import com.lol.vendotron.dao.VendotronDaoFileImpl;
import com.lol.vendotron.service.VendotronServiceLayer;
import com.lol.vendotron.service.VendotronServiceLayerImpl;
import com.lol.vendotron.ui.UserIO;
import com.lol.vendotron.ui.UserIOConsoleImpl;

/**
 *
 * @author Henry
 */
public class App {
    public static void main(String[] args) {
        UserIO io = new UserIOConsoleImpl();
//        VendotronView view = new VendotronViewImpl(io);
        VendotronDao dao = new VendotronDaoFileImpl();
//
        // Instantiate the Audit DAO
        VendotronAuditDao auditDao = new VendotronAuditDaoFileImpl();
        VendotronServiceLayer service = new VendotronServiceLayerImpl(dao, auditDao);

        // for testing
        VendotronController controller = new VendotronController(service);
        /////

//        VendotronController controller = new VendotronController(service, view);

        controller.run();
    }

}
