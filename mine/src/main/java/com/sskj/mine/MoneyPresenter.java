package com.sskj.mine;

import com.lzy.okgo.OkGo;
import com.lzy.okrx2.adapter.FlowableBody;
import com.sskj.common.base.BasePresenter;
import com.sskj.common.http.BaseHttpConfig;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;
import com.sskj.common.http.JsonConvert;
import com.sskj.common.http.Page;
import com.sskj.mine.MoneyActivity;
import com.sskj.mine.data.CommissionBean;

import java.util.List;

import io.reactivex.Flowable;


/**
 * @author Hey
 * Create at  2019/09/05 09:37:33
 */
class MoneyPresenter extends BasePresenter<MoneyActivity> {
    public Flowable<List<CommissionBean>> getCommsion(int page, int size) {
        return OkGo.<HttpResult<Page<CommissionBean>>>get(BaseHttpConfig.BASE_URL + HttpConfig.COMISSION)
                .params("p", page)
                .params("size", size)
                .tag(this)
                .converter(new JsonConvert<HttpResult<Page<CommissionBean>>>() {
                })
                .adapt(new FlowableBody<>())
                .map(pageHttpResult -> {
                    return pageHttpResult.getData().getRes();
                });
    }

}
