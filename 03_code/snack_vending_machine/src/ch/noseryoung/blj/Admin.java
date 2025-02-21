package ch.noseryoung.blj;
import java.util.ArrayList;
import java.util.List;
import java.security.Key;


//
public class Admin {
    private static final String Secret Key = "1234";
    private List<String> logEntries;
    public Admin() {
        logEntries = new ArrayList<>();
        this.items = new ArrayList<>();
    }
}

//
public boolean restockItem(Item item, int amount) {
    if (item == null || amount <= 0) {
        logAction("Restock failed: Invalid input");
        return false;
        }

    item.setQuantity(item.getQuantity() + amount);
    logAction("Item " + item.getName() + " was " + amount + " restocked");
    return true;
    }

//
public boolean priceIncreaseAll(double percentageIncrease) {
    if (percentageIncrease <= 0) {
        logAction("Price increase failed: Invalid percentage");
        return false;
        }
    for (Item item : items) {
        double newPrice = item.getPrice() * (1 + percentageIncrease / 100);
        item.setPrice(newPrice);
        }
    logAction("All prices were increased by " + percentageIncrease + "% increased");
    return true;
    }

//




