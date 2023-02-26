package com.example.mycashregisrtyapp;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemInfomation implements Parcelable {
    double amount;
    String product_type;
    int quantity;
    String purchaseDate;

    public ItemInfomation(double amount, String product_type, int quantity, String purchaseDate) {
        this.amount = amount;
        this.product_type = product_type;
        this.quantity = quantity;
        this.purchaseDate = purchaseDate;
    }

    protected ItemInfomation(Parcel in) {
        this.amount = in.readDouble();
        this.product_type = in.readString();
        this.quantity = in.readInt();
        this.purchaseDate = in.readString();
    }

    public static final Parcelable.Creator<ItemInfomation> CREATOR = new Parcelable.Creator<ItemInfomation>() {
        @Override
        public ItemInfomation createFromParcel(Parcel in) {
            return new ItemInfomation(in);
        }

        @Override
        public ItemInfomation[] newArray(int size) {
            return new ItemInfomation[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.amount);
        parcel.writeString(this.product_type);
        parcel.writeInt(this.quantity);
        parcel.writeString(this.purchaseDate);
    }
}
