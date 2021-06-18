package com.example.loftmoney.cells;

import com.example.loftmoney.remote.MoneyRemoteItem;

public class MoneyItem {

    private String name;
    private String price;
    private boolean isSelected;

    public MoneyItem(String name, String price) {
        this.name = name;
        this.price = price;
        this.isSelected = false;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setPrice(int price) {
//        this.price = price;
//    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected){
        isSelected = selected;
    }

    public static MoneyItem getInstance(MoneyRemoteItem moneyRemoteItem) {
        return new MoneyItem(moneyRemoteItem.getName(), moneyRemoteItem.getPrice());
    }
}
