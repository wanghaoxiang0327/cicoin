package com.sskj.asset;

import com.lzy.okgo.OkGo;
import com.sskj.common.base.BasePresenter;
import com.sskj.asset.InsertAddressActivity;
import com.sskj.common.http.BaseHttpConfig;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;


/**
 * @author Hey
 * Create at  2019/06/26
 */
public class InsertAddressPresenter extends BasePresenter<InsertAddressActivity> {


    public void insertAddress(String qiaobao_url, String notes, String type) {
        OkGo.<HttpResult<Object>>post(BaseHttpConfig.BASE_URL + HttpConfig.ADDRESS_MANAGE)
                .params("qiaobao_url", qiaobao_url)
                .params("notes", notes)
                .params("type", type)
                .execute(new JsonCallBack<HttpResult<Object>>(this,false) {
                    @Override
                    protected void onNext(HttpResult<Object> result) {
                        mView.insertSuccess();
                    }
                });

    }
}
