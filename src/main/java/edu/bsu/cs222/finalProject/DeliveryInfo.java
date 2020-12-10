package edu.bsu.cs222.finalProject;

public class DeliveryInfo
{
     private String streetAddressLine1;
     private String streetAddressLine2;
     private String zipCode;
     private String state;
     private String city;

    public DeliveryInfo( String streetAddressLine1 , String streetAddressLine2 , String zipCode , String state , String city ) {
        this.streetAddressLine1 = streetAddressLine1;
        this.streetAddressLine2 = streetAddressLine2;
        this.zipCode = zipCode;
        this.state = state;
        this.city = city;
    }

    public String getStreetAddressLine1()
    { return streetAddressLine1; }

    public String getStreetAddressLine2()
    { return streetAddressLine2; }

    public String getZipCode()
    { return zipCode; }

    public String getState()
    { return state; }

    public String getCity()
    { return city; }
}
