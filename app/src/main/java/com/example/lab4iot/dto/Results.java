package com.example.lab4iot.dto;


public class Results {


    String gender;


    Name name;


    Location location;


    String email;


    Login login;


    Dob dob;


    Registered registered;


    String phone;


    String cell;


    Id id;


    Picture picture;


    String nat;


    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getGender() {
        return gender;
    }

    public void setName(Name name) {
        this.name = name;
    }
    public Name getName() {
        return name;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    public Location getLocation() {
        return location;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
    public Login getLogin() {
        return login;
    }

    public void setDob(Dob dob) {
        this.dob = dob;
    }
    public Dob getDob() {
        return dob;
    }

    public void setRegistered(Registered registered) {
        this.registered = registered;
    }
    public Registered getRegistered() {
        return registered;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPhone() {
        return phone;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }
    public String getCell() {
        return cell;
    }

    public void setId(Id id) {
        this.id = id;
    }
    public Id getId() {
        return id;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
    public Picture getPicture() {
        return picture;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }
    public String getNat() {
        return nat;
    }
}