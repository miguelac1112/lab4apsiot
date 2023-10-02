package com.example.lab4iot.dto;


public class Login {


   String uuid;


   String username;


   String password;


   String salt;


   String md5;


   String sha1;


   String sha256;


    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public String getUuid() {
        return uuid;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    
    public void setSalt(String salt) {
        this.salt = salt;
    }
    public String getSalt() {
        return salt;
    }
    
    public void setMd5(String md5) {
        this.md5 = md5;
    }
    public String getMd5() {
        return md5;
    }
    
    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }
    public String getSha1() {
        return sha1;
    }
    
    public void setSha256(String sha256) {
        this.sha256 = sha256;
    }
    public String getSha256() {
        return sha256;
    }
    
}