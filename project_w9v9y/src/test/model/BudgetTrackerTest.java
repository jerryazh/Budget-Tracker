package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.lang.reflect.Array;

import static org.junit.jupiter.api.Assertions.*;

public class BudgetTrackerTest {

    private BudgetTracker budgetTracker;
    private ArrayList<Item> listOfItems;



    @BeforeEach
    public void setUp() {
        budgetTracker = new BudgetTracker(100);
        listOfItems = new ArrayList<Item>();

    }

    @Test
    public void testConstructor() {
        assertEquals(100, budgetTracker.getBudget());
    }

    @Test
    public void testAddOneItem() {
        budgetTracker.addItem("Pepsi", 3, 1);
        assertEquals(97, budgetTracker.getBudget());
    }

    @Test
    public void testAddMoreItem() {
        budgetTracker.addItem("Pepsi", 3, 1);
        budgetTracker.addItem("Paper", 5, 1);
        budgetTracker.addItem("Bread", 6, 1);
        assertEquals(86, budgetTracker.getBudget());
    }

    @Test
    public void testRemoveOneItem() {
        budgetTracker.addItem("Pepsi", 3, 1);
        budgetTracker.removeItem("Pepsi");
        assertEquals(100, budgetTracker.getBudget());
        assertEquals(listOfItems, budgetTracker.getListOfItems());
    }
    @Test
    public void testRemoveNoItem() {
        budgetTracker.addItem("Pepsi", 3, 1);
        budgetTracker.removeItem("Coke");
        assertEquals(97, budgetTracker.getBudget());
    }

    @Test
    public void testRemoveMoreThanOneItem() {
        budgetTracker.addItem("Pepsi", 3, 1);
        budgetTracker.addItem("Paper", 5, 1);
        budgetTracker.addItem("Bread", 6, 1);
        budgetTracker.removeItem("Pepsi");
        budgetTracker.removeItem("Bread");
        assertEquals(95, budgetTracker.getBudget());
    }

    @Test
    public void testViewItem() {
        budgetTracker.addItem("Pepsi", 3, 1);
        assertEquals("\nPepsi,3.0,1", budgetTracker.viewItems());
    }

    @Test
    public void testViewMoreThanOneItem() {
        budgetTracker.addItem("Pepsi", 3, 1);
        budgetTracker.addItem("Paper", 5, 1);
        assertEquals("\nPepsi,3.0,1\nPaper,5.0,1", budgetTracker.viewItems());
    }

    @Test
    public void testGetListOfItem() {
        assertEquals(listOfItems, budgetTracker.getListOfItems());
    }

    @Test
    public void testGetBudget() {
        assertEquals(100, budgetTracker.getBudget());
    }

}
