package ch.noseryoung.blj;

import java.util.ArrayList;
import java.util.Scanner;



public class Menu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void menuLoop() throws InterruptedException {

        // Setup
        double userMoney = 40;
        String secretKey = "AlphaSigma";
        ArrayList<Item> vendingMachine = new ArrayList<>();
        addStartingItems(vendingMachine);

        welcomeMessage();

        waitOnUserStartInput(vendingMachine, secretKey);

        while (true) {
            printVendingMachine(vendingMachine);

            // User Item selection process
            String userChoice;
            do {
                System.out.println("You have \u001B[33m" + round(userMoney) + "\u001B[0m Franks left");
                System.out.print("Please enter the product number (type 'cancel' to exit): ");
                userChoice = scanner.nextLine();

                if (isSecretKeyInput(vendingMachine, userChoice, secretKey)) {continue;}
                if (isCancelInput(userChoice)) {return;}

                int choiceNum = stringToInt(userChoice);
                if (choiceNum < 1 || choiceNum > vendingMachine.size()) {
                    printError("There is no product with the number " + userChoice + "!");
                }
            } while (!isValidChoice(userChoice, vendingMachine.size()));

            // Cancel Input if these conditions are met
            if (stringToInt(userChoice) < 1 || stringToInt(userChoice) > vendingMachine.size()) {
                printError("Please give a number wich exists actually");
                continue;
            }
            int choice = stringToInt(userChoice);
            if (choice < 1 || choice > vendingMachine.size()) {
                printError("Please enter a valid product number.");
                continue;
            }
            Item chosenItem = vendingMachine.get(choice - 1);
            if (userMoney < chosenItem.getPrice()) {
                printError("You don't have enough money for this item. Please choose a cheaper product.");
                continue;
            }
            if (chosenItem.getQuantity() <= 0) {
                printError("This item is sold out. You cant buy it.");
                continue;
            }


            // User Input of money
            String moneyInput;
            double moneyInserted;
            do {
                System.out.println("Your item cost's "+chosenItem.getPrice()+" Franks");
                System.out.println("Your money status: \u001B[33m" + round(userMoney) + "\u001B[0m Franks");
                System.out.print("Please insert your money (example: 6.5): ");
                moneyInput = scanner.nextLine();

                if (isCancelInput(userChoice)) {return;}

                // Ask for more money if you don't have enough
                else if (stringToDouble(moneyInput) > userMoney) {
                    do {
                        printError("You don't have that much money");
                        printError("try again");
                        moneyInput = scanner.nextLine();
                    } while (stringToDouble(moneyInput) > userMoney);
                }
                moneyInserted = round(stringToDouble(moneyInput));

                // If you dont have enough money
                int choiceNum = stringToInt(userChoice);
                if (choiceNum < 1 || choiceNum > vendingMachine.size()) {
                    printError("There is no product with the number " + userChoice + "!");
                }

            if (moneyInserted < chosenItem.getPrice()) {
                printError("You need to insert enough money");
            }
        } while (moneyInserted < chosenItem.getPrice());

        // Change
        double change = round(moneyInserted) - chosenItem.getPrice();
        if (change > 0) {
            System.out.println("You get " + round(change) + " Franks back.");//Change calculation
        }

        // Transaction
        userMoney -= chosenItem.getPrice();
        chosenItem.buyOne();
        transactionSuccessfulMessage(chosenItem ,userMoney);
        }
    }



    // |----- Outsourcing -----|

    private static void addStartingItems(ArrayList<Item> vending_machine) {
        vending_machine.add(new Item("FocusWater", "default", 2.7));
        vending_machine.add(new Item("Doner kebab", "default", 13.5));
        vending_machine.add(new Item("Baklava", "default", 3.5));
        vending_machine.add(new Item("Rivella", "default", 2.5));
        vending_machine.add(new Item("Bratwurst", "default", 7.0));
        vending_machine.add(new Item("Toblerone", "default", 4.0));
        vending_machine.add(new Item("Mate tea", "default", 3.0));
        vending_machine.add(new Item("Pretzel", "default", 2.2));
        vending_machine.add(new Item("Poutine", "default", 6.5));
        vending_machine.add(new Item("Sushi", "default", 12.0));
        vending_machine.add(new Item("Pizza Margherita", "default", 10.5));
        vending_machine.add(new Item("Churros", "default", 3.8));
        vending_machine.add(new Item("Kimchi", "default", 5.0));
        vending_machine.add(new Item("Alpha-Lighter", "default", 5.0));
    }

    private static void welcomeMessage() {
        System.out.println("\nWELCOME TO THE SNACK VENDING MACHINE!");
        System.out.println("This is a vending machine developed for Alphas. Please choose your item and enjoy it with a smile (:");
    }

    private static void waitOnUserStartInput(ArrayList<Item> vendingMachine, String secretKey) {
        String userInput;
        do {
            System.out.print("Enter 'Start': ");
            userInput = scanner.nextLine();

            if (isCancelInput(userInput)) {return;}
            if (isSecretKeyInput(vendingMachine, userInput, secretKey)) {return;}

            if (!userInput.toUpperCase().startsWith("S")) {
                printError("Please enter Start");
            }

        } while (!userInput.toUpperCase().startsWith("S")); // Accepting as long as first letter is 's' / 'S'
    }

    private static void printVendingMachine(ArrayList<Item> vending_machine) {
        System.out.println("Available Items:");
        System.out.println("""
                 ___________________________________
                |           SNACK AUTOMAT          |
                |                                  |
                |----------------------------------|""");
        for (int i = 0; i < vending_machine.size(); i++) {
            Item item = vending_machine.get(i);
            if (item.getQuantity() > 0) {
                System.out.println("| [" + (i + 1) + "] " + item.getName() + " - " + item.getPrice() + " Franks");
            } else {
                System.out.println("| \u001B[9m[" + (i + 1) + "] " + item.getName() + " - " + item.getPrice() + " Franks\u001B[0m");
            }
        }
        System.out.println("""
                |__________________________________|
                |                                  |
                |__________________________________|
                """);
    }

    private static boolean isSecretKeyInput (ArrayList<Item> vendingMachine, String input, String secretKey) {
        if (input.equals(secretKey)) {
            Admin.adminMenu(vendingMachine);
            printVendingMachine(vendingMachine);
            return true;
        }
        return false;
    }

    private static boolean isCancelInput (String input) {
        if (input.equalsIgnoreCase("cancel")) {
            System.out.println("Thank you for considering us :))");
            return true;
        }
        return false;
    }

    private static void transactionSuccessfulMessage(Item chosenItem, Double userMoney) throws InterruptedException {
        System.out.println("\u001B[32m\nYour purchase was successful!\u001B[0m");
        System.out.println("You have bought " + chosenItem.getName() + " for " + chosenItem.getPrice() + " Franks");
        System.out.println("You have \u001B[33m" + round(userMoney) + "\u001B[0m Franks left");
        Thread.sleep(3500);
    }



    // |----- Functional Methods -----|

    private static boolean isValidChoice(String input, int maxSize) {
        int choice = stringToInt(input);
        return choice >= 1 && choice <= maxSize;
    }

    private static int stringToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            printError("Please try again");
            return 0;
        }
    }

    private static double stringToDouble(String input) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            printError("Please try again");
            return 0;
        }
    }

    private static double round(double value) {
        return Math.round(value * 100) / 100.00;
    }

    private static void printError(String message) {
        System.out.println("\u001B[31m" + message + "\u001B[0m");
    }
}
