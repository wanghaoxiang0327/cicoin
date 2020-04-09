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
import com.sskj.contact.data.HoldOrder;
import com.sskj.contact.data.PointInfo;

import java.util.List;

import io.reactivex.Flowable;


/**
 * @author Hey
 * Create at  2019/08/26 16:57:34
 */
class HoldPresenter extends BasePresenter<HoldFragment> {

    public Flowable<List<HoldOrder>> getHoldOrder(String code, int page, int size) {
        return OkGo.<HttpResult<Page<HoldOrder>>>post(HttpConfig.BASE_URL + HttpConfig.GET_HOLD_ORDER)
//                .params("code", code)
                .params("p", page)
                .params("size", size)
                .tag(this)
                .converter(new JsonConvert<HttpResult<Page<HoldOrder>>>() {
                })
                .adapt(new FlowableBody<>())
                .map(pageHttpResult -> {
                    return pageHttpResult.getData().getRes();
                });
    }

    public void closeOrder(String num, String order_id) {
        OkGo.<HttpResult<Object>>post(HttpConfig.BASE_URL + HttpConfig.CONTACT_CLOSE_ORDER)
                .params("num", num)
                .params("order_id", order_id)
                .tag(this)
                .execute(new JsonCallBack<HttpResult<Object>>(this) {
                    @Override
                    protected void onNext(HttpResult<Object> result) {
                        ToastUtils.show(result.getMsg());
                        mView.closeOrderSuccess();
                    }
                });

    }

    public void getPointInfo(HoldOrder order) {
        OkGo.<HttpResult<PointInfo>>post(HttpConfig.BASE_URL + HttpConfig.CONTACT_POINT_INFO)
                .params("pid", order.getPid())
                .tag(this)
                .execute(new JsonCallBack<HttpResult<PointInfo>>(this) {
                    @Override
                    protected void onNext(HttpResult<PointInfo> result) {
                        mView.setPointInfo(result.getData(), order);
                    }
                });

    }


}
