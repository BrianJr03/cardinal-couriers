package edu.bsu.cs222.finalProject;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Item {

    private String name;
    private String price;

    public Item(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public ArrayList<String[]> collectItemsFromResources() throws IOException {
        JsonParser parser = new JsonParser();
        FileReader reader = new FileReader( "src/main/resources/items.json" );

        ArrayList<String[]> itemsList = new ArrayList<>();

        JsonArray inventory = parser.parse(reader).getAsJsonArray();
        reader.close();

        for (JsonElement item : inventory)
        {
            String[] singleItem = new String[2];
            singleItem[0] = item.getAsJsonObject().get("name").toString();
            singleItem[1] = item.getAsJsonObject().get("price").getAsString();
            itemsList.add(singleItem);
        }
        return itemsList;
    }

    public ArrayList<Item> createArrayListOfItems(ArrayList<String[]> itemData) {
        ArrayList<Item> itemsList = new ArrayList<>();
        for (int i = 0; i < itemData.size(); i++) {
            Item newItem = new Item(itemData.get(i)[0], itemData.get(i)[1]);
            itemsList.add(newItem);
        }
        return itemsList;
    }
}
