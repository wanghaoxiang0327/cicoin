package com.sskj.mine;

import com.lzy.okgo.OkGo;
import  com.sskj.common.base.BasePresenter;
import com.sskj.common.http.BaseHttpConfig;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;
import com.sskj.mine.TeamActivity;
import com.sskj.mine.data.TeamBean;


/**
 * @author Hey
 * Create at  2019/09/05 09:37:16
 */
class TeamPresenter extends BasePresenter<TeamActivity> {
    public void getTeam(int page,int size){
        OkGo.<HttpResult<TeamBean>>get(BaseHttpConfig.BASE_URL+ HttpConfig.MY_TEAM)
                .params("p",page)
                .params("size",size)
                .execute(new JsonCallBack<HttpResult<TeamBean>>(this) {
                    @Override
                    protected void onNext(HttpResult<TeamBean> result) {
                        mView.setTeam(result.getData());
                    }
                });
    }
}


