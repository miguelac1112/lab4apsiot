package com.example.lab4iot.services;

import com.example.lab4iot.dto.Profile;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RandomUser {

    @GET("/api")
    Call<Profile> getResults();
}
