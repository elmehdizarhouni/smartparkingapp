package com.example.parkingapp;

import model.ApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("your-endpoint")
    Call<ApiResponse> getData();
}
