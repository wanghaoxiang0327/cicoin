package com.sskj.asset.data;

public class TransferInfoBean {

    /**
     * asset : 159106.2218//非杠杆
     * wall : 20//杠杆
     */

    private double asset;
    private double wall;

    public double getAsset() {
        return asset;
    }

    public void setAsset(double asset) {
        this.asset = asset;
    }

    public double getWall() {
        return wall;
    }

    public void setWall(double wall) {
        this.wall = wall;
    }
}
