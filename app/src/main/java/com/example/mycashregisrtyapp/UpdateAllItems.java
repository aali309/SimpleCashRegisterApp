package com.example.mycashregisrtyapp;

import java.util.ArrayList;

public class UpdateAllItems {
    ArrayList<ItemInfomation> allItems = new ArrayList<>(0);
    ArrayList<ItemInfomation> purchasedItems = new ArrayList<>(0);

    public void addPurchasedItem(ItemInfomation item) {
        purchasedItems.add(item);
    }
}
