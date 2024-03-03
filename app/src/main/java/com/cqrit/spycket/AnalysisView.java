package com.cqrit.spycket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.cqrit.spycket.models.Todo;

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

        listView = findViewById(R.id.analysisList);
        ArrayAdapter<Todo> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        Retrofit retrofit = new Retrofit.Builder()
                // TODO : CHANGE URL
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<Todo>> call = apiService.getTodos();
        call.enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(@NonNull Call<List<Todo>> call, @NonNull Response<List<Todo>> response) {
                assert response.body() != null;
                arrayAdapter.addAll(response.body());
                listView.setAdapter(arrayAdapter);
                Toast.makeText(AnalysisView.this, "Analysis fetched successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NonNull Call<List<Todo>> call, @NonNull Throwable t) {
                Toast.makeText(AnalysisView.this, "Error fetching data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}