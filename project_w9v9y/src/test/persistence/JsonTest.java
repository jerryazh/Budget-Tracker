package persistence;

import model.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkItem(String name, double price, int quantity, Item item) {
        assertEquals(name, item.getName());
        assertEquals(price, item.getPrice());
        assertEquals(quantity, item.getNumOfItems());
    }
}
