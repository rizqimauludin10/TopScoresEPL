package com.example.topscoresepl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.topscoresepl.adapter.ScoresAdapter;
import com.example.topscoresepl.apihelper.BaseApiService;
import com.example.topscoresepl.apihelper.UtilsApi;
import com.example.topscoresepl.model.Player;
import com.example.topscoresepl.model.Scorer;
import com.example.topscoresepl.model.Value;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    Context context;
    private List<Scorer> playerList = new ArrayList<>();
    private RecyclerView.Adapter adapter;
    private BaseApiService baseApiService;
    String token = "33a4fff7578c44fe83ffa0a1f34c9cd6";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = (RecyclerView) findViewById(R.id.rvscores);
        baseApiService = UtilsApi.getApiService();

        adapter = new ScoresAdapter(context, playerList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //GetAPI
        getResultScores();

    }

    private void getResultScores() {
        //progressDialog = ProgressDialog.show(context, null, "Harap Tunggu...", true, false);

        baseApiService.getValue(token).enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                if (response.isSuccessful()) {
                    //progressDialog.dismiss();
                    playerList = response.body().getScorers();
                    recyclerView.setAdapter(new ScoresAdapter(context, playerList));
                    adapter.notifyDataSetChanged();
                } else {
                    //progressDialog.dismiss();
                    Toast.makeText(context, "Failed get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                //progressDialog.dismiss();
                Toast.makeText(context, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
