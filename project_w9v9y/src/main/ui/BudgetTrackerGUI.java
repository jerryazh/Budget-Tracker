package ui;

import model.BudgetTracker;
import model.EventLog;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;


public class BudgetTrackerGUI extends JFrame {

    private static final String JSON_STORE = "./data/budgetTracker.json";
    private BudgetTracker tracker;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JButton addItemButton;
    private JButton removeItemButton;
    private JButton viewItemsButton;
    private JButton saveItemsButton;
    private JButton loadItemsButton;
    private JLabel budgetLabel;
    private JLabel imageLabel;
    private JTextArea textItemsArea;

    // EFFECTS: runs the Budget Tracker Frame
    public BudgetTrackerGUI() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        setTitle("BudgetTracker");
        setSize(800, 800);



        addButtonPanel();
        windowClose();

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    private void windowClose() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Events Logged when App started:");
                EventLog events = EventLog.getInstance();
                for (Event event : events) {
                    System.out.println(event.getDescription());
                }
            }
        });
    }

    //EFFECTS: add all the buttons needed towards the BudgetTrackerGUI
    private void addButtonPanel() {
        setLayout(new GridLayout(7,1));
        java.net.URL imgUrl = getClass().getResource("money1.png");
        ImageIcon icon = new ImageIcon(imgUrl);
        imageLabel = new JLabel(icon);
        add(imageLabel);
        addBudgetAmount();
        setAddItemButton();
        setRemoveItemButton();
        setViewItems();
        loadItems();
        saveItems();
    }

    //MODIFIES: this
    //EFFECTS: initializes the budgetTracker from the user input of the initial budget in the JOptionPanel
    private void addBudgetAmount() {
        double resultBudget = Double.parseDouble(JOptionPane.showInputDialog("Budget"));
        tracker = new BudgetTracker(resultBudget);
        budgetLabel = new JLabel("Budget:" + tracker.getBudget());
        budgetLabel.setHorizontalAlignment(JLabel.CENTER);
        budgetLabel.setHorizontalTextPosition(JLabel.CENTER);
        add(budgetLabel);
    }

    // MODIFIES: this
    // EFFECTS: initializes a button and adds the item that is inputted after the button is pressed
    private void setAddItemButton() {
        addItemButton = new JButton("Add Item");
        add(addItemButton);
        addItemButton.addActionListener(e -> {
            String resultName = JOptionPane.showInputDialog("Name");
            double resultPrice = Double.parseDouble(JOptionPane.showInputDialog("Price"));
            int resultQuantity = Integer.parseInt(JOptionPane.showInputDialog("Quantity"));
            tracker.addItem(resultName, resultPrice, resultQuantity);
            updateBudgetLabel();
        });
    }

    // MODIFIES: this
    // EFFECTS: initializes a button and removes the item by typing the name after the button is pressed
    private void setRemoveItemButton() {
        removeItemButton = new JButton("Remove Item");
        add(removeItemButton);
        removeItemButton.addActionListener(e -> {
            String resultName = JOptionPane.showInputDialog("Name");
            tracker.removeItem(resultName);
            updateBudgetLabel();
        });
    }

    // MODIFIES: this
    // EFFECTS: able to see the items that is inputted in another panel after the button is pressed
    private void setViewItems() {
        viewItemsButton = new JButton("View Items");
        add(viewItemsButton);
        viewItemsButton.addActionListener(e -> {
            textItemsArea = new JTextArea(tracker.viewItems());
            JScrollPane scrollPane = new JScrollPane(textItemsArea);
            JOptionPane.showMessageDialog(null, scrollPane, "Items Listed",
                                           JOptionPane.PLAIN_MESSAGE);
        });
    }

    // EFFECTS: saves the budgetTracker to file after the button is pressed
    private void saveItems() {
        saveItemsButton = new JButton("Save Items");
        add(saveItemsButton);
        saveItemsButton.addActionListener(e -> {
            try {
                jsonWriter.open();
                jsonWriter.write(tracker);
                jsonWriter.close();
                JOptionPane.showMessageDialog(null,"Saved "
                                + tracker.getBudget() + " to " + JSON_STORE,
                        "Saved Confirmation", JOptionPane.INFORMATION_MESSAGE);
            } catch (FileNotFoundException er) {
                JOptionPane.showMessageDialog(null, "Unable to write to file: " + JSON_STORE,
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // EFFECTS: loads the budgetTracker to file after the button is pressed
    private void loadItems() {
        loadItemsButton = new JButton("Load Items");
        add(loadItemsButton);
        loadItemsButton.addActionListener(e -> {
            try {
                tracker = jsonReader.read();
                JOptionPane.showMessageDialog(null,
                        "Loaded " + tracker.getBudget() + " from " + JSON_STORE,
                        "Load Confirmation", JOptionPane.INFORMATION_MESSAGE);
                updateBudgetLabel();
            } catch (IOException er) {
                JOptionPane.showMessageDialog(null, "Unable to read from file: " + JSON_STORE,
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    //MODIFIES: this
    // EFFECTS: updates the budget at the main screen
    private void updateBudgetLabel() {
        budgetLabel.setText("Budget:" + tracker.getBudget());
    }

}
