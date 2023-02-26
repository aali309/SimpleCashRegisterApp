package com.example.mycashregisrtyapp;

import android.app.Application;

import java.util.ArrayList;

public class MyApp extends Application {
    UpdateAllItems manager = new UpdateAllItems();

    @Override
    public void onCreate() {
        super.onCreate();
        ArrayList<ItemInfomation> tempList = manager.allItems;
        tempList.add(new ItemInfomation(20.44, "Pante", 10, null));
        tempList.add(new ItemInfomation(5.9, "Hats", 30, null));
        tempList.add(new ItemInfomation(10.44, "Shoes", 100, null));
    }
}
