package com.cqrit.spycket;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cqrit.spycket.models.Data;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

import com.cqrit.spycket.models.PacketData;

public class DataView extends AppCompatActivity {

    public interface DataApiService {
        @GET("api/informations/{id}/{packet}")
        Call<List<Data>> getData(@Path("id") String id, @Path("packet") String packet);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_view);

        String packetName = getIntent().getStringExtra("selectedData");
        setTitle(packetName);

        TextView detailsTextView = findViewById(R.id.data_packet_text);
        detailsTextView.setText("Data " + packetName);

        TextView ipSourceData = findViewById(R.id.ipSourceData);
        TextView ipDestData = findViewById(R.id.ipDestData);
        TextView macSourceData = findViewById(R.id.macSourceData);
        TextView macDestData = findViewById(R.id.macDestData);
        TextView appData = findViewById(R.id.protocolAppData);
        TextView transpData = findViewById(R.id.protocolTransportData);

        OkHttpClient httpClient = new OkHttpClient();
        String URL_BASE = "http://10.3.122.96:5000";
        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient)
                .baseUrl(URL_BASE + "/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        String id = getIntent().getStringExtra("idData");
        Log.e("DataView", id);
        String num_trame = getIntent().getStringExtra("num_trame");
        Log.v("DataView", "Valeur : " + num_trame);
        DataView.DataApiService dataApiService = retrofit.create(DataView.DataApiService.class);
        Call<List<Data>> call = dataApiService.getData(id, num_trame);

        call.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(@NonNull Call<List<Data>> call, @NonNull Response<List<Data>> response) {
                List<Data> dataList = response.body();
                if (dataList != null && !dataList.isEmpty()) {
                    Data data = dataList.get(0);
                    ipSourceData.setText(data.getIp_src());
                    ipDestData.setText(data.getIp_dst());
                    macSourceData.setText(data.getMac_src());
                    macDestData.setText(data.getMac_dst());
                    appData.setText(data.getApp());
                    transpData.setText(data.getTransport());
                }
                Toast.makeText(DataView.this, "Analysis fetched successfully", Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onFailure(@NonNull Call<List<Data>> call, @NonNull Throwable t) {
                Log.e("DataView", "Error fetching data", t);
                Toast.makeText(DataView.this, "Error fetching data: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }
}