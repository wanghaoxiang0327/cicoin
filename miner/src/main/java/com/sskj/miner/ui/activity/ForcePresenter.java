package com.sskj.miner.ui.activity;

import com.lzy.okgo.OkGo;
import com.sskj.common.base.BasePresenter;
import com.sskj.common.http.BaseHttpConfig;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;
import com.sskj.miner.bean.ForceBean;
import com.sskj.miner.ui.activity.ForceActivity;


/**
 * @author Hey
 * Create at  2019/09/05 18:20:14
 */
class ForcePresenter extends BasePresenter<ForceActivity> {

    public void getforce(int page,int size) {
        OkGo.<HttpResult<ForceBean>>get(BaseHttpConfig.BASE_URL+ HttpConfig.YL)
                .params("P",page)
                .params("size",size)
                .execute(new JsonCallBack<HttpResult<ForceBean>>() {
                    @Override
                    protected void onNext(HttpResult<ForceBean> result) {
                        if (result.getStatus()==BaseHttpConfig.OK){
                            mView.setForce(result.getData());
                        }
                    }
                });
    }

}
