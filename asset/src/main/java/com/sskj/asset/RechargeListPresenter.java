package com.sskj.asset;

import com.lzy.okgo.OkGo;
import com.sskj.asset.data.RechargeListBean;
import com.sskj.common.base.BasePresenter;
import com.sskj.asset.RechargeListActivity;
import com.sskj.common.data.CoinAsset;
import com.sskj.common.http.BaseHttpConfig;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;

import java.util.List;


/**
 * @author Hey
 * Create at  2019/09/06 01:01:36
 */
class RechargeListPresenter extends BasePresenter<RechargeListActivity> {
    public void getList(String pid, int p, int size) {
        OkGo.<HttpResult<RechargeListBean>>get(BaseHttpConfig.BASE_URL + HttpConfig.ASSET_RECORDLIST)
                .params("P", p)
                .params("pid", pid)
                .params("size", size)
                .execute(new JsonCallBack<HttpResult<RechargeListBean>>() {
                    @Override
                    protected void onNext(HttpResult<RechargeListBean> result) {
                        if (result.getStatus() == BaseHttpConfig.OK) {
                            mView.setList(result.getData().getRes());
                        }
                    }
                });

    }

    /**
     * 币种分类
     *
     * @return
     */
    public void getCoinAsset(boolean showDialog) {
        OkGo.<HttpResult<List<CoinAsset>>>get(BaseHttpConfig.BASE_URL + HttpConfig.COINASSET)
                .execute(new JsonCallBack<HttpResult<List<CoinAsset>>>() {
                    @Override
                    protected void onNext(HttpResult<List<CoinAsset>> result) {
                        if (result != null && result.getData() != null) {
                            if (!showDialog) {
                                mView.setCoinList(result.getData());
                            } else {
                                mView.showCoinDialog(result.getData());
                            }
                        }
                    }
                });
    }

}
