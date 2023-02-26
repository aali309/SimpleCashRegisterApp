package com.example.mycashregisrtyapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ProductInfomation extends AppCompatActivity {
    TextView productInfoText;
    TextView productInfoPrice;
    TextView productInfoDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moreinformation);

        productInfoText = findViewById(R.id.productMoreInfoText);
        productInfoPrice = findViewById(R.id.productMoreInfoPriceText);
        productInfoDate = findViewById(R.id.productMoreInfoDateText);

        ItemInfomation item = getIntent().getExtras().getParcelable("items");

        if (item != null) {
            productInfoText.setText("Product: " + item.product_type);
            DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getCurrencyInstance();
            decimalFormat.setMinimumFractionDigits(2);
            String totalCostFormatted = decimalFormat.format(item.amount);
            productInfoPrice.setText("Price: " + totalCostFormatted);
            productInfoDate.setText("Purchase Date: " + item.purchaseDate);
        }

    }
}
