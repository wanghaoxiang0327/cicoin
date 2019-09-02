package com.sskj.contact;

import com.lzy.okgo.OkGo;
import  com.sskj.common.base.BasePresenter;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;
import com.sskj.contact.OrderRecordsActivity;
import com.sskj.contact.data.CoinInfo;
import com.sskj.contact.data.DetailOrder;


/**
 * @author Hey
 * Create at  2019/08/28 09:24:57
 */
class OrderRecordsPresenter extends BasePresenter<OrderRecordsActivity> {
    public void getDetailInfo(String code){
        OkGo.<HttpResult<DetailOrder>> get(HttpConfig.BASE_URL+HttpConfig.GET_TONGJI)
                .tag(this)
                .params("code",code)
                .execute(new JsonCallBack<HttpResult<DetailOrder>>(this){
                    @Override
                    protected void onNext(HttpResult<DetailOrder> result) {
                        mView.setDetailInfo(result.getData());
                    }
                });
    }
}
