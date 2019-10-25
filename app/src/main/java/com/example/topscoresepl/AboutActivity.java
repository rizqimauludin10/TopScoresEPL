package com.example.topscoresepl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static android.view.View.*;

public class AboutActivity extends AppCompatActivity {

    TextView emailLink;
    String Link = "https://www.dicoding.com/users/392388";
    ImageView back, github, linkedin, whatsapp, twitter;
    StatusBar statusBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        statusBar = new StatusBar(this);
        statusBar.statusBarCall(AboutActivity.this);

        back = findViewById(R.id.backHomeAbout);
        github = findViewById(R.id.github);
        linkedin = findViewById(R.id.linkedin);
        whatsapp = findViewById(R.id.whatsapp);
        twitter = findViewById(R.id.twitter);


       /* emailLink = findViewById(R.id.email);
        emailLink.setMovementMethod(LinkMovementMethod.getInstance());*/

       back.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent;
               intent = new Intent(AboutActivity.this, Main2Activity.class);
               startActivity(intent);
           }
       });

       github.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View view) {
               String url = "https://github.com/rizqimauludin10";
               Uri uriUrl = Uri.parse(url);
               Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
               startActivity(launchBrowser);
           }
       });

       twitter.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View view) {
               String url = "https://twitter.com/mrizqimldn_";
               Uri uriUrl = Uri.parse(url);
               Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
               startActivity(launchBrowser);
           }
       });

       linkedin.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View view) {
               String url = "https://www.linkedin.com/in/rizqi-mauludin-48367a112/";
               Uri uriUrl = Uri.parse(url);
               Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
               startActivity(launchBrowser);
           }
       });

       whatsapp.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View view) {
               String phone = "6289685191803";
               Intent intent = new Intent(Intent.ACTION_VIEW,
                       Uri.parse("https://api.whatsapp.com/send?phone=" + phone + "&text=Selamat%20Pagi,%20Boleh%20berkenalan%20dengan%20anda?"));
               startActivity(intent);
           }
       });

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
