package com.example.vv.retrofitexamples;

import retrofit2.Call;
import retrofit2.http.GET;


public interface JsontestAPI {
        @GET("/")
        public Call<ServerTime> getServerDateTime();
}

