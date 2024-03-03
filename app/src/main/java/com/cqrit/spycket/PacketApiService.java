package com.cqrit.spycket;


import com.cqrit.spycket.models.PacketData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PacketApiService {
    @GET("api/informations")
    Call<List<PacketData>> getPacket();
}