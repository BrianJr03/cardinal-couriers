package edu.bsu.cs222.finalProject;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Inventory {

    private final ArrayList<Item> items;

    public Inventory(ArrayList<Item> items) {
        this.items = items;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public static ArrayList<Item> createArrayListOfItems(ArrayList<String[]> itemData) {
        ArrayList<Item> itemsList = new ArrayList<>();
        for ( String[] itemDatum : itemData )
        {
            Item newItem = new Item( itemDatum[ 0 ] , itemDatum[ 1 ] );
            itemsList.add( newItem );
        }
        return itemsList;
    }

    public static ArrayList<String[]> collectItemsFromResources() throws IOException {
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
}