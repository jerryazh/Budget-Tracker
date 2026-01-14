package persistence;

import model.BudgetTracker;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public BudgetTracker read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBudgetTracker(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses BudgetTracker from JSON object and returns it
    private BudgetTracker parseBudgetTracker(JSONObject jsonObject) {
        int budget = jsonObject.getInt("budget");
        BudgetTracker bt = new BudgetTracker(budget);
        addItems(bt, jsonObject);
        return bt;
    }

    // MODIFIES: bt
    // EFFECTS: parses items from JSON object and adds them to BudgetTracker
    private void addItems(BudgetTracker bt, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("items");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addItem(bt, nextThingy);
        }
    }

    // MODIFIES: bt
    // EFFECTS: parses item from JSON object and adds it to BudgetTracker
    private void addItem(BudgetTracker bt, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double price = jsonObject.getDouble("price");
        int quantity = jsonObject.getInt("quantity");
        bt.addItemInJson(name, price, quantity);
    }
}
