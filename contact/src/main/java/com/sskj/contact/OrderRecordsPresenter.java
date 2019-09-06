package com.sskj.contact;

import com.hjq.toast.ToastUtils;
import com.lzy.okgo.OkGo;
import com.sskj.common.base.BasePresenter;
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
    public void getDetailInfo(String code) {
        OkGo.<HttpResult<DetailOrder>>get(HttpConfig.BASE_URL + HttpConfig.GET_TONGJI)
                .tag(this)
                .params("code", code)
                .execute(new JsonCallBack<HttpResult<DetailOrder>>(this, false) {
                    @Override
                    protected void onNext(HttpResult<DetailOrder> result) {
                        try {
                            if (result != null && result.getData() != null) {
                                mView.setDetailInfo(result.getData());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public void closeAllOrder() {
        OkGo.<HttpResult<Object>>post(HttpConfig.BASE_URL + HttpConfig.CONTACT_CLOSE_ALL_ORDER)
                .tag(this)
                .execute(new JsonCallBack<HttpResult<Object>>(this) {
                    @Override
                    protected void onNext(HttpResult<Object> result) {
                        ToastUtils.show(result.getMsg());
                        mView.closeAllOrderSuccess();
                    }
                });
    }

}

