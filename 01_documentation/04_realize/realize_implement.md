# Implement

### High-Level 
1. Visualization Vending Machine
2. Development of the Vending Machine
3. Way of working on the Code
4. Issues Encountered

## 1. Visualization Vending Machine
Below you will see the output design of the vending machine:

![Visualization DesignOutput Code]()

## 2. Development of the Vending Machine
We have divided it into three classes and each person has taken over one class. Following you will see three examples of codes from each class:

**Payment Process (Menu.java)**
* The system displays the item price and the user's current balance
* If the user enters "Cancel", the transaction is aborted
* If the entered amount is greater than the available balance, the user is asked to re-enter the amount-> else a warning will appear and must retry
* Once a valid amount is inserted:
  * If there is change, it is calculated and displayed
  * The item is deducted from the vending machine stock
  * The user's balance is updated
* The program waits 3.5seconds before continuing

![Payment Process (Menu.java)]()

**Change Price and buy Item (Item.java)**
changePrice (double priceChangePercent)
* Calculates a new price based on a percentage change (dynamical adjusts the prices)
Process:  
     1. Convert the percentage into a multiplier: (priceChangePercent / 100) + 1  
     2. Multiply the current price by the multiplier to adjust the price
     3. Round the result to two decimal places using Math.round(... * 100) / 100

**buyOne()**
Reduces the quantity of an item after a purchase
Process:  
     1. Check if the item is available (quantity > 0)
     2. If available, reduce the quantity by 1 
     3. If not available, output an error message: "ERROR: You can't buy this item, it is sold out!"  
Called every time a iser buys an item to ensure they can't buy out-of-stock items 

![Change Price and buy Item (Item.java)]()

**Increase Single Item Price (Admin.java)**
* Prompts the admin to enter the number-> Checks if the input is valid 
* If valid, asks for a percentage value to increase the price
* Applies the new price using changePrice(percentage)
* If the input is invalid, an error message is displayed

![Increase Single Item Price (Admin.java)]()


## 3. Way of Working on the Code  
We provided each other feedback on the code when it was checked or when a different perspective was needed to keep track of the code. For example, TODO comments were added in the code, or old code blocks were commented out so that we could still see how the old code was rewritten into the new one, allowing us to compare and ensure nothing was lost.

## 4. Issues Encountered  
* Issue 1:  
  * Had issues with data types  
  * Couldn't figure out how to convert from String to Integer without causing a crash  
  * Resolved by researching online 

* Issue 2:  
  * Two people were working on the same code 
  * Had difficulty understanding each other's code  
  * In the future we will avoid doing this  