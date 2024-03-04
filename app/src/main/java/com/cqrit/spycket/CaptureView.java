package com.cqrit.spycket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.cqrit.spycket.models.CaptureData;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CaptureView extends AppCompatActivity {

    private ListView listView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_view);

        listView = findViewById(R.id.detailsCaptureList);
        ArrayAdapter<CaptureData> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        OkHttpClient httpClient = new OkHttpClient();
        String URL_BASE = "http://10.3.122.96:5000";
        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient)
                .baseUrl(URL_BASE +"/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CaptureApiService captureApiService = retrofit.create(CaptureApiService.class);
        Call<List<CaptureData>> call = captureApiService.getData();

        call.enqueue(new Callback<List<CaptureData>>() {
            @Override
            public void onResponse(@NonNull Call<List<CaptureData>> call, @NonNull Response<List<CaptureData>> response) {
                assert response.body() != null;
                arrayAdapter.addAll(response.body());
                listView.setAdapter(arrayAdapter);
                Toast.makeText(CaptureView.this, "Analysis fetched successfully", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(@NonNull Call<List<CaptureData>> call, @NonNull Throwable t) {
                Log.e("CaptureView", "Error fetching data", t);
                Toast.makeText(CaptureView.this, "Error fetching data: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            CaptureData selectedData = arrayAdapter.getItem(position);
            String itemName = listView.getItemAtPosition(position).toString();
            Intent intent = new Intent(CaptureView.this, PacketView.class);
            intent.putExtra("selectedData", String.valueOf(selectedData));
            intent.putExtra("itemName", itemName);
            startActivity(intent);
        });

    }
}