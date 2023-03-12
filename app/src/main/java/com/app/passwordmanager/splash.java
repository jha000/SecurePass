package com.app.passwordmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;

public class splash extends AppCompatActivity {

    LottieAnimationView lottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        lottie=findViewById(R.id.lottie);

        int SPLASH_SCREEN_TIME_OUT = 3000;
        new Handler().postDelayed(() -> {
            Intent i=new Intent(splash.this,
                    authentication.class);
            startActivity(i);
            finish();
        }, SPLASH_SCREEN_TIME_OUT);

    }
}