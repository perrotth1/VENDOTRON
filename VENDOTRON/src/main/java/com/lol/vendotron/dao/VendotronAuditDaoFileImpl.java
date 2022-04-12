/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lol.vendotron.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author Henry
 */
public class VendotronAuditDaoFileImpl implements VendotronAuditDao {

    public static final String AUDIT_FILE = "vendotron_audit.txt";

    @Override
    public void writeAuditEntry(String entry) throws VendotronDaoFileException {
        try ( PrintWriter out = new PrintWriter(new FileWriter(AUDIT_FILE, true))) {
            LocalDateTime timestamp = LocalDateTime.now();
            out.println(timestamp.toString() + " : " + entry);
            out.flush();
        } catch (IOException e) {
            throw new VendotronDaoFileException("Could not persist audit information.", e);
        }
    }
}
