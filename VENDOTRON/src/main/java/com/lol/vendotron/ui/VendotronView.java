/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lol.vendotron.ui;

import java.util.List;
import com.lol.vendotron.dao.VendotronDaoFileException;
import com.lol.vendotron.dto.Egg;

/**
 *
 * @author Nicole
 */

public class VendotronView 
{
    private UserIO io = new UserIOConsoleImpl();
}

public int getMenuSelection()
{
    io.print("Main Menu");
    io.print("1. Put Money To Buy");
    io.print("2. Select Item");
    io.print("3.");
    io.print("4. Cancel");
    io.print("5.");
    
    return io.readInt("Please choose one of the options above:")    
}

// Display 
public void displayselectItem()
{
    io.print();
}


// Display Banners

public void display()
{
    io.print("---Buy an Item---");
}

public void display()
{
    io.print("---Insufficient Funds---");
}

public void displayCancel()
{
    io.print("---Dispensing Egg---");
}

public void displayunknownCommand()
{
    io.print("---Unknown Command---");
}

public void displayexitMessage()
{
    io.print("---Bye!---");
}
