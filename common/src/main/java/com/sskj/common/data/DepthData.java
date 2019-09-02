package com.sskj.common.data;

public class DepthData {

    private float totalSize;
    private float price;


    public DepthData() {
    }

    public DepthData(float totalSize, float price) {
        this.totalSize = totalSize;
        this.price = price;
    }

    public float getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(float totalSize) {
        this.totalSize = totalSize;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getVolume() {
        return totalSize;
    }
}
