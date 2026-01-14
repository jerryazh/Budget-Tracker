package persistence;

import model.BudgetTracker;
import model.Item;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            BudgetTracker bt = new BudgetTracker(100);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            BudgetTracker bt = new BudgetTracker(100);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyBudgetTracker.json");
            writer.open();
            writer.write(bt);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyBudgetTracker.json");
            bt = reader.read();
            assertEquals(100, bt.getBudget());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            BudgetTracker bt = new BudgetTracker(100);
            bt.addItem("Rice", 4, 2);
            bt.addItem("Milk", 6, 1);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralBudgetTracker.json");
            writer.open();
            writer.write(bt);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralBudgetTracker.json");
            bt = reader.read();
            assertEquals(86, bt.getBudget());
            List<Item> items = bt.getListOfItems();
            assertEquals(2, items.size());
            checkItem("Rice", 8, 1, items.get(0));
            checkItem("Milk", 6, 1, items.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


}
