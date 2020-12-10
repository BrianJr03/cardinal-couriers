package edu.bsu.cs222.finalProject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeliveryInfo
{
     private final String streetAddressLine1;
     private final String zipCode;
     private final String state;
     private final String city;

    public DeliveryInfo( String streetAddressLine1 , String zipCode , String state , String city ) {
        this.streetAddressLine1 = streetAddressLine1;
        this.zipCode = zipCode;
        this.state = state;
        this.city = city;
    }

    public boolean isValidZip( String zipCode ) {
        Pattern pattern = Pattern.compile("\\d{5}");
        Matcher matcher = pattern.matcher(zipCode);
        return (matcher.find() && matcher.group().equals(zipCode));
    }

    public boolean isValidCity(String city) {
        Pattern pattern = Pattern.compile("^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$");
        Matcher matcher = pattern.matcher(city);
        if ( city.length() < 3 ) {return false;}
        return (matcher.find() && matcher.group().equals(city));
    }

    public boolean isValidState_Abbreviation(String state) {
        Pattern pattern = Pattern.compile("^(?:(A[KLRZ]|C[AOT]|D[CE]|FL|GA|HI|I[ADLN]|K[SY]|LA|M[ADEINOST]|" +
                "N[CDEHJMVY]|O[HKR]|P[AR]|RI|S[CD]|T[NX]|UT|V[AIT]|W[AIVY]))$");
        Matcher matcher = pattern.matcher(state);
        return (matcher.find() && matcher.group().equals(state));
    }

    public boolean isValidStreet_Address(String streetAddress) {
        Pattern pattern = Pattern.compile("^\\d+?[A-Za-z]*\\s\\w*\\s?\\w+?\\s\\w{2}\\w*\\s*\\w*$");
        Matcher matcher = pattern.matcher(streetAddress);
        return (matcher.find() && matcher.group().equals(streetAddress));
    }

    public String getStreetAddressLine1()
    { return streetAddressLine1; }

    public String getZipCode()
    { return zipCode; }

    public String getState()
    { return state; }

    public String getCity()
    { return city; }
}
