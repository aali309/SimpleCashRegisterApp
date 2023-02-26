package com.example.mycashregisrtyapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ItemBaseAdapter adapter;
    ListView itemList;
    TextView totalText;
    TextView quantityText;
    NumberPicker numberPicker;
    Button buyBtn;
    Button managerBtn;
    TextView productTypeText;

    ArrayList<ItemInfomation> itemArray;
    ArrayList<ItemInfomation> purchasedItems;
    UpdateAllItems manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = ((MyApp)getApplication()).manager;

        itemList = findViewById(R.id.item_list);
        totalText = findViewById(R.id.totalText);
        quantityText = findViewById(R.id.quantityText);
        numberPicker = findViewById(R.id.numberPicker);
        buyBtn = findViewById(R.id.buyButton);
        managerBtn = findViewById(R.id.managerButton);
        productTypeText = findViewById(R.id.productTypeText);

        buyBtn.setOnClickListener(this);
        managerBtn.setOnClickListener(this);

        purchasedItems = new ArrayList<>();
        itemArray = manager.allItems;
        adapter = new ItemBaseAdapter(itemArray, this);
        itemList.setAdapter(adapter);

        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ItemInfomation temp = adapter.getItem(i);
                productTypeText.setText(temp.product_type);
                quantityText.setText(String.valueOf(0));
                numberPicker.setValue(0);
                totalText.setText("Total");
            }
        });

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                ItemInfomation temp = adapter.getItemByName(productTypeText.getText().toString());
                quantityText.setText(String.valueOf(i1));
                if (temp != null) {
                    double totalCost = temp.amount * i1;
                    DecimalFormat decimalFormat = new DecimalFormat();
                    decimalFormat.setMinimumFractionDigits(2);
                    String totalCostFormatted = decimalFormat.format(totalCost);
                    totalText.setText(totalCostFormatted);
                }
            }
        });

        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(99);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.buyButton:
                ItemInfomation currentItem = adapter.getItemByName(productTypeText.getText().toString());
                if (currentItem == null || Integer.parseInt(quantityText.getText().toString()) == 0 || totalText.getText().toString().equals("Total")) {
                    Toast.makeText(this, "Error: All fields are required!", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(quantityText.getText().toString()) > currentItem.quantity) {
                    Toast.makeText(this, "Error: Not enough quantity in stock!", Toast.LENGTH_SHORT).show();
                }
                else {
                    int amtPurchased = Integer.parseInt(quantityText.getText().toString());
                    double totalCost = Double.parseDouble(totalText.getText().toString());
                    DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getCurrencyInstance();
                    decimalFormat.setMinimumFractionDigits(2);
                    String totalCostFormatted = decimalFormat.format(totalCost);
                    String productType = productTypeText.getText().toString();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    manager.addPurchasedItem(new ItemInfomation(totalCost, productType, amtPurchased, LocalDateTime.now().format(formatter).toString()));
                    AlertDialog.Builder alert = new AlertDialog.Builder(this);
                    alert.setTitle("Thank You for your purchase!");
                    alert.setMessage("You Purchased " + quantityText.getText().toString() + " " + productType + " for " + totalCostFormatted);
                    alert.show();
                    currentItem.quantity -= amtPurchased;
                    adapter.notifyDataSetChanged();
                    productTypeText.setText("Choose Product");
                    numberPicker.setValue(0);
                    quantityText.setText("0");
                    totalText.setText("Total");
                }
                break;

            case R.id.managerButton:
                Intent myIntent = new Intent(this, RestockHistory.class);
                startActivity(myIntent);
                break;
        }
    }
}