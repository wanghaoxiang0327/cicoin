package com.sskj.market;

import com.lzy.okgo.OkGo;
import com.sskj.common.base.BasePresenter;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;
import com.sskj.market.data.BuySellData;

import java.util.List;


/**
 * @author Hey
 * Create at  2019/07/31 10:25:20
 */
class DeepthPresenter extends BasePresenter<DepthFragment> {


    public void getDeepData(String code) {
        OkGo.<HttpResult<List<BuySellData>>>get(HttpConfig.BASE_URL + HttpConfig.GET_DEEP)
                .params("code", code)
                .execute(new JsonCallBack<HttpResult<List<BuySellData>>>(this) {
                    @Override
                    protected void onNext(HttpResult<List<BuySellData>> result) {
                        if (result.getData() != null && result.getData().size() > 0) {
                            mView.setDeepMap(result.getData().get(0));
                        }
                    }
                });
    }

    public void getPankouData(String code) {
        OkGo.<HttpResult<List<BuySellData>>>get(HttpConfig.BASE_URL + HttpConfig.GET_PANKOU)
                .params("code", code)
                .tag(this)
                .execute(new JsonCallBack<HttpResult<List<BuySellData>>>(this) {
                    @Override
                    protected void onNext(HttpResult<List<BuySellData>> result) {
                        if (result.getData() != null && result.getData().size() > 0) {
                            mView.setDeepData(result.getData().get(0));
                        }
                    }
                });
    }
}
