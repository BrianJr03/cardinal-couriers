package edu.bsu.cs222.finalProject;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class DeliveryMap {

    public static JsonObject collectJsonObjectFromGoogle(String address) throws IOException {
        URLConnection connection =
    }
    public static URLConnection connectToGoogleMaps(String address) throws IOException {
        address = formatAddressForGoogle(address);
        URL url;
        url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?origins="+address+
                "&destinations=2000+W+University+Ave+Muncie+IN&key=AIzaSyDPvnx2-pANJxeTBNFyMQERpp2uRFznfP0");


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
