package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BudgetTracker implements Writable {
    private double initialBudget;
    private ArrayList<Item> listOfItems;

    //EFFECTS; creates a Budget tracker with the Initial budget and an empty list
    public BudgetTracker(double initialBudget) {
        this.initialBudget = initialBudget;
        listOfItems = new ArrayList<Item>();
    }

    //REQUIRES; It doesn't Contain the Same Item with the same name and price
    //Modifies: this
    //Effects; adds the item within the list and decreases the budget by the price
    public void addItem(String name, double price, int quantity) {
        Item newItem = new Item(name, price, quantity);
        newItem.increasePriceByChangeNumOfItems(quantity);
        initialBudget -= newItem.getPrice();
        listOfItems.add(newItem);
        EventLog.getInstance().logEvent(new Event(name + " of price: " + price + ", and Quantity: "
                 + quantity + " added to Budget Tracker."));
    }


    //REQUIRES; It contains the items that needs to be removed
    //Modifies: this
    //Effects; removes the item within the list and increases the budget by the price
    public ArrayList<Item> removeItem(String name) {
        for (Item item : listOfItems) {
            String check = item.getName();
            if (check.equals(name)) {
                initialBudget += item.getPrice();
                item.increasePriceByChangeNumOfItems(item.getNumOfItems());
                listOfItems.remove(item);
                EventLog.getInstance().logEvent(new Event(name + " removed from Budget Tracker."));
                return listOfItems;
            }
        }
        return listOfItems;
    }

    //Modifies: this
    //Effects; displays all the items as a string within the list with each item getting a new line
    public String viewItems() {
        String items = "";
        for (int i = 0; i < listOfItems.size(); i++) {
            Item item = listOfItems.get(i);
            items += "\n" + item.getName() + "," + item.getPrice() + "," + item.getNumOfItems();
        }
        return items;
    }

    //EFFECTS; returns a listOfItems
    public ArrayList<Item> getListOfItems() {
        return listOfItems;
    }

    //EFFECTS; returns the inital Budget
    public double getBudget() {
        return initialBudget;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("budget", initialBudget);
        json.put("items", listOfItemsToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray listOfItemsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Item t : listOfItems) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

    //REQUIRES; It doesn't Contain the Same Item with the same name and price
    //Modifies: this
    //Effects; adds the item within the list without changing price by quantity
    public void addItemInJson(String name, double price, int quantity) {
        Item newItem = new Item(name, price, quantity);
        listOfItems.add(newItem);
    }


}
