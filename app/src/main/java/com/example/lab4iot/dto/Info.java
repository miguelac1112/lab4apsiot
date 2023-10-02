package com.example.lab4iot.dto;


public class Info {


   String seed;


   int results;


   int page;


   String version;


    public void setSeed(String seed) {
        this.seed = seed;
    }
    public String getSeed() {
        return seed;
    }
    
    public void setResults(int results) {
        this.results = results;
    }
    public int getResults() {
        return results;
    }
    
    public void setPage(int page) {
        this.page = page;
    }
    public int getPage() {
        return page;
    }
    
    public void setVersion(String version) {
        this.version = version;
    }
    public String getVersion() {
        return version;
    }
    
}