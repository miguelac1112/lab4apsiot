package com.example.lab4iot.dto;
import java.util.Date;


public class Dob {


   Date date;


   int age;


    public void setDate(Date date) {
        this.date = date;
    }
    public Date getDate() {
        return date;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    public int getAge() {
        return age;
    }
    
}