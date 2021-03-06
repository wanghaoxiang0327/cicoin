package com.sskj.mine;

import com.lzy.okgo.OkGo;
import com.sskj.common.base.BasePresenter;
import com.sskj.common.http.BaseHttpConfig;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;
import com.sskj.common.user.model.UserViewModel;
import com.sskj.mine.SecurityActivity;
import com.sskj.mine.data.GoogleInfo;


/**
 * @author Hey
 * Create at  2019/06/24
 */
public class SecurityPresenter extends BasePresenter<SecurityActivity> {
    /**
     * 获取谷歌信息
     */
    public void getGoogleInfo() {
        OkGo.<HttpResult<GoogleInfo>>post(BaseHttpConfig.BASE_URL + HttpConfig.GET_GOOGLE_INFO)
                .execute(new JsonCallBack<HttpResult<GoogleInfo>>(this) {
                    @Override
                    protected void onNext(HttpResult<GoogleInfo> result) {
                        mView.startGoogle(result.getData());
                    }
                });
    }


    public void switchGogle(String dyGoodleCommand, String mobile, String code, String act, UserViewModel userViewModel) {
        OkGo.<HttpResult>post(BaseHttpConfig.BASE_URL + HttpConfig.GOGLE)
                .params("dyGoodleCommand", dyGoodleCommand)
                .params("mobile", mobile)
                .params("code", code)
                .params("act", act)
                .execute(new JsonCallBack<HttpResult>() {
                    @Override
                    protected void onNext(HttpResult result) {
                        mView.switchSuccess();
                        userViewModel.update();

                    }
                });

    }
}
