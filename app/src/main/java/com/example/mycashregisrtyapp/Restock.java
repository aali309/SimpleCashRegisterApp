package com.example.mycashregisrtyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Restock extends AppCompatActivity implements View.OnClickListener {
    EditText newQuantity;
    Button okBtn;
    Button cancelBtn;
    ArrayList<ItemInfomation> itemArray;
    UpdateAllItems manager;
    ItemBaseAdapter adapter;
    ListView itemList;
    ItemInfomation selectedItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restock);

        selectedItem = null;
        newQuantity = findViewById(R.id.newQField);
        itemList = findViewById(R.id.listRestock);
        okBtn = findViewById(R.id.okButton);
        cancelBtn = findViewById(R.id.cancelButton);
        okBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);

        manager = ((MyApp)getApplication()).manager;
        itemArray = manager.allItems;
        adapter = new ItemBaseAdapter(itemArray, this);
        itemList.setAdapter(adapter);

        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItem = itemArray.get(i);
            }
        });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.okButton:
                if (selectedItem != null) {
                    if (!newQuantity.getText().toString().equals("")) {
                        int newquantity = Integer.parseInt(newQuantity.getText().toString());
                        if (newquantity < 1) {
                            Toast.makeText(this, "Error: Please enter at least 1 new product!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            selectedItem.quantity += newquantity;
                            adapter.notifyDataSetChanged();
                            selectedItem = null;
                            newQuantity.setText("");
                        }
                    }
                    else {
                        Toast.makeText(this, "Error: Please enter amount to add.", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(this, "Error: Please select an item.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.cancelButton:
                Intent restockIntent = new Intent(this, RestockHistory.class);
                startActivity(restockIntent);
                break;
        }
    }
}
