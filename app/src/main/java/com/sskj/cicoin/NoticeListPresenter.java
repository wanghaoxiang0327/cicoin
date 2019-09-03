package com.sskj.cicoin;

import com.lzy.okgo.OkGo;
import com.sskj.common.App;
import com.sskj.common.base.BasePresenter;
import com.sskj.cicoin.NoticeListActivity;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;
import com.sskj.common.http.Page;
import com.sskj.common.language.LocalManageUtil;


/**
 * @author Hey
 * Create at  2019/09/03 15:27:18
 */
class NoticeListPresenter extends BasePresenter<NoticeListActivity> {
    public void getNoticeList(String page) {
        OkGo.<HttpResult<Page<NoticeBean>>>post(HttpConfig.BASE_URL + HttpConfig.NOTICE_LIST)
                .params("p", page)
                .params("size", "10")
                .params("lang", LocalManageUtil.getLanguage(App.INSTANCE))
                .execute(new JsonCallBack<HttpResult<Page<NoticeBean>>>(this) {
                    @Override
                    protected void onNext(HttpResult<Page<NoticeBean>> result) {
                        mView.setNotice(result.getData());
                    }
                });

    }
}
