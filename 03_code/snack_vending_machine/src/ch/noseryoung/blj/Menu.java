package ch.noseryoung.blj;

import java.util.ArrayList;
import java.util.Scanner;



public class Menu {

    private static Scanner scanner = new Scanner(System.in);



    // TODO: Maybe do more Outsourcing in menuLoop
    // TODO: Maybe make design better
    public static void menuLoop() {

        // Setup
        double userMoney = 20;
        boolean programStatus = true; // If the programm should still continue running
        ArrayList<Item> vendingMachine = new ArrayList<Item>();
        addStartingItems(vendingMachine);

        welcomeMessage();

        String userInput;
        do {
            System.out.print("Enter Start to begin the program: ");
            userInput = scanner.nextLine();
        } while (!userInput.equalsIgnoreCase("start"));

        while (programStatus) {

            printVendingMachine(vendingMachine);

            // User Item selection process
            String userChoice;
            do {
                System.out.print("\nPlease enter the product number you want to choose: ");
                userChoice = scanner.nextLine();
                if (userChoice.equalsIgnoreCase("cancel") || userChoice.equalsIgnoreCase("secret Key")) {
                    break;
                }
                // TODO: Error-Message if number of non existing item and if random text was entered
            } while (stringToInt(userChoice) < 1 || stringToInt(userChoice) > vendingMachine.size());

            // Stop vending machine if cancel was entered
            if (userChoice.equalsIgnoreCase("cancel")) {
                programStatus = false;
                continue;
            }
            // Admin function if secret Key was entered
            if (userChoice.equalsIgnoreCase("secret Key")) { // TODO: Add real secret key
                // TODO: The function from admin is then called here
                continue;
            }

            System.out.println();
            Item chosenItem = vendingMachine.get(Integer.parseInt(userChoice) - 1);
            chosenItem.printItem();
            System.out.println();

            // User Input if want it wants to buy
            String moneyInput;
            do {
                System.out.println("Your money status: \u001B[33m" + userMoney + "\u001B[0m Franks");
                System.out.print("Please put your money in the snack machine: ");
                moneyInput = scanner.nextLine();
                if (userChoice.equalsIgnoreCase("cancel")) {
                    break;
                }
                // TODO: Error-Message if item was 0 or less and if random text was entered
            } while (stringToDouble(moneyInput) < 0.01);

            // Transaction
            // TODO: Make, that the program asks you to put in more money, if you haven't put in enough
            userMoney -= chosenItem.getPrice();
            chosenItem.buyOne();

            System.out.println("\u001B[32m\nYour purchase was successful!\u001B[0m");
            System.out.println("You have bought " + chosenItem.getName() + " for " + chosenItem.getPrice() + " Franks");
            System.out.println("You have \u001B[33m" + userMoney + "\u001B[0m Franks left");
        }

        // TODO: Goodbye message
    }


    // |----- Outsourcing -----|

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
        // TODO: Explanation
    }


    private static void printVendingMachine (ArrayList < Item > vending_machine) {
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
    }



    // |----- Functional Methods -----|

    private static int stringToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            // TODO: Make a error message
            return 0;
        }
    }

    private static double stringToDouble(String input) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            // TODO: Make a error message
            return 0;
        }
    }
}
