package com.sskj.market;

import com.lzy.okgo.OkGo;
import  com.sskj.common.base.BasePresenter;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;
import com.sskj.market.data.Trade;
import com.sskj.market.data.TradeData;

import java.util.List;


/**
 * @author Hey
 * Create at  2019/08/01 16:57:04
 */
class TradePresenter extends BasePresenter<TradeFragment> {


    public void getOrder(String code){
        OkGo.<HttpResult<TradeData>>post(HttpConfig.BASE_URL + HttpConfig.GET_ALL_TRADE)
                .params("code", code)
                .execute(new JsonCallBack<HttpResult<TradeData>>(this) {
                    @Override
                    protected void onNext(HttpResult<TradeData> result) {
                        mView.setData(result.getData());
                    }
                });
    }
}
