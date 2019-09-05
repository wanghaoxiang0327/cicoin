package com.sskj.mine;

import com.lzy.okgo.OkGo;
import  com.sskj.common.base.BasePresenter;
import com.sskj.common.http.BaseHttpConfig;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;
import com.sskj.mine.MoneyActivity;
import com.sskj.mine.data.CommissionBean;


/**
 * @author Hey
 * Create at  2019/09/05 09:37:33
 */
class MoneyPresenter extends BasePresenter<MoneyActivity> {
    public void getCommsion(int page,int size){
        OkGo.<HttpResult<CommissionBean>>get(BaseHttpConfig.BASE_URL+ HttpConfig.COMISSION)
                .params("p",page)
                .params("size",size)
                .execute(new JsonCallBack<HttpResult<CommissionBean>>(this) {
                    @Override
                    protected void onNext(HttpResult<CommissionBean> result) {
                        mView.setCommission(result.getData());
                    }
                });
    }

}
