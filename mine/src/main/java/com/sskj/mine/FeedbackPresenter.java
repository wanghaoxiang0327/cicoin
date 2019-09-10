package com.sskj.mine;

import com.hjq.toast.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.sskj.common.App;
import com.sskj.common.base.BasePresenter;
import com.sskj.common.http.BaseHttpConfig;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.HttpsUtil;
import com.sskj.common.http.JsonCallBack;
import com.sskj.mine.FeedbackActivity;


/**
 * @author Hey
 * Create at  2019/09/05 10:46:48
 */
class FeedbackPresenter extends BasePresenter<FeedbackActivity> {
    public void sendRequest(String content, String contact) {
        OkGo.<HttpResult<Object>>post(HttpConfig.BASE_URL + HttpConfig.FEEDBACK)
                .params("des", content)
                .params("mobile", contact)
                .execute(new JsonCallBack<HttpResult<Object>>(this) {
                    @Override
                    protected void onNext(HttpResult<Object> result) {
                        if (result.getStatus() == BaseHttpConfig.OK) {
                            mView.success();
                            ToastUtils.show(App.INSTANCE.getString(R.string.mine_czcgaa));
                        }
                    }
                });
    }
}
