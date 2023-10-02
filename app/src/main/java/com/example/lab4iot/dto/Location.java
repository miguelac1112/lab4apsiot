package com.example.lab4iot.dto;


public class Location {


   Street street;


   String city;


   String state;


   String country;


   int postcode;


   Coordinates coordinates;


   Timezone timezone;


    public void setStreet(Street street) {
        this.street = street;
    }
    public Street getStreet() {
        return street;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    public String getCity() {
        return city;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    public String getState() {
        return state;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    public String getCountry() {
        return country;
    }
    
    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }
    public int getPostcode() {
        return postcode;
    }
    
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
    public Coordinates getCoordinates() {
        return coordinates;
    }
    
    public void setTimezone(Timezone timezone) {
        this.timezone = timezone;
    }
    public Timezone getTimezone() {
        return timezone;
    }
    
}