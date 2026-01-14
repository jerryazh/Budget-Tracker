package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ItemTest {
    private Item item1;

    @BeforeEach
    public void setUp() {
        item1 = new Item("Pepsi", 3, 1);
    }

    @Test
    public void testConstructor() {
        assertEquals("Pepsi", item1.getName());
        assertEquals(3, item1.getPrice());
        assertEquals(1, item1.getNumOfItems());
    }

    @Test
    public void testIncreasePriceByChangeOfItems() {
        item1.increasePriceByChangeNumOfItems(4);
        assertEquals(12, item1.getPrice());
    }

    @Test
    public void testGetPrice() {
        assertEquals(3, item1.getPrice());
    }

    @Test
    public void testGetName() {
        assertEquals("Pepsi", item1.getName());
    }

    @Test
    public void testGetQuantity() {
        assertEquals(1, item1.getNumOfItems());
    }


}