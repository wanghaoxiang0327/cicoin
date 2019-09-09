package com.sskj.common.data;

public class DepthData {

    private String totalSize;
    private float price;


    public DepthData() {
    }

    public DepthData(String totalSize, float price) {
        this.totalSize = totalSize;
        this.price = price;
    }

    public String getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(String totalSize) {
        this.totalSize = totalSize;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getVolume() {
        return totalSize;
    }
}
