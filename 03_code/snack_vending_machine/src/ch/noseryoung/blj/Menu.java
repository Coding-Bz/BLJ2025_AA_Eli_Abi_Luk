package ch.noseryoung.blj;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void menuLoop() throws InterruptedException {
        // Setup
        double userMoney = 40;
        boolean programStatus = true; // If the programm should still continue running
        ArrayList<Item> vendingMachine = new ArrayList<>();
        addStartingItems(vendingMachine);

        Admin admin = new Admin(scanner);

        welcomeMessage();

        String userInput;
        do {
            System.out.print("Enter Start to begin the program: ");
            userInput = scanner.nextLine();
            if (!userInput.toUpperCase().startsWith("S")) {
                System.out.println("Please enter Start");
            }
            if (userInput.equals("AlphaSigma")) {
                admin.adminMenu(vendingMachine);
                continue;
            }
            if (userInput.equalsIgnoreCase("cancel")) {
                return;
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
                    programStatus = false;
                    return;
                }
                if (userChoice.equals("AlphaSigma")) {
                    admin.adminMenu(vendingMachine);
                    printVendingMachine(vendingMachine);
                }

            } while (!isValidChoice(userChoice, vendingMachine.size()));

            if (stringToInt(userChoice) < 1 || stringToInt(userChoice) > vendingMachine.size()) {
                System.out.println("Please give a number wich exists actually");
                continue;
            }

            // TODO: Error-Message if number of non existing item and if random text was entered 👌
            if (userChoice.equalsIgnoreCase("cancel")) {
                return;
            }

            // Admin function if secret Key was entered // TODO: Add real secret key

            if (userChoice.equals("AlphaSigma")) {
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
                System.out.print("Your item cost's "+chosenItem.getPrice());
                System.out.println();
                System.out.println("Your money status: \u001B[33m" + Math.round(userMoney * 100) / 100.00 + "\u001B[0m Franks");
                System.out.print("Please insert your money (example: 6.5): ");
                moneyInput = scanner.nextLine();
                if (moneyInput.equalsIgnoreCase("Cancel")){
                    return;
                    }



                else if (stringToDouble(moneyInput) > userMoney) {

                    do {
                        System.out.println("You don't have that much money");
                        System.out.println("try again");
                        moneyInput = scanner.nextLine();
                    } while (stringToDouble(moneyInput) > userMoney);


                }
                moneyInserted = Math.round(stringToDouble(moneyInput) * 100) / 100.00;


                if (moneyInserted < chosenItem.getPrice()) {
                    System.out.println("You need to insert enough money");
                }
            } while (moneyInserted < chosenItem.getPrice());

            double change = moneyInserted / 100.00 * 100.00 - chosenItem.getPrice();

            if (change > 0) {
                System.out.println("You get " + Math.round(change * 100) / 100.00 + " Franks back.");//Rückgeldberechnung
            }

            userMoney -= chosenItem.getPrice();       //actualize
            chosenItem.buyOne();

            System.out.println("\u001B[32m\nYour purchase was successful!\u001B[0m");
            System.out.println("You have bought " + chosenItem.getName() + " for " + chosenItem.getPrice() + " Franks");
            System.out.println("You have \u001B[33m" + Math.round(userMoney * 100) / 100.00 + "\u001B[0m Franks left");
            Thread.sleep(5000);
        }

        System.out.println("Thank you for considering us:))");
    }


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


    private static void addStartingItems(ArrayList<Item> vending_machine) {

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


    private static void welcomeMessage() {
        System.out.println("\nWELCOME TO THE SNACK VENDING MACHINE!");
        System.out.println("|//////////////////////////////////////////////////////////////////////////////////////|");
        System.out.println("This is a vending machine developed for Alphas please choose your item and enjoy it with a smile");
    }

    //complement for visual understanding abigail (proviso)
    private static void printVendingMachine(ArrayList<Item> vending_machine) {
        System.out.println("Available Items:");
        System.out.print(" ___________________________________\n" +
                "|           SNACK AUTOMAT          |\n" +
                "|                                  |\n" +
                "|----------------------------------|\n");


        for (int i = 0; i < vending_machine.size(); i++) {
            Item item = vending_machine.get(i);
            System.out.println("| [" + (i + 1) + "] " + item.getName() + " - " + item.getPrice() + " Franks");
        }
        System.out.println(

                "|__________________________________|\n" +
                        "|                                  |\n" +
                        "|__________________________________|\n");
    }
}




