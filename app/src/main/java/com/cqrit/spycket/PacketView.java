package com.cqrit.spycket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cqrit.spycket.models.PacketData;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class PacketView extends AppCompatActivity {
    public interface PacketApiService {
        @GET("api/informations/{id}")
        Call<List<PacketData>> getPacket(@Path("id") String id);
    }

    private ListView listView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packet_view);

        String itemName = getIntent().getStringExtra("itemName");
        setTitle(itemName);

        TextView detailsTextView = findViewById(R.id.data_packet_text);
        detailsTextView.setText("Details of " + itemName + " capture");

        listView = findViewById(R.id.detailsCaptureList);
        ArrayAdapter<PacketData> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        OkHttpClient httpClient = new OkHttpClient();
        String URL_BASE = "http://10.3.122.96:5000";
        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient)
                .baseUrl(URL_BASE + "/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        String id = getIntent().getStringExtra("idExecution");
        PacketApiService packetApiService = retrofit.create(PacketApiService.class);
        Call<List<PacketData>> call = packetApiService.getPacket(id);

        call.enqueue(new Callback<List<PacketData>>() {
            @Override
            public void onResponse(@NonNull Call<List<PacketData>> call, @NonNull Response<List<PacketData>> response) {
                assert response.body() != null;
                arrayAdapter.addAll(response.body());
                listView.setAdapter(arrayAdapter);
                Toast.makeText(PacketView.this, "Analysis fetched successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NonNull Call<List<PacketData>> call, @NonNull Throwable t) {
                Toast.makeText(PacketView.this, "Error fetching data", Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PacketData selectedData = arrayAdapter.getItem(position);
                if (selectedData != null) {
                    Intent intent = new Intent(PacketView.this, DataView.class);
                    String num_trame = selectedData.getTrame();
                    intent.putExtra("idData", String.valueOf(id));
                    intent.putExtra("num_trame", num_trame);
                    intent.putExtra("selectedData", String.valueOf(selectedData));
                    startActivity(intent);
                }
            }
        });
    }
}