package com.sskj.miner.ui.activity;

import com.lzy.okgo.OkGo;
import com.sskj.common.base.BasePresenter;
import com.sskj.common.http.BaseHttpConfig;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;
import com.sskj.miner.bean.UsdtBean;
import com.sskj.miner.ui.activity.SyActivity;


/**
 * @author Hey
 * Create at  2019/09/05 18:20:34
 */
class SyPresenter extends BasePresenter<SyActivity> {
    public void getUsdt(int page, int size) {
        OkGo.<HttpResult<UsdtBean>>get(BaseHttpConfig.BASE_URL + HttpConfig.USDT)
                .params("P", page)
                .params("size", size)
                .execute(new JsonCallBack<HttpResult<UsdtBean>>() {
                    @Override
                    protected void onNext(HttpResult<UsdtBean> result) {
                        if (result.getStatus()==BaseHttpConfig.OK)
                        {
                            mView.setUsdt(result.getData());
                        }
                    }
                });
    }

}
