/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lol.vendotron.dao;

/**
 *
 * @author Henry
 */
public interface VendotronAuditDao {

    void writeAuditEntry(String entry) throws VendotronDaoFileException;
}
