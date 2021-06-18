package com.example.loftmoney.remote;

import com.google.gson.annotations.SerializedName;

public class MoneyRemoteItem {

    @SerializedName("id")
    private String itemId;

    @SerializedName("name")
    private String name;

    @SerializedName("price")
    private String price;

    @SerializedName("type")
    private String type;

    @SerializedName("date")
    private String date;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}