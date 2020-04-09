package com.sskj.asset;

import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okrx2.adapter.FlowableBody;
import com.sskj.asset.data.WithDrawEntity;
import com.sskj.common.App;
import com.sskj.common.base.BasePresenter;
import com.sskj.asset.CoinFragment;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonConvert;
import com.sskj.common.http.Page;
import com.sskj.common.language.LocalManageUtil;

import java.util.List;

import io.reactivex.Flowable;


/**
 * @author Hey
 * Create at  2019/09/05 21:00:01
 */
class CoinPresenter extends BasePresenter<CoinFragment> {
    public Flowable<List<RechargeEntity>> getRechareDetailList(int page, int size, String pid) {
        return OkGo.<HttpResult<Page<RechargeEntity>>>post(HttpConfig.BASE_URL + HttpConfig.GET_RECHARGE_RECORD)
                .params("p", page)
                .params("s", size)
                .params("pid", pid)
                .params("lang", LocalManageUtil.getLanguage(App.INSTANCE))
                .converter(new JsonConvert<HttpResult<Page<RechargeEntity>>>() {
                })
                .adapt(new FlowableBody<>())
                .map(pageHttpResult -> {
                    return pageHttpResult.getData().getRes();
                });

    }

    public Flowable<List<WithDrawEntity>> getWithDrawDetailList(int page, int size, String pid) {
        return OkGo.<HttpResult<Page<WithDrawEntity>>>post(HttpConfig.BASE_URL + HttpConfig.GET_WITHDRAW_RECORD)
                .params("p", page)
                .params("s", size)
                .params("pid", pid)
                .params("lang", LocalManageUtil.getLanguage(App.INSTANCE))
                .converter(new JsonConvert<HttpResult<Page<WithDrawEntity>>>() {
                })
                .adapt(new FlowableBody<>())
                .map(pageHttpResult -> {
                    return pageHttpResult.getData().getRes();

                });

    }
}
