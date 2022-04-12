package com.lol.vendotron.ui;

import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO {
    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public String readString(String prompt) {
        Scanner sc = new Scanner(System.in);
        System.out.println(prompt);
        return( sc.nextLine() );
    }

    @Override
    public int readInt(String prompt) {
        Scanner sc = new Scanner(System.in);
        System.out.println(prompt);
        return( Integer.parseInt( sc.nextLine() ) );
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        Scanner sc = new Scanner(System.in);
        int input;
        while(true) {
            System.out.println(prompt);
            input = Integer.parseInt( sc.nextLine() ) ;
            if(input >= min && input <= max ) {
                return input;
            }
        }
    }

    @Override
    public double readDouble(String prompt) {
        Scanner sc = new Scanner(System.in);
        System.out.println(prompt);
        return( Double.parseDouble( sc.nextLine() ) );
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        Scanner sc = new Scanner(System.in);
        double input;
        while(true) {
            System.out.println(prompt);
            input = Double.parseDouble( sc.nextLine() ) ;
            if(input >= min && input <= max ) {
                return input;
            }
        }
    }

    @Override
    public float readFloat(String prompt) {
        Scanner sc = new Scanner(System.in);
        System.out.println(prompt);
        return( Float.parseFloat( sc.nextLine() ) );
    
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        Scanner sc = new Scanner(System.in);
        float input;
        while(true) {
            System.out.println(prompt);
            input = Float.parseFloat( sc.nextLine() ) ;
            if(input >= min && input <= max ) {
                return input;
            }
        }
    }

    @Override
    public long readLong(String prompt) {
        Scanner sc = new Scanner(System.in);
        System.out.println(prompt);
        return( Long.parseLong( sc.nextLine() ) );
    
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        Scanner sc = new Scanner(System.in);
        long input;
        while(true) {
            System.out.println(prompt);
            input = Long.parseLong( sc.nextLine() ) ;
            if(input >= min && input <= max ) {
                return input;
            }
        }
    }
}
