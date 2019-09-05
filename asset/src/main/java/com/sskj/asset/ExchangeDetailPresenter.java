package com.sskj.asset;

import com.lzy.okgo.OkGo;
import com.lzy.okrx2.adapter.FlowableBody;
import com.sskj.asset.data.ExchangeListEntity;
import com.sskj.asset.data.TransferInfoBean;
import com.sskj.common.base.BasePresenter;
import com.sskj.asset.ExchangeDetailActivity;
import com.sskj.common.http.BaseHttpConfig;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;
import com.sskj.common.http.JsonConvert;
import com.sskj.common.http.Page;

import java.util.List;

import io.reactivex.Flowable;


/**
 * @author Hey
 * Create at  2019/09/05 18:45:54
 */
class ExchangeDetailPresenter extends BasePresenter<ExchangeDetailActivity> {
    public Flowable<List<ExchangeListEntity.Exchange>> getExchangeDetail(String p, String size) {
        return OkGo.<HttpResult<Page<ExchangeListEntity.Exchange>>>post(BaseHttpConfig.BASE_URL + HttpConfig.EXCHANGE_DETAIL_LIST)
                .params("p", p)
                .params("size", size)
                .converter(new JsonConvert<HttpResult<Page<ExchangeListEntity.Exchange>>>() {
                })
                .adapt(new FlowableBody<>())
                .map(pageHttpResult -> {
                    return pageHttpResult.getData().getRes();
                });
    }
}
