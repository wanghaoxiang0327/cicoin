package com.sskj.cicoin;

import com.lzy.okgo.OkGo;
import com.sskj.common.App;
import com.sskj.common.base.BasePresenter;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;
import com.sskj.common.language.LocalManageUtil;


/**
 * @author Hey
 * Create at  2019/06/26
 */
public class NewsDetailPresenter extends BasePresenter<NewsDetailActivity> {
    public void getNoticeDetail(String id) {
        OkGo.<HttpResult<NewsBean>>post(HttpConfig.BASE_URL + HttpConfig.NOTICE_DETAIL)
                .params("id", id)
                .execute(new JsonCallBack<HttpResult<NewsBean>>(this) {
                    @Override
                    protected void onNext(HttpResult<NewsBean> result) {
                        mView.setNoticeDetail(result.getData());
                    }
                });

    }

    public void getSysInfoDetial(String id) {
        OkGo.<HttpResult<NewsBean>>post(HttpConfig.BASE_URL + HttpConfig.SYSINFO_DETAIL)
                .params("id", id)
                .execute(new JsonCallBack<HttpResult<NewsBean>>(this) {
                    @Override
                    protected void onNext(HttpResult<NewsBean> result) {
                        mView.setNoticeDetail(result.getData());
                    }
                });

    }

}
