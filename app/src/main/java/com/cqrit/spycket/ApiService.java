package com.cqrit.spycket;


import com.cqrit.spycket.models.Todo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("todos")
    Call<List<Todo>> getTodos();
}
