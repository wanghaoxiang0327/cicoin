package com.sskj.contact.dialog;

import com.hjq.toast.ToastUtils;
import com.lzy.okgo.OkGo;
import com.sskj.common.base.BasePresenter;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;

public class CreateDialogPresenter extends BasePresenter<ContactCreateDialog> {

    public void createOrder(String price, int priceType, int tradeType, String num, String code, String lever) {
        OkGo.<HttpResult<Object>>post(HttpConfig.BASE_URL + HttpConfig.CONTACT_CREATE_ORDER)
                .tag(this)
                .params("newprice", price)
                .params("type", priceType)
                .params("otype", tradeType)
                .params("buynum", num)
                .params("shopname", code)
                .params("leverage", lever)
                .execute(new JsonCallBack<HttpResult<Object>>(this) {
                    @Override
                    protected void onNext(HttpResult<Object> result) {
                        ToastUtils.show(result.getMsg());
                        mView.createOrderSuccess();
                    }
                });

    }
}
