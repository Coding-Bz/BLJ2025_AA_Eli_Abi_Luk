package ch.noseryoung.blj;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);

    // TODO: Maybe do more Outsourcing in menuLoop
    // TODO: Maybe make design better
    public static void menuLoop() {
        // Setup
        double userMoney = 20;
        boolean programStatus = true; // If the programm should still continue running
        ArrayList<Item> vendingMachine = new ArrayList<Item>();
        addStartingItems(vendingMachine);


        Admin admin = new Admin(scanner);


        welcomeMessage();
        String userInput;
        do {
            System.out.print("Enter Start to begin the program: ");
            userInput = scanner.nextLine();
            if (!userInput.toUpperCase().startsWith("S")){
                System.out.println("Please enter Start");
            }
        } while (!userInput.toUpperCase().startsWith("S"));      //Change: Accepting as long as first letter 's' | 'S'


        while (programStatus) {
            printVendingMachine(vendingMachine);
            // User Item selection process
            String userChoice;
            do {
                System.out.print("\nPlease enter the product number you want to choose(or type 'cancel' to exit): ");
                userChoice = scanner.nextLine();
                if (userChoice.equalsIgnoreCase("cancel")) {
                    break;
                }
            } while (!isValidChoice(userChoice, vendingMachine.size()));

            if(stringToInt(userChoice) < 1 || stringToInt(userChoice) > vendingMachine.size()) {
                    System.out.println("Please give a number wich exists actually");
                    continue;
                }

                // TODO: Error-Message if number of non existing item and if random text was entered 👌
            if (userChoice.equalsIgnoreCase("cancel")) {
                break;
            }

            // Admin function if secret Key was entered // TODO: Add real secret key
            if (userChoice.trim().equalsIgnoreCase(admin.getSecretKey().trim())) {
                admin.adminMenu(vendingMachine);
                continue;
            }

            int choice = stringToInt(userChoice);
            if (choice < 1 || choice > vendingMachine.size()) {
                System.out.println("Please enter a valid product number.");
                continue;
            }

            Item chosenItem = vendingMachine.get(choice - 1);
            if (userMoney < chosenItem.getPrice()) {
                System.out.println("You don't have enough money for this item. Please choose a cheaper product.");
                continue;
            }


            /*

            do {
                System.out.println("Your money status: \u001B[33m" + userMoney + "\u001B[0m Franks");
                if(userMoney<chosenItem.getPrice()){
                    System.out.println("You are broke sorry");

                    if (userMoney<1){
                        System.out.println("You don't have money for any product here");
                        return;
                    }
                    System.out.println("Please choose a cheapet product");
                }while (userMoney<chosenItem.getPrice())


                    System.out.print("Please put your money in the snack machine: ");
                moneyInput = scanner.nextLine(); //Getting a String to reduce errors
                if (stringToDouble(moneyInput) < chosenItem.getPrice()){
                    System.out.println("You have to put enough money please try again");
                    break;
                } else if (stringToDouble(moneyInput)> chosenItem.getPrice()) {
                    System.out.println("You gave too much money so you get "+  (stringToInt(moneyInput) - chosenItem.getPrice()) +" back"); } //Rückgeldberechnung
                else {
                    continue;
                }

                if (userChoice.equalsIgnoreCase("cancel")) {
                    break;
                }

                else if (stringToDouble(moneyInput) < 0.01) {
                    System.out.println("Your number shouldn't be less then 1");
                }

                // TODO: Error-Message if item was 0 or less and if random text was entered
            } while (stringToDouble(moneyInput) < 0.01 || userMoney<chosenItem.getPrice());

            // Transaction
            // TODO: Make, that the program asks you to put in more money, if you haven't put in enough

            userMoney -= chosenItem.getPrice();
            chosenItem.buyOne();

            System.out.println("\u001B[32m\nYour purchase was successful!\u001B[0m");
            System.out.println("You have bought " + chosenItem.getName() + " for " + chosenItem.getPrice() + " Franks");
            System.out.println("You have \u001B[33m" +Math.round(userMoney*100)/100  + "\u001B[0m Franks left");
        }

        System.out.println("Thank you for considering us :))");
    }
             */

//changed abigail
            // User Input if want it wants to buy
            String moneyInput;
            double moneyInserted;
            do {
                System.out.println("Your money status: \u001B[33m" + userMoney + "\u001B[0m Franks");
                System.out.print("Please insert your money (example: 6.5): ");
                moneyInput = scanner.nextLine();
                moneyInserted = stringToDouble(moneyInput);

                if (moneyInserted < chosenItem.getPrice()) {
                    System.out.println("You need to insert enough money. Please try again.");
                }
            } while (moneyInserted < chosenItem.getPrice());

            double change = moneyInserted - chosenItem.getPrice();
            if (change > 0) {
                System.out.println("You get " + change + " Franks back.");    //Rückgeldberechnung
            }

            userMoney -= chosenItem.getPrice();       //actualize
            chosenItem.buyOne();

            System.out.println("\u001B[32m\nYour purchase was successful!\u001B[0m");
            System.out.println("You have bought " + chosenItem.getName() + " for " + chosenItem.getPrice() + " Franks");
            System.out.println("You have \u001B[33m" + Math.round(userMoney * 100) / 100.00 + "\u001B[0m Franks left");
        }

        System.out.println("Thank you for considering us:))");
    }
