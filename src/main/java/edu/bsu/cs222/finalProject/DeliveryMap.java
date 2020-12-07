package edu.bsu.cs222.finalProject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class DeliveryMap {

    public static Double findDistanceFromBSU(JsonObject jsonData) {
        if (jsonData.getAsJsonObject().get("origin_addresses") == null) {
            return null;
        }
        String distance = jsonData.getAsJsonArray("rows").get(0).getAsJsonObject().get("elements").getAsJsonArray().get(0).getAsJsonObject().get("distance").getAsJsonObject().get("text").toString();
        if (distance.contains("km")) {
            String[] distanceInfo = distance.split(" ");
            return Double.parseDouble(distanceInfo[0].replaceFirst("\"", ""));
        } else {
            return null;
        }
    }

    public static JsonObject collectJsonObjectFromGoogle(String address) {
            URLConnection connection = connectToGoogleMaps(address);
            return readJsonDataFrom(connection);
    }

    public static URLConnection connectToGoogleMaps(String address) {
        try {
            address = formatAddressForGoogle(address);
            URL url;
            url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + address +
                    "&destinations=2000+W+University+Ave+Muncie+IN&key=AIzaSyDPvnx2-pANJxeTBNFyMQERpp2uRFznfP0");
            URLConnection connection = url.openConnection();
            connection.connect();
            return connection;
        }
        catch (IOException e){
                return null;
            }
        }

    public static JsonObject readJsonDataFrom(URLConnection connection) {
        try {
            StringBuilder jsonStringBuilder = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String jsonDataString;
            while ((jsonDataString = bufferedReader.readLine()) != null) {
                jsonStringBuilder.append(jsonDataString).append("\n");
            }
            String websiteInfo = jsonStringBuilder.toString().trim();
            return new Gson().fromJson(websiteInfo, JsonObject.class);
        } catch (IOException e) {
            return null;
        }
    }

    public static String formatAddressForGoogle(String address){
        StringBuilder formattedAddress = new StringBuilder();
        String[]addressElements =  address.split(" ");
        for (String addressElement : addressElements) {
            formattedAddress.append(addressElement).append("+");
        }
        formattedAddress.append("Muncie+IN");
        return formattedAddress.toString();
    }
}
