package com.sskj.market;

import com.lzy.okgo.OkGo;
import com.sskj.common.base.BasePresenter;
import com.sskj.common.http.BaseHttpConfig;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;
import com.sskj.common.http.Page;
import com.sskj.market.data.HoldBean;
import com.sskj.market.data.Stock;
import com.sskj.market.http.MarketHttp;

import java.util.List;


/**
 * @author Hey
 * Create at  2019/05/22
 */
class ChartPresenter extends BasePresenter<ChartFragment> {
    /**
     * 获取股票K线
     *
     * @param goodsType K线周期(minute/minute5/minute15/minute30/minute60/day)
     * @param code      (BCH_USDT/ETC_USDT/ETH_USDT/BTC_USDT/LTC_USDT )
     */
    public void getStockInfo( String goodsType, String code) {
        OkGo.<HttpResult<List<Stock>>>get(BaseHttpConfig.BASE_URL + MarketHttp.GOODS_INFO)
                .params("goodsType", goodsType)
                .params("code", code)
                .execute(new JsonCallBack<HttpResult<List<Stock>>>() {
                    @Override
                    protected void onNext(HttpResult<List<Stock>> result) {
                        mView.setChartData(result.getData());
                    }
                });
    }

//    public void getOrder(String code) {
//        OkGo.<HttpResult<Page<HoldBean>>>post(BaseHttpConfig.BASE_URL + HttpConfig.MYORDER)
//                .params("state", 1)
//                .params("p", 1)
//                .params("size", 100)
//                .params("pid", code)
//                .execute(new JsonCallBack<HttpResult<Page<HoldBean>>>() {
//                    @Override
//                    protected void onNext(HttpResult<Page<HoldBean>> result) {
//                        mView.setHoldOrder(result.getData());
//                    }
//                });
//
//    }
}
