package com.tap.schoolplatform.models.shared;

public class Address {
    String street;
    int postalCode;
    String colony;
    String city;
    String state;
    String country;

    public Address(String street, int postalCode, String colony, String city, String state, String country) {
        this.street = street;
        this.postalCode = postalCode;
        this.colony = colony;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    public int getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getColony() {
        return colony;
    }
    public void setColony(String colony) {
        this.colony = colony;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
}
