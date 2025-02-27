package ch.noseryoung.blj;

import java.util.ArrayList;
import java.util.Scanner;

public class Admin {
    private static final String SecretKey= "AlphaSigma";
    private final Scanner scanner;

    // ---getter---
    public String getSecretKey() {
        return SecretKey;
    }
// --------

    public Admin(Scanner scanner) {
        this.scanner = scanner;
    }

    public void adminMenu(ArrayList<Item> vendingMachine) {
        System.out.println("\n/// ADMIN MENU ///");
        System.out.println("1. Restock Items");
        System.out.println("2. Increase All Prices");
        System.out.println("3. Increase Single Item Price");
        System.out.println("4. Show Stock Status");
        System.out.println("5. Exit Admin Menu");

        String choice;
        do {
            System.out.print("\nEnter your choice (1-5): ");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    restockItems(vendingMachine);
                    break;
                case "2":
                    increaseAllPrices(vendingMachine);
                    break;
                case "3":
                    increaseSinglePrice(vendingMachine);
                    break;
                case "4":
                    showStockStatus(vendingMachine);
                    break;
                case "5":
                    System.out.println("Admin menu...");
                    break;
                default:
                    System.out.println("\u001B[31 Wrong Input\u001B[0m");
            }
        } while (!choice.equals("5"));
    }

    private void restockItems(ArrayList<Item> vendingMachine) {
        System.out.println("\n/// RESTOCKING ITEMS ///");
        for (Item item : vendingMachine) {
            if (item.getQuantity() < 3) {
                System.out.println("Restocking " + item.getName() + "...");
                item.restock();
            }
        }
        System.out.println("\u001B[32mAll items have been restocked!\u001B[0m");
    }

    private void increaseAllPrices(ArrayList<Item> vendingMachine) {
        System.out.print("\nEnter price increase percentage (10 for 10%): ");
        try {
            double percentage = Double.parseDouble(scanner.nextLine());
            for (Item item : vendingMachine) {
                item.changePrice(percentage);
            }
            System.out.println("\u001B[32mAll prices have been increased by " + percentage + "%\u001B[0m");
        } catch (NumberFormatException e) {
            System.out.println("\u001B[31mInvalid input! Please enter a number.\u001B[0m");
        }
    }

    private void increaseSinglePrice(ArrayList<Item> vendingMachine) {
        System.out.println("\n/// INCREASE SINGLE ITEM PRICE ///");
        for (int i = 0; i < vendingMachine.size(); i++) {
            System.out.println((i + 1) + ". " + vendingMachine.get(i).getName());
        }

        System.out.print("\nEnter item number: ");
        try {
            int itemNumber = Integer.parseInt(scanner.nextLine()) - 1;
            if (itemNumber >= 0 && itemNumber < vendingMachine.size()) {
                System.out.print("Enter price increase percentage (10 for 10%): ");
                double percentage = Double.parseDouble(scanner.nextLine());
                vendingMachine.get(itemNumber).changePrice(percentage);
                System.out.println("\u001B[32mPrice updated.\u001B[0m");
            } else {
                System.out.println("\u001B[31mInvalid item number.\u001B[0m");
            }
        } catch (NumberFormatException e) {
            System.out.println("\u001B[31mWrong Input. Please enter a number.\u001B[0m");
        }
    }

    private void showStockStatus(ArrayList<Item> vendingMachine) {
        System.out.println("\n/// STOCK STATUS ///");
        for (Item item : vendingMachine) {
            System.out.println(item.getName() + ": " + item.getQuantity() + " units - Price: " + item.getPrice() + " CHF");
        }
    }
}
