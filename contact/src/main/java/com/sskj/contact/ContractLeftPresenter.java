package com.sskj.contact;

import com.lzy.okgo.OkGo;
import com.sskj.common.base.BasePresenter;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;
import com.sskj.contact.ContractLeftFragment;
import com.sskj.contact.data.CoinInfo;


/**
 * @author Hey
 * Create at  2019/08/22 17:44:22
 */
class ContractLeftPresenter extends BasePresenter<ContractLeftFragment> {

    public void getCoinInfo(String code) {
        OkGo.<HttpResult<CoinInfo>>get(HttpConfig.BASE_URL + HttpConfig.GET_LEVER)
                .tag(this)
                .params("code", code)
                .execute(new JsonCallBack<HttpResult<CoinInfo>>(this) {
                    @Override
                    protected void onNext(HttpResult<CoinInfo> result) {
                        mView.setCoinInfo(result.getData());
                    }
                });
    }


}
