package ch.noseryoung.blj;



public class Item {

    private final String name;
    private final String description;
    private final String rarity;
    private double price;
    private final int maxQuantity;
    private int quantity;

    public Item(String name, String description, String rarity, double price) {
        this.name = name;
        this.price = price;
        this.description = description;
        switch (rarity) {
            case "default":
                this.rarity = "default";
                this.maxQuantity = 10;
                break;
            case "limited":
                this.rarity = "limited";
                this.maxQuantity = 5;
                break;
            default:
                System.out.println("\u001B[31mERROR: Invalid rarity! Value was set do default!\u001B[0m");
                this.rarity = "default";
                this.maxQuantity = 10;
                break;
        }
        this.quantity = maxQuantity;
    }



    // |----- getters -----|

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public int getQuantity() {
        return this.quantity;
    }



    // |----- setters -----|

    public void changePrice(double priceChangePercent) {
        this.price = (double) Math.round((price * ((priceChangePercent / 100) + 1)) * 100) / 100 ;
        // If you want to increase the price by 20% than you musst enter 20 (not 1.2)
    }

    public void buyOne() {
        if (this.quantity > 0) {
            this.quantity--;
        } else {
            System.out.println("\u001B[31mERROR: You can't buy this item, it is sold out!\u001B[0m");
        }
    }

    public void restock() {
        if (this.rarity.equals("default")) {
            this.quantity = maxQuantity;
        }
        // There is no error message, if you try restocking a limited item
    }



    // |----- print -----|

    public void printItem() {

        System.out.println(this.name.toUpperCase() + "\n---\n" + this.description + "\nprice: " + this.price );

        if (this.rarity.equals("limited")) {
            System.out.println("Item is limited. Buy while it's still available!");
        }

        if (this.quantity > 3) {
            System.out.println("\u001B[32mItem is available\u001B[0m");
        } else if (this.quantity > 0) {
            System.out.println("\u001B[33mItem is almost sold out\u001B[0m");
        } else {
            System.out.println("\u001B[31mItem is sold out\u001B[0m");
        }
    }
}
