package com.lol.vendotron.ui;

import java.math.BigDecimal;
import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO {
    final private Scanner sc;

    public UserIOConsoleImpl() {
        sc = new Scanner(System.in);
    }

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return sc.nextLine();
    }

    @Override
    public int readInt(String prompt) {
        int userInput;

        while (true) {
            System.out.println(prompt);

            try {
                userInput = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("*ERROR* Invalid integer format. Please try again!");
            }
        }
        return userInput;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int userInput;

        while (true) {
            userInput = readInt(prompt);
            if (min <= userInput && userInput <= max) {
                break;
            } else {
                System.out.format("*ERROR* Invalid value: Please enter the value between %d and %d\n", min, max);
            }
        }
        return userInput;
    }

    @Override
    public double readDouble(String prompt) {
        double userInput;

        while (true) {
            System.out.println(prompt);

            try {
                userInput = Double.parseDouble(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("*ERROR* Invalid integer format. Please try again!");
            }
        }
        return userInput;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double userInput;

        while (true) {
            userInput = readDouble(prompt);
            if (min <= userInput && userInput <= max) {
                break;
            } else {
                System.out.format("*ERROR* Invalid value: Please enter the value between %f and %f\n", min, max);
            }
        }
        return userInput;
    }

    @Override
    public float readFloat(String prompt) {
        float userInput;

        while (true) {
            System.out.println(prompt);

            try {
                userInput = Float.parseFloat(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("*ERROR* Invalid integer format. Please try again!");
            }
        }
        return userInput;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float userInput;

        while (true) {
            userInput = readFloat(prompt);
            if (min <= userInput && userInput <= max) {
                break;
            } else {
                System.out.format("*ERROR* Invalid value: Please enter the value between %f and %f\n", min, max);
            }
        }
        return userInput;
    }

    @Override
    public long readLong(String prompt) {
        long userInput;

        while (true) {
            System.out.println(prompt);

            try {
                userInput = Long.parseLong(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("*ERROR* Invalid integer format. Please try again!");
            }
        }
        return userInput;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long userInput;

        while (true) {
            userInput = readLong(prompt);
            if (min <= userInput && userInput <= max) {
                break;
            } else {
                System.out.format("*ERROR* Invalid value: Please enter the value between %d and %d\n", min, max);
            }
        }
        return userInput;
    }

//    @Override
//    public BigDecimal readBigDecimal(String prompt) {
//        Scanner sc = new Scanner(System.in);
//        System.out.println(prompt);
//        return new BigDecimal(sc.nextLine());
//    }
    @Override
    public BigDecimal readBigDecimal(String prompt) {
        BigDecimal userInput;

        while (true) {
            System.out.println(prompt);

            try {
                userInput = new BigDecimal(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("*ERROR* Invalid decimal format. Please try again!");
            }
        }
        return userInput;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max) {
        BigDecimal userInput;

        while (true) {
            userInput = readBigDecimal(prompt);
            if (0 <= userInput.compareTo(min) && userInput.compareTo(max) <= 0) {
                break;
            } else {
                System.out.format("*ERROR* Invalid value: Please enter the value between %s and %s\n", min, max);
            }
        }
        return userInput;
    }
}
