package com.sskj.common.data;

import java.io.Serializable;
import java.util.List;

public class WithdrawCoinInfo implements Serializable {
    public List<WithdrawCoin> btc;
    public List<WithdrawCoin> eth;

    public static class WithdrawCoin {
        public String id;
        public String cat;
        public String qiaobao_url;
        public String notes;
    }

}
