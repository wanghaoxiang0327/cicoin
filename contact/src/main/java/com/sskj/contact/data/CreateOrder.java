package com.sskj.contact.data;

import com.sskj.contact.type.Price;
import com.sskj.contact.type.Trade;

import java.io.Serializable;

public class CreateOrder implements Serializable {

    private Price priceType;
    private Trade tradeType;
    private String price;
    private String num;
    private String lever;
    private String code;
    private String total;

    private String fee;


    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Price getPriceType() {
        return priceType;
    }

    public void setPriceType(Price priceType) {
        this.priceType = priceType;
    }

    public Trade getTradeType() {
        return tradeType;
    }

    public void setTradeType(Trade tradeType) {
        this.tradeType = tradeType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getLever() {
        return lever;
    }

    public void setLever(String lever) {
        this.lever = lever;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

}
