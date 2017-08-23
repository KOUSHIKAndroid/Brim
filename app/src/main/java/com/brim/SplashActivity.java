package com.brim;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.brim.AppContant.BrimApplication;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                finish();

            }
        },800);


//        BrimApplication.getInstnace().SetUserId("MA2P1OOWMD");
//        BrimApplication.getInstnace().SetAuthToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiJNQTJQMU9PV01EIiwiZXhwIjoxNTAzMTIwMzMyfQ.9uFXq-4Cm3jCiPtdWRP4w2d3LBUmf259zCiRSAfnlDo");



    }
}
