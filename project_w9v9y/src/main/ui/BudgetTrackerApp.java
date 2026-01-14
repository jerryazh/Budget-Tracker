package ui;

import model.BudgetTracker;
import persistence.JsonReader;
import persistence.JsonWriter;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class BudgetTrackerApp {
    private static final String JSON_STORE = "./data/budgetTracker.json";
    private BudgetTracker tracker;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: runs the Budget Tracker application
    public BudgetTrackerApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runTracker();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runTracker() {
        boolean keepGoing = true;
        String command = null;


        init();

        System.out.print("Enter budget: ");
        double money = input.nextDouble();
        tracker = new BudgetTracker(money);


        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            doAddItem();
        } else if (command.equals("r")) {
            doRemoveItem();
        } else if (command.equals("v")) {
            doViewItem();
        } else if (command.equals("s")) {
            saveBudgetTracker();
        } else if (command.equals("l")) {
            loadBudgetTracker();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes input
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> addItem");
        System.out.println("\tr -> removeItem");
        System.out.println("\tv -> viewItem");
        System.out.println("\ts -> save BudgetTracker to file");
        System.out.println("\tl -> load BudgetTracker from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: conducts adding an Item
    private void doAddItem() {
        System.out.print("Enter Item name: ");
        String name = input.next();
        System.out.print("Enter Item price: ");
        double price = input.nextDouble();
        System.out.print("Enter Item quantity: ");
        int quantity = input.nextInt();

        tracker.addItem(name, price, quantity);

        printBudget(tracker);
    }

    // EFFECTS: saves the budgetTracker to file
    private void saveBudgetTracker() {
        try {
            jsonWriter.open();
            jsonWriter.write(tracker);
            jsonWriter.close();
            System.out.println("Saved " + tracker.getBudget() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads budgetTracker from file
    private void loadBudgetTracker() {
        try {
            tracker = jsonReader.read();
            System.out.println("Loaded " + tracker.getBudget() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: conducts removing the item
    private void doRemoveItem() {
        System.out.print("Enter name of Item to remove: ");
        String name = input.next();

        tracker.removeItem(name);

        printBudget(tracker);
    }

    // MODIFIES: this
    // EFFECTS: conducts viewing the items
    private void doViewItem() {
        System.out.print("\nItems: ");

        System.out.println(tracker.viewItems());

        printBudget(tracker);
    }




    // EFFECTS: prints the money in the budget
    private void printBudget(BudgetTracker selected) {
        System.out.printf("Budget: $%.2f\n", selected.getBudget());
    }




}
