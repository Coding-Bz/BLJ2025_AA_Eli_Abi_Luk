package ch.noseryoung.blj;

import java.util.ArrayList;
import java.util.Scanner;



//By Elif
public class  money_managment {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double money_talks = 20;
        System.out.println("Your balance is 20 CHF");


        String name="";
        String description = "";
        String product_id="";
        double price = 0;



        new Item(name,description, product_id,price); {}
        ArrayList<Item> vending_machine = new ArrayList<Item>();
        vending_machine.add(new Item("FocusWater", "Vitamin water from Switzerland", "1", 2.7));
        vending_machine.add(new Item("Döner", "A delicious food from Türkiye", "2", 13.5 ));
        vending_machine.add(new Item("Baklava", "Sweet desert from Türkiye", "3", 3.5));
        vending_machine.add(new Item("Rivella", "Popular Swiss soft drink", "5", 2.5));
        vending_machine.add(new Item("Bratwurst", "Swiss grilled sausage", "6", 7.0));
        vending_machine.add(new Item("Toblerone", "Famous Swiss chocolate", "7", 4.0));
        vending_machine.add(new Item("Mate Tee", "Traditional drink from Argentina", "8", 3.0));
        vending_machine.add(new Item("Bretzel", "German twisted bread", "9", 2.2));
        vending_machine.add(new Item("Poutine", "Canadian fries with gravy and cheese curds", "10", 6.5));
        vending_machine.add(new Item("Sushi", "Japanese rice and fish rolls", "11", 12.0));
        vending_machine.add(new Item("Pizza Margherita", "Classic Italian pizza", "12", 10.5));
        vending_machine.add(new Item("Churros", "Spanish fried dough with sugar", "13", 3.8));
        vending_machine.add(new Item("Kimchi", "Korean fermented cabbage", "14", 5.0));


        System.out.println("___________________________\n" +
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
                "|  [Geld:"+money_talks+" ___ CHF]           |\n" +
                "|  Auswahl: __               |\n" +
                "|___________________________|\n");
        int user_chosses;
        do {




            System.out.println("Please enter the product you want to choose");
            user_chosses = scanner.nextInt();
            if (user_chosses > 20){
                System.out.println("Please anter a number between 1-20");
            }



            money_talks -= vending_machine.get(user_chosses - 1).getPrice;

                case 0:
                    System.out.println("Program cancelled");
                    break;
                default:
                    System.out.println("Please enter a number between 1-13");
            }





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
                    "|  [Geld: ___ "+money_talks+"CHF]           |\n" +
                    "|  Auswahl:" +user_chosses+": __               |\n" +
                    "|___________________________|\n");

        } while (user_chosses !=0);





    for (Item item: vending_machine){

        System.out.println(item);

    }
    }}