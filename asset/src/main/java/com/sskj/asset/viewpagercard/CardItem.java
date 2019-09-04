package com.sskj.asset.viewpagercard;


public class CardItem {
    private String assetacount;
    private String mTextResource;
    private String mTitleResource;

    public CardItem(String assetacount,String title, String text) {
        this.assetacount = assetacount;
        mTitleResource = title;
        mTextResource = text;
    }

    public String getAssetacount() {
        return assetacount;
    }

    public String getText() {
        return mTextResource;
    }

    public String getTitle() {
        return mTitleResource;
    }
}
