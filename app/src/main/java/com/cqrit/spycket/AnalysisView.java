package com.cqrit.spycket;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class AnalysisView extends AppCompatActivity {
    private ListView listView;
    private ApiService apiService;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.analysisList);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://url.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        fetchData();
    }

    private void fetchData() {
        Call<List<String>> call = apiService.getPackages();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(@NonNull Call<List<String>> call, @NonNull Response<List<String>> response) {
                if (response.isSuccessful()) {
                    List<String> packages = response.body();
                    if (packages != null) {
                        updateListView(packages);
                    }
                } else {
                    // Gérer les erreurs ici
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<String>> call, @NonNull Throwable t) {
                // Gérer les erreurs ici
            }
        });
    }

    private void updateListView(List<String> packages) {
        PackageAdapter adapter = new PackageAdapter(this, packages);
        listView.setAdapter(adapter);
    }
}