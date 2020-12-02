package edu.bsu.cs222.finalProject;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Inventory {

    private final ArrayList<Item> items;

    public Inventory(ArrayList<Item> items)
    { this.items = items; }

    public ArrayList<Item> getItems()
    { return items; }

    public static JsonArray getInventory(String storeName) throws IOException {
        String path;
        switch ( storeName ) {
            default -> path = "src/main/resources/storeItems/Walmart_Items.json";
            case "kroger" -> path = "src/main/resources/storeItems/Kroger_Items.json";
            case "target" -> path = "src/main/resources/storeItems/Target_Items.json";
        }

        JsonParser parser = new JsonParser();
        FileReader reader = new FileReader(path);
        return parser.parse(reader).getAsJsonArray();
    }

    public static ArrayList<Item> collectItemsAsArrayList(String storeName) throws IOException {
        JsonArray inventoryAsJsonArray = getInventory(storeName);
        ArrayList<Item> itemsList = new ArrayList<>();
        for (JsonElement item : inventoryAsJsonArray) {
            itemsList.add(new Item(item.getAsJsonObject().get("name").toString(), item.getAsJsonObject().get("price").getAsDouble()));
        }
        return itemsList;
    }
}