package com.sskj.mine;

import com.lzy.okgo.OkGo;
import  com.sskj.common.base.BasePresenter;
import com.sskj.common.http.BaseHttpConfig;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;
import com.sskj.mine.InviteHomeActivity;
import com.sskj.mine.data.InvateBean;


/**
 * @author Hey
 * Create at  2019/09/05 08:58:17
 */
class InviteHomePresenter extends BasePresenter<InviteHomeActivity> {

    public void getInvate(){
        OkGo.<HttpResult<InvateBean>>get(BaseHttpConfig.BASE_URL+ HttpConfig.INV)
                .execute(new JsonCallBack<HttpResult<InvateBean>>() {
                    @Override
                    protected void onNext(HttpResult<InvateBean> result) {
                        mView.getInv(result.getData());
                    }
                });
    }
}
