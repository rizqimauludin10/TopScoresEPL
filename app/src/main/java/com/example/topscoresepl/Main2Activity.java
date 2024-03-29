package com.example.topscoresepl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.topscoresepl.adapter.ScoresAdapter;
import com.example.topscoresepl.apihelper.BaseApiService;
import com.example.topscoresepl.apihelper.UtilsApi;
import com.example.topscoresepl.model.Scorer;
import com.example.topscoresepl.model.Season;
import com.example.topscoresepl.model.Value;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Main2Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    Context context;
    private List<Scorer> playerList = new ArrayList<>();
    private InternetCheck internetCheck;
    private StatusBar statusBar;


    private RecyclerView.Adapter adapter;
    private BaseApiService baseApiService;
    private ShimmerFrameLayout mShimmerViewContainer;
    String token = "33a4fff7578c44fe83ffa0a1f34c9cd6";

    TextView tvMatchday, tvSeasonOne, tvSeasonTwo;
    Integer currentMatchday;
    String seasonOne, seasonTwo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        statusBar = new StatusBar(this);
        statusBar.statusBarCall(Main2Activity.this);

        baseApiService = UtilsApi.getApiService();

        setInternetCheck();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("Top Scorers List");

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("");
        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this,R.color.white));
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this,R.color.white));

        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);

        recyclerView = findViewById(R.id.rvscores);

        adapter = new ScoresAdapter(context, playerList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        tvMatchday = (TextView) findViewById(R.id.tvMatchday);
        tvSeasonOne = (TextView) findViewById(R.id.tvSeasonOne);
        tvSeasonTwo = (TextView) findViewById(R.id.tvSeasonTwo);

        //GetAPI


    }

    private void getResultScores() {
        baseApiService.getValue(
                token
        ).enqueue(new Callback<Value>() {
            @Override
            public void onResponse(@NotNull Call<Value> call, Response<Value> response) {
                if (response.isSuccessful()) {
                    playerList = response.body().getScorers();
                    recyclerView.setAdapter(new ScoresAdapter(context, playerList));
                    adapter.notifyDataSetChanged();

                    currentMatchday = response.body().getSeason().getCurrentMatchday();
                    seasonOne = response.body().getSeason().getStartDate();
                    seasonTwo = response.body().getSeason().getEndDate();

                    tvMatchday.setText(String.valueOf(currentMatchday));
                    tvSeasonOne.setText(seasonOne);
                    tvSeasonTwo.setText(seasonTwo);

                    System.out.println("response output version SOUT =>" + response.body().getSeason().getCurrentMatchday());


                    mShimmerViewContainer.stopShimmer();
                    mShimmerViewContainer.setVisibility(View.GONE);
                    recyclerView.smoothScrollToPosition(0);
                } else {
                    mShimmerViewContainer.stopShimmer();
                    Toast.makeText(getApplicationContext(), "Failed get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<Value> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Not Internet Connection", Toast.LENGTH_SHORT).show();
                //mShimmerViewContainer.stopShimmer();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id =item.getItemId();

        if (id == R.id.about) {
            Intent intent;
            intent = new Intent(Main2Activity.this, AboutActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void setInternetCheck() {
        internetCheck = new InternetCheck(getApplicationContext());
        if (!internetCheck.isConnected(Main2Activity.this)) {
            internetCheck.buildDialog(Main2Activity.this).show();
        } else {
            getResultScores();
        }
    }
}
