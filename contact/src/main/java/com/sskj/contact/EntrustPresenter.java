package com.sskj.contact;

import com.hjq.toast.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okrx2.adapter.FlowableBody;
import com.sskj.common.base.BasePresenter;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;
import com.sskj.common.http.JsonConvert;
import com.sskj.common.http.Page;
import com.sskj.contact.EntrustFragment;
import com.sskj.contact.data.EntrustOrder;
import com.sskj.contact.data.HoldOrder;

import java.util.List;

import io.reactivex.Flowable;


/**
 * @author Hey
 * Create at  2019/08/26 16:58:03
 */
public class EntrustPresenter extends BasePresenter<EntrustFragment> {
    public Flowable<List<EntrustOrder>> getEntrustOrder(int page, int size, String code) {
        return OkGo.<HttpResult<Page<EntrustOrder>>>post(HttpConfig.BASE_URL + HttpConfig.GET_ENTRUST_ORDER)
                .params("p", page)
                .params("size", size)
                .params("code", code)
                .tag(this)
                .converter(new JsonConvert<HttpResult<Page<EntrustOrder>>>() {
                })
                .adapt(new FlowableBody<>())
                .map(pageHttpResult -> {
                    return pageHttpResult.getData().getRes();
                });

    }

    public void cancelOrder(String order_id) {
        OkGo.<HttpResult<Object>>post(HttpConfig.BASE_URL + HttpConfig.CONTACT_CANCEL_ORDER)
                .params("order_id", order_id)
                .tag(this)
                .execute(new JsonCallBack<HttpResult<Object>>(this) {
                    @Override
                    protected void onNext(HttpResult<Object> result) {
                        ToastUtils.show(result.getMsg());
                        mView.cancelOrderSuccess();
                    }
                });

    }
}