//till here

        private static boolean isValidChoice(String input, int maxSize) {
            int choice = stringToInt(input);
            return choice >= 1 && choice <= maxSize;
        }


    // |----- Functional Methods -----|

    private static int stringToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Please try again");
            return 0;
        }
    }

    private static double stringToDouble(String input) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Please try again");
            return 0;
        }
    }


    private static void addStartingItems (ArrayList < Item > vending_machine) {

        vending_machine.add(new Item("FocusWater", "Vitamin water from Switzerland", "default", 2.7));
        vending_machine.add(new Item("Döner", "A delicious food from Türkiye", "default", 13.5));
        vending_machine.add(new Item("Baklava", "Sweet desert from Türkiye", "limited", 3.5));
        vending_machine.add(new Item("Rivella", "Popular Swiss soft drink", "default", 2.5));
        vending_machine.add(new Item("Bratwurst", "Swiss grilled sausage", "default", 7.0));
        vending_machine.add(new Item("Toblerone", "Famous Swiss chocolate", "default", 4.0));
        vending_machine.add(new Item("Mate Tee", "Traditional drink from Argentina", "default", 3.0));
        vending_machine.add(new Item("Bretzel", "German twisted bread", "default", 2.2));
        vending_machine.add(new Item("Poutine", "Canadian fries with gravy and cheese curds", "default", 6.5));
        vending_machine.add(new Item("Sushi", "Japanese rice and fish rolls", "default", 12.0));
        vending_machine.add(new Item("Pizza Margherita", "Classic Italian pizza", "default", 10.5));
        vending_machine.add(new Item("Churros", "Spanish fried dough with sugar", "default", 3.8));
        vending_machine.add(new Item("Kimchi", "Korean fermented cabbage", "default", 5.0));
        vending_machine.add(new Item("Alpha-Lighter", "Only Alphas can use it", "limited", 5.0));
    }


    private static void welcomeMessage () {
        System.out.println("\nWELCOME TO THE SNACK VENDING MACHINE!");
        System.out.println("|//////////////////////////////////////////////////////////////////////////////////////|");
        System.out.println("This is a vending machine developed for Alphas please choose your item and enjoy it with a smile");
    }

//complement for visual understanding abigail (proviso)
    private static void printVendingMachine(ArrayList<Item> vending_machine) {
        System.out.println("Available Items:");
        for (int i = 0; i < vending_machine.size(); i++) {
            Item item = vending_machine.get(i);
            System.out.println("[" + (i + 1) + "] " + item.getName() + " - " + item.getPrice() + " Franks");
        }
    }
}
        // TODO: Dynamic display of the snack vending machine
        /*
        System.out.println("  ___________________________\n" +
                    "|       SNACK AUTOMAT       |\n" +
                    "|---------------------------|\n" +
                    "| [1] FocusWater  [2] Döner  |\n" +
                    "| [3] Baklava     [4] Rivella|\n" +
                    "| [5] Bratwurst   [6] Tobler.|\n" +
                    "| [7] Mate Tee    [8] Bretzel|\n" +
                    "| [9] Poutine    [10] Sushi  |\n" +
                    "|[11] Pizza      [12] Churros|\n" +
                    "|[13] Kimchi                 |\n" +
                    "|---------------------------|\n" +
                    "| [0] Exit                   |\n" +
                    "|___________________________|\n" +
                    "|  [Geld: ___ "+ userMoney +"CHF]           |\n" +
                    "|  Auswahl:" +user_choice+": __               |\n" +
                    "|___________________________|\n");
        */



