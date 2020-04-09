package com.sskj.contact.dialog;

import com.hjq.toast.ToastUtils;
import com.lzy.okgo.OkGo;
import com.sskj.common.base.BasePresenter;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;
import com.sskj.contact.data.PointInfo;

public class OrderSettingDialogPresenter extends BasePresenter<ContactOrderSettingDialog> {



    public void setPoint(String order_id,String zy,String zs){
        OkGo.<HttpResult<PointInfo>> post(HttpConfig.BASE_URL+HttpConfig.CONTACT_SET_POINT)
                .params("h_id",order_id)
                .params("zy",zy)
                .params("zs",zs)
                .tag(this)
                .execute(new JsonCallBack<HttpResult<PointInfo>>(this) {
                    @Override
                    protected void onNext(HttpResult<PointInfo> result) {
                        ToastUtils.show(result.getMsg());
                        mView.setPointSuccess();
                    }
                });

    }

}
