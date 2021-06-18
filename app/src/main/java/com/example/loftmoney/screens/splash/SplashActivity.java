package com.example.loftmoney.screens.splash;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.loftmoney.LoftApp;
import com.example.loftmoney.R;
import com.example.loftmoney.screens.login.LoginActivity;
import com.example.loftmoney.screens.main.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_name), 0);
        String authToken = sharedPreferences.getString(LoftApp.AUTH_KEY, "");

        if(TextUtils.isEmpty(authToken)){
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
        }
    }
}