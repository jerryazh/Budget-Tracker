package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents the Item having a Name, price, and the amount of item
public class Item implements Writable {
    private String name;
    private double price;
    private int numOfItems;

    //EFFECTS; constructs an Item with given name and
    //         price with the number of item at 1
    public Item(String name, double price, int numOfItems) {
        this.name = name;
        this.price = price;
        this.numOfItems = 1;
    }


    //Modifies: this
    //Effects; multiplies the price by the quantity of int num.
    public void increasePriceByChangeNumOfItems(int num) {
        numOfItems = num;
        price = price * num;
    }

    //Effects; returns the name of the item
    public String getName() {
        return name;
    }

    //Effects; returns the price of the item
    public double getPrice() {
        return price;
    }

    //Effects; returns the quantity of the item
    public int getNumOfItems() {
        return numOfItems;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("price", price);
        json.put("quantity", numOfItems);
        return json;
    }

}
