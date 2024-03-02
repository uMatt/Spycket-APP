package com.cqrit.spycket;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/todos")
    Call<List<String>> getPackages();
}
