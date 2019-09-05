package com.sskj.contact;

import com.lzy.okgo.OkGo;
import com.sskj.common.base.BasePresenter;
import com.sskj.common.http.BaseHttpConfig;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;
import com.sskj.contact.ShareActivity;
import com.sskj.contact.data.ShareInfo;


/**
 * @author Hey
 * Create at  2019/09/05 08:56:36
 */
class SharePresenter extends BasePresenter<ShareActivity> {
    public void getShareInfo() {
        OkGo.<HttpResult<ShareInfo>>get(BaseHttpConfig.BASE_URL + HttpConfig.SHARE_INFO)
                .execute(new JsonCallBack<HttpResult<ShareInfo>>(this) {
                    @Override
                    protected void onNext(HttpResult<ShareInfo> result) {
                        mView.setShareInfo(result.getData());
                    }
                });
    }
}
