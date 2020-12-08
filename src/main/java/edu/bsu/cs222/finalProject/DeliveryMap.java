package edu.bsu.cs222.finalProject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class DeliveryMap {

    public static Double findDistanceFromBSU(JsonObject jsonData) {
        if (jsonData.getAsJsonObject().get("origin_addresses") == null) {
            return null;
        }
        String distance = jsonData.getAsJsonArray("rows").get(0).getAsJsonObject().get("elements")
                .getAsJsonArray().get(0).getAsJsonObject().get("distance").getAsJsonObject().get("text").toString();
        String[] distanceInfo = distance.split(" ");
        return Double.parseDouble(distanceInfo[0].replaceFirst("\"", ""));
    }

    public static JsonObject collectJsonObjectFromGoogle(String address, String city, String state) throws IOException {
        URLConnection connection = connectToGoogleMaps(address, city, state);
        return readJsonDataFrom(connection);
    }

    public static URLConnection connectToGoogleMaps(String address,String city,String state) throws IOException {
        address = formatAddressForGoogle(address,city,state);
        URL url;
        url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + address +
                "&destinations=2000+W+University+Ave+Muncie+IN&key=AIzaSyDPvnx2-pANJxeTBNFyMQERpp2uRFznfP0");
        URLConnection connection = url.openConnection();
        connection.connect();
        return connection;
    }

    public static JsonObject readJsonDataFrom(URLConnection connection) throws IOException {
        StringBuilder jsonStringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String jsonDataString;
        while ((jsonDataString = bufferedReader.readLine()) != null) {
            jsonStringBuilder.append(jsonDataString).append("\n");
        }
        String websiteInfo = jsonStringBuilder.toString().trim();
        return new Gson().fromJson(websiteInfo, JsonObject.class);
    }

    public static String formatAddressForGoogle(String address, String city, String state){
        StringBuilder formattedAddress = new StringBuilder();
        String[]addressElements =  address.split(" ");
        for (String addressElement : addressElements) {
            formattedAddress.append(addressElement).append("+");
        }
        formattedAddress.append(city).append("+");
        formattedAddress.append(state);
        return formattedAddress.toString();
    }
}
