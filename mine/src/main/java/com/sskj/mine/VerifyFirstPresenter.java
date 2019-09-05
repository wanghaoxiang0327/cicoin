package com.sskj.mine;

import com.lzy.okgo.OkGo;
import com.sskj.common.base.BasePresenter;
import com.sskj.common.http.BaseHttpConfig;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;
import com.sskj.common.utils.SpUtil;
import com.sskj.mine.VerifyFirstActivity;
import com.sskj.mine.data.FirstBean;


/**
 * @author Hey
 * Create at  2019/09/04 21:11:04
 */
class VerifyFirstPresenter extends BasePresenter<VerifyFirstActivity> {

    public void firstVerify(String name, String idcard) {
        OkGo.<HttpResult<FirstBean>>post(BaseHttpConfig.BASE_URL + HttpConfig.VERIFY_FIRST)
                .params("realname", name)
                .params("idcard", idcard)
                .params("type","1")
                .execute(new JsonCallBack<HttpResult<FirstBean>>() {
                    @Override
                    protected void onNext(HttpResult<FirstBean> result) {
                        SpUtil.put("mine_name", result.getData().getName());
                        SpUtil.put("mine_idno", result.getData().getIdcard());
                        mView.verifySuccess();

                    }
                });
    }

}
