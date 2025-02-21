package ch.noseryoung.blj;

public class Item {

    public String name;
    public String description;
    public double price;
    public int maxQuantity;
    public int quantity;

    public Item(String name, String description, double price, String maxQuantity, int quantity) {
        this.name = name;
        this.price = price;
        this.description = description;
        if (maxQuantity.equals("default")) {
            this.maxQuantity = 64;
        } else if (maxQuantity.equals("limited")) {
            this.maxQuantity = 10;
        } else {
            System.err.println("ERROR: Invalid max quantity! Value was set do default!");
            this.maxQuantity = 64;
        }
        this.quantity = quantity;
    }
}
