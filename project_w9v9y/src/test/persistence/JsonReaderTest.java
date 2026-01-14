package persistence;

import model.BudgetTracker;
import model.Item;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            BudgetTracker bt = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyBudgetTracker.json");
        try {
            BudgetTracker bt = reader.read();
            assertEquals(100 , bt.getBudget());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralBudgetTracker.json");
        try {
            BudgetTracker bt = reader.read();
            assertEquals(100, bt.getBudget());
            List<Item> Items = bt.getListOfItems();
            checkItem("Rice", 4, 1, Items.get(0));
            checkItem("Milk", 6, 1, Items.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }






}
