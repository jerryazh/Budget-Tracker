package ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new BudgetTrackerGUI();
        } catch (FileNotFoundException er) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
//try {
//      new BudgetTrackerApp();
//        } catch (FileNotFoundException e) {
//        System.out.println("Unable to run application: file not found");
//        }