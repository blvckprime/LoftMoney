package com.example.loftmoney.screens.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.loftmoney.screens.dashboard.DashboardFragment;
import com.example.loftmoney.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction().replace(R.id.containerView, new DashboardFragment())
                .commitNow();
    }

}