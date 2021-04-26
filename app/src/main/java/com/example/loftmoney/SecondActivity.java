package com.example.loftmoney;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;


public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        int costId = getIntent().getIntExtra(name:"COST_ID", defaultValue:0);
        Log.e(tag:"TAG", msg:"Cost Id = " + costId);
    }
}