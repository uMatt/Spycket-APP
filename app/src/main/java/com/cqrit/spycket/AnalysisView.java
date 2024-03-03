package com.cqrit.spycket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.cqrit.spycket.models.CaptureData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AnalysisView extends AppCompatActivity {

    private ListView listView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_view);

        listView = findViewById(R.id.detailsCaptureList);
        ArrayAdapter<CaptureData> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        Retrofit retrofit = new Retrofit.Builder()
                // TODO : CHANGE URL
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CaptureDataService captureDataService = retrofit.create(CaptureDataService.class);
        Call<List<CaptureData>> call = captureDataService.getData();

        call.enqueue(new Callback<List<CaptureData>>() {
            @Override
            public void onResponse(@NonNull Call<List<CaptureData>> call, @NonNull Response<List<CaptureData>> response) {
                assert response.body() != null;
                arrayAdapter.addAll(response.body());
                listView.setAdapter(arrayAdapter);
                Toast.makeText(AnalysisView.this, "Analysis fetched successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NonNull Call<List<CaptureData>> call, @NonNull Throwable t) {
                Toast.makeText(AnalysisView.this, "Error fetching data", Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Récupération de l'objet CaptureData sélectionné
                CaptureData selectedData = arrayAdapter.getItem(position);
                // Lancement de DetailActivity en passant les données nécessaires
                Intent intent = new Intent(AnalysisView.this, DetailAnalysis.class);
                intent.putExtra("selectedData", String.valueOf(selectedData));
                startActivity(intent);
            }
        });
    }
}