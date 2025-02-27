# Implement

### High-Level 
1. Design Vending Machine
2. Development of the Vending Machine
3. Way of working on the Code
4. Issues Encountered

## 1. Design Vending Machine
Below you will see the output design of the vending machine:

![Visualization DesignOutput Code](https://github.com/Coding-Bz/BLJ2025_AA_Eli_Abi_Luk/blob/main/02_attachments/01_images/AA_Visualization%20Design%20Machien%20Output_realize_implement.png)

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

![Payment Process (Menu.java)](https://github.com/Coding-Bz/BLJ2025_AA_Eli_Abi_Luk/blob/main/02_attachments/01_images/MenuClass_realize%20implement.png)

---

**Change Price and buy Item (Item.java)**
* Calculates a new price based on a percentage change (dynamical adjusts the prices)
* Process:  
  * Convert the percentage into a multiplier: (priceChangePercent / 100) + 1  
  * Multiply the current price by the multiplier to adjust the price
  * Round the result to two decimal places using Math.round(... * 100) / 100

**buyOne()**
* Reduces the quantity of an item after a purchase
Process:  
  * Check if the item is available (quantity > 0)
  * If available, reduce the quantity by 1 
  * If not available, output an error message: "ERROR: You can't buy this item, it is sold out!"  
Called every time a iser buys an item to ensure they can't buy out-of-stock items 

![Change Price and buy Item (Item.java)](https://github.com/Coding-Bz/BLJ2025_AA_Eli_Abi_Luk/blob/main/02_attachments/01_images/ItemClass_realize%20implement.png)

---

**Increase Single Item Price (Admin.java)**
* Prompts the admin to enter the number-> Checks if the input is valid 
* If valid, asks for a percentage value to increase the price
* Applies the new price using changePrice(percentage)
* If the input is invalid, an error message is displayed

![Increase Single Item Price (Admin.java)](https://github.com/Coding-Bz/BLJ2025_AA_Eli_Abi_Luk/blob/main/02_attachments/01_images/admin_realize%20implement.png)


## 3. Way of Working on the Code  
We provided each other feedback on the code when it was checked or when a different perspective was needed to keep track of the code. For example, TODO comments were added in the code, or old code blocks were commented out so that we could still see how the old code was rewritten into the new one, allowing us to compare and ensure nothing was lost.

![CommunicationCode](https://github.com/Coding-Bz/BLJ2025_AA_Eli_Abi_Luk/blob/main/02_attachments/01_images/AA_CommunicationTeam_Code_RealizePhase.png)

## 4. Issues Encountered  
* Issue 1:  
  * Had issues with data types  
  * Couldn't figure out how to convert from String to Integer without causing a crash  
  * Resolved by researching online 

* Issue 2:  
  * Two people were working on the same code 
  * Had difficulty understanding each other's code  
  * In the future we will avoid doing this
 
* Issue 3:
  * When we wanted to switch or merge we had sometimes merge problems
  * In the beginning it was a bit lack of knowledge how to handle it correctly
  * After some time we could handle it with practice
    
![Issue](https://github.com/Coding-Bz/BLJ2025_AA_Eli_Abi_Luk/blob/main/02_attachments/01_images/AA_IssuesEncountered_RealizePhase.png)
