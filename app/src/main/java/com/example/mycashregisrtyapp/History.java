package com.example.mycashregisrtyapp;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class History extends AppCompatActivity {

    ItemBaseAdapter adapter;
    UpdateAllItems manager;
    ArrayList<ItemInfomation> itemArray;
    ListView itemList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        manager = ((MyApp)getApplication()).manager;
        itemList = findViewById(R.id.item_list);

        itemArray = manager.purchasedItems;
        adapter = new ItemBaseAdapter(itemArray, this);
        itemList.setAdapter(adapter);

        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ItemInfomation temp = itemArray.get(i);

                Intent myIntent = new Intent(view.getContext(), ProductInfomation.class);
                myIntent.putExtra("items", temp);
                startActivity(myIntent);
            }
        });
    }
}
