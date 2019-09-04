package com.sskj.market;

import com.lzy.okgo.OkGo;
import com.lzy.okrx2.adapter.FlowableBody;
import com.sskj.common.base.BasePresenter;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonConvert;
import com.sskj.common.http.Page;
import com.sskj.market.data.DealOrder;

import java.util.List;

import io.reactivex.Flowable;


/**
 * @author Hey
 * Create at  2019/08/01 16:57:04
 */
class TradePresenter extends BasePresenter<TradeFragment> {
    public Flowable<List<DealOrder>> getDealOrder(String code, int page, int size, String btime, String etime) {
        return OkGo.<HttpResult<Page<DealOrder>>>post(HttpConfig.BASE_URL + HttpConfig.GET_DEAL_ORDER)
                .params("code", code)
                .params("p", page)
                .params("size", size)
                .params("btime", btime)
                .params("etime", etime)
                .tag(this)
                .converter(new JsonConvert<HttpResult<Page<DealOrder>>>() {
                })
                .adapt(new FlowableBody<>())
                .map(pageHttpResult -> {
                    return pageHttpResult.getData().getRes();
                });

    }
}
