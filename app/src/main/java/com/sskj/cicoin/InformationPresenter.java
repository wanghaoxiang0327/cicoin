package com.sskj.cicoin;

import com.lzy.okgo.OkGo;
import com.lzy.okrx2.adapter.FlowableBody;
import com.sskj.common.App;
import com.sskj.common.base.BasePresenter;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonConvert;
import com.sskj.common.http.Page;
import com.sskj.common.language.LocalManageUtil;

import java.util.List;

import io.reactivex.Flowable;


/**
 * @author Hey
 * Create at  2019/06/26
 */
public class InformationPresenter extends BasePresenter<InformationFragment> {
    public Flowable<List<NewsBean>> getNotice(int page,int size) {
        return OkGo.<HttpResult<Page<NewsBean>>>post(HttpConfig.BASE_URL + HttpConfig.NOTICE_LIST)
                .params("p",page)
                .params("size",size)
                .params("lang", LocalManageUtil.getLanguage(App.INSTANCE))
                .converter(new JsonConvert<HttpResult<Page<NewsBean>>>() {})
                .adapt(new FlowableBody<>())
                .map(pageHttpResult -> {
                    return pageHttpResult.getData().getRes();
                });

    }

    public Flowable<List<NewsBean>> getInformation(int page,int size) {
        return OkGo.<HttpResult<Page<NewsBean>>>post(HttpConfig.BASE_URL + HttpConfig.INFORMATION)
                .params("page",page)
                .params("size",size)
                .params("lang", LocalManageUtil.getLanguage(App.INSTANCE))
                .converter(new JsonConvert<HttpResult<Page<NewsBean>>>() {})
                .adapt(new FlowableBody<>())
                .map(pageHttpResult -> {
                    return pageHttpResult.getData().getRes();
                });

    }
}
