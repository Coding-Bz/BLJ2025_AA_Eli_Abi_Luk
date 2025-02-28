package ch.noseryoung.blj;



public class Item {

    private final String name;
    private final String rarity;
    private double price;
    private final int maxQuantity;
    private int quantity = 0;

    public Item(String name, String rarity, double price) {
        this.name = name;
        this.price = price;
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
}
