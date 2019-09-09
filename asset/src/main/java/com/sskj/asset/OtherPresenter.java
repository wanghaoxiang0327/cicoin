package com.sskj.asset;

import com.lzy.okgo.OkGo;
import com.lzy.okrx2.adapter.FlowableBody;
import com.sskj.asset.data.OtherRecordEntity;
import com.sskj.common.App;
import com.sskj.common.base.BasePresenter;
import com.sskj.asset.OtherFragment;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonConvert;
import com.sskj.common.http.Page;
import com.sskj.common.language.LocalManageUtil;

import java.util.List;

import io.reactivex.Flowable;


/**
 * @author Hey
 * Create at  2019/09/05 21:26:00
 */
class OtherPresenter extends BasePresenter<OtherFragment> {
    public Flowable<List<OtherRecordEntity>> getOtherDetailList(int page, int size, String pid) {
        return OkGo.<HttpResult<Page<OtherRecordEntity>>>post(HttpConfig.BASE_URL + HttpConfig.GET_OTHER_RECORD)
                .params("p", page)
                .params("pid", pid)
                .params("size", size)
                .params("lang", LocalManageUtil.getLanguage(App.INSTANCE))
                .converter(new JsonConvert<HttpResult<Page<OtherRecordEntity>>>() {
                })
                .adapt(new FlowableBody<>())
                .map(pageHttpResult -> {
                    return pageHttpResult.getData().getRes();
                });

    }
}
