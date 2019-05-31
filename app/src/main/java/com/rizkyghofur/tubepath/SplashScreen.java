package com.rizkyghofur.tubepath;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class SplashScreen extends AppCompatActivity {
    private int waktu_loading=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageView GambarGif;

        setContentView(R.layout.activity_splash_screen);
        GambarGif = findViewById(R.id.anilogo);
        Glide.with(SplashScreen.this)
                .load(R.drawable.anisplash)
                .asGif()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(GambarGif);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent home=new Intent(SplashScreen.this, WelcomeActivity.class);
                startActivity(home);
                finish();
            }
        },waktu_loading);
    }
}