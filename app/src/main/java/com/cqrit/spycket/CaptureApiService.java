package com.cqrit.spycket;


import com.cqrit.spycket.models.CaptureData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CaptureApiService {
    @GET("api/execution")
    Call<List<CaptureData>> getData();
}