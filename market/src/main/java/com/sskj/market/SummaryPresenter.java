package com.sskj.market;

import com.lzy.okgo.OkGo;
import com.sskj.common.base.BasePresenter;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;
import com.sskj.market.data.Summary;

/**
 * @author Hey
 * Created by Administrator on 17点21分.
 */

public class SummaryPresenter extends BasePresenter<SummaryFragment> {


    public void getData(String code) {
        OkGo.<HttpResult<Summary>>get(HttpConfig.BASE_URL + HttpConfig.CODE_INFO)
                .params("code", code)
                .execute(new JsonCallBack<HttpResult<Summary>>(this) {
                    @Override
                    protected void onNext(HttpResult<Summary> result) {
                        mView.setData(result.getData());
                    }
                });
    }

}
