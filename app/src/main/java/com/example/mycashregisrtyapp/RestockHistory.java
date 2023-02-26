package com.example.mycashregisrtyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RestockHistory extends AppCompatActivity implements View.OnClickListener {
    Button restockBtn;
    Button historyBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager);
        restockBtn = findViewById(R.id.restockButton);
        historyBtn = findViewById(R.id.historyButton);
        historyBtn.setOnClickListener(this);
        restockBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.restockButton:
                Intent myIntent = new Intent(this, Restock.class);
                startActivity(myIntent);
                break;
            case R.id.historyButton:
                Intent restockIntent = new Intent(this, History.class);
                startActivity(restockIntent);
                break;
        }
    }
}
