package com.sskj.asset;

import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.sskj.asset.data.AddressBean;
import com.sskj.asset.data.WithdrawInfo;
import com.sskj.common.base.BasePresenter;
import com.sskj.asset.AddressListActivity;
import com.sskj.common.http.ApiException;
import com.sskj.common.http.BaseHttpConfig;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;


/**
 * @author Hey
 * Create at  2019/06/26
 */
public class AddressListPresenter extends BasePresenter<AddressListActivity> {
    public void getAddressList() {
        OkGo.<HttpResult<AddressBean>>get(BaseHttpConfig.BASE_URL + HttpConfig.ADDRESS_LIST)
                .execute(new JsonCallBack<HttpResult<AddressBean>>(this, false) {
                    @Override
                    protected void onNext(HttpResult<AddressBean> result) {
                        mView.setData(result.getData());
                    }
                });
    }


    public void deleteAddress(String qiaobao_url,String id) {
        OkGo.<HttpResult<Object>>post(BaseHttpConfig.BASE_URL + HttpConfig.ADDRESS_MANAGE)
                .params("qiaobao_url", qiaobao_url)
                .params("id",id)
                .params("act", "del")
                .execute(new JsonCallBack<HttpResult<Object>>(this, false) {
                    @Override
                    protected void onNext(HttpResult<Object> result) {
                        mView.deleteSuccess();
                    }

                    @Override
                    public void onError(Response<HttpResult<Object>> response) {
                        super.onError(response);
                        ApiException exception = (ApiException) response.getException();
                    }
                });

    }
}
