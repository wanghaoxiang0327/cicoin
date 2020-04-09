package com.sskj.contact.data;

public class Pankou {

    private String totalSize;
    private String price;
    private int rate;

    public Pankou() {

    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getProgressRate() {
        return rate;
    }

    public Pankou(String price, String totalSize) {

        if (totalSize.equals("0.0")) {
            totalSize = "--";
        }
        if (price.equals("0.0")) {
            price = "--";
        }

        this.totalSize = totalSize;
        this.price = price;
    }

    public String getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(String totalSize) {
        this.totalSize = totalSize;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
