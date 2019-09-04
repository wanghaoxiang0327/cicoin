package com.sskj.cicoin;

import com.lzy.okgo.OkGo;
import com.sskj.common.base.BasePresenter;
import com.sskj.cicoin.CoinFragment;
import com.sskj.common.data.CoinBean;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;

import java.util.List;


/**
 * @author Hey
 * Create at  2019/09/04 10:14:24
 */
class CoinPresenter extends BasePresenter<CoinFragment> {
    public void getMarketList() {
        OkGo.<HttpResult<List<CoinBean>>>get(HttpConfig.BASE_URL + HttpConfig.GET_PRODUCT)
                .execute(new JsonCallBack<HttpResult<List<CoinBean>>>(this) {
                    @Override
                    protected void onNext(HttpResult<List<CoinBean>> result) {
                        mView.setData(result.getData());
                    }
                });
    }
}
