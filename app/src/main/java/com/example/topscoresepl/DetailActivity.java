package com.example.topscoresepl;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.topscoresepl.apihelper.BaseApiService;
import com.example.topscoresepl.apihelper.UtilsApi;
import com.example.topscoresepl.model.Player;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DetailActivity extends AppCompatActivity {

    private BaseApiService baseApiService;
    String token = "33a4fff7578c44fe83ffa0a1f34c9cd6";
    String firstName, playerPosition, playerClub, playerCountry, playerNationality, playerDate;
    Object playerDate2;
    Integer shirtNumber;
    Integer idExtras = 0;
    String playersimage;
    TextView tvshirtNumber, tvplayerName, tvplayerPosition, tvplayerClub, tvplayerCountry, tvplayerNationality, tvplayerDate;
    ImageView ivPictPlayers, back;
    private StatusBar statusBar;
    String DATE = "dd MMMM yyyy";
    String outputText;

    Context context;
    ProgressDialog progressDialog;
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        statusBar = new StatusBar(this);
        statusBar.statusBarCall(DetailActivity.this);

        baseApiService = UtilsApi.getApiService();

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        AppBarLayout appBarLayout = findViewById(R.id.appbar);

       /* Typeface typeface = Typeface.createFromAsset(context.getAssets(), "font/TitilliumWeb-Regular.ttf");*/

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(firstName);
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle("");
                    isShow = false;
                }

            }
        });

        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.white));
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.white));

        tvshirtNumber = findViewById(R.id.shirtNumber);
        tvplayerName = findViewById(R.id.playerName);
        ivPictPlayers = findViewById(R.id.detailPict);
        tvplayerPosition = findViewById(R.id.playerPosition);
        tvplayerClub = findViewById(R.id.playerClub);
        tvplayerCountry = findViewById(R.id.playerCountryofBirth);
        tvplayerNationality = findViewById(R.id.playerNationality);
        tvplayerDate = findViewById(R.id.playerDate);
        back = findViewById(R.id.backHome);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            idExtras = bundle.getInt("idPlayers");
            playersimage = bundle.getString("urlPict");
            playerClub =bundle.getString("clubName");
        }

        openProgressDialog();
        getPlayersDetail();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(DetailActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });

    }

    private void getPlayersDetail() {
        baseApiService.getPlayers(
                token,
                idExtras
        ).enqueue(new Callback<Player>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    System.out.println("response id Players =>" + response.body().getFirstName());

                    firstName = response.body().getName();
                    shirtNumber = response.body().getShirtNumber();
                    playerPosition = response.body().getPosition();
                    playerCountry = response.body().getCountryOfBirth();
                    playerNationality = response.body().getNationality();
                    playerDate = response.body().getDateOfBirth();



                    /*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
                    LocalDateTime dateTime = LocalDateTime.parse(playerDate, formatter);
                    //System.out.println(dateTime.format(formatter2));*/

                    DateFormat outputFormat = new SimpleDateFormat(DATE, Locale.US);
                    DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

                    Date date = null;
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE);
                        dateFormat.setTimeZone(TimeZone.getTimeZone("ID"));
                        date = inputFormat.parse(playerDate);
                        outputText = outputFormat.format(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                    tvplayerName.setText(firstName);
                    tvplayerPosition.setText(playerPosition);
                    tvplayerClub.setText(playerClub);
                    tvplayerCountry.setText(playerCountry);
                    tvplayerNationality.setText(playerNationality);
                    tvplayerDate.setText(outputText);

                    Picasso.get().load(playersimage)
                            .into(ivPictPlayers);

                    if (shirtNumber != null) {
                        tvshirtNumber.setText(String.valueOf(shirtNumber));
                    } else {
                        tvshirtNumber.setText("12");
                    }

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(context, "Failed get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {

            }
        });
    }

    private void openProgressDialog() {
        progressDialog = new ProgressDialog(DetailActivity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setTitle("Detail Players");
        progressDialog.show();
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
