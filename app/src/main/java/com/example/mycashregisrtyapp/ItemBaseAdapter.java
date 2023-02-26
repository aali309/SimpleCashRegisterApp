package com.example.mycashregisrtyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class ItemBaseAdapter extends BaseAdapter {
    ArrayList<ItemInfomation> items;
    Context context;

    public ItemBaseAdapter(ArrayList<ItemInfomation> listOfItems, Context context) {
        this.items = listOfItems;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public ItemInfomation getItem(int i) {
        return items.get(i);
    }

    public ItemInfomation getItemByName(String name) {
        for (ItemInfomation item: items) {
            if (item.product_type.equals(name)) {
                return item;
            }
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.baseadapter,null);

        TextView productType = view.findViewById(R.id.productTypeList);
        TextView productCost = view.findViewById(R.id.productCostList);
        TextView productQuantity = view.findViewById(R.id.productQuantityList);

        productType.setText(items.get(i).product_type);
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getCurrencyInstance();
        decimalFormat.setMinimumFractionDigits(2);
        String totalCostFormatted = decimalFormat.format(items.get(i).amount);
        productCost.setText(totalCostFormatted);
        productQuantity.setText(String.valueOf(items.get(i).quantity));

        return view;
    }
}
