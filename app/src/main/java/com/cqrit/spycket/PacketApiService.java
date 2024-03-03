package com.cqrit.spycket;


import com.cqrit.spycket.models.PacketData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PacketApiService {
    @GET("todos")
    Call<List<PacketData>> getPacket();
}