package com.sskj.contact;

import com.lzy.okgo.OkGo;
import com.sskj.common.base.BasePresenter;
import com.sskj.common.data.CoinAsset;
import com.sskj.common.data.CoinBean;
import com.sskj.common.http.BaseHttpConfig;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpObserver;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;
import com.sskj.common.http.RxUtils;
import com.sskj.contact.ContractFragment;

import java.util.List;


/**
 * @author Hey
 * Create at  2019/08/22 17:36:31
 */
class ContractPresenter extends BasePresenter<ContractFragment> {



    public void getMarketList(boolean showDialog) {
        OkGo.<HttpResult<List<CoinBean>>>get(HttpConfig.BASE_URL + HttpConfig.GET_PRODUCT)
                .execute(new JsonCallBack<HttpResult<List<CoinBean>>>(this) {
                    @Override
                    protected void onNext(HttpResult<List<CoinBean>> result) {
                        mView.setCoinList(result.getData(),showDialog);
                    }
                });

    }
}
