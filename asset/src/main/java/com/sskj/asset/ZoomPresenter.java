package com.sskj.asset;

import com.hjq.toast.ToastUtils;
import com.lzy.okgo.OkGo;
import com.sskj.asset.data.GAssetBean;
import com.sskj.asset.data.TransferInfoBean;
import com.sskj.common.base.BasePresenter;
import com.sskj.asset.ZoomActivity;
import com.sskj.common.data.CoinListEntity;
import com.sskj.common.http.BaseHttpConfig;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;

import java.util.List;


/**
 * @author Hey
 * Create at  2019/08/23 14:57:15
 */
class ZoomPresenter extends BasePresenter<ZoomActivity> {
    public void getTransfer(String coin, String ex_coin, String num, String tpwd) {
        OkGo.<HttpResult<TransferInfoBean>>post(BaseHttpConfig.BASE_URL + HttpConfig.GET_TRANSFER)
                .params("coin", coin)
                .params("ex_coin", ex_coin)
                .params("num", num)
                .params("tpwd", tpwd)
                .execute(new JsonCallBack<HttpResult<TransferInfoBean>>(this) {
                    @Override
                    protected void onNext(HttpResult<TransferInfoBean> result) {
//                        mView.updateui(result.getData());
                    }
                });
    }


    public void getExchangeInfo() {
        OkGo.<HttpResult<List<CoinListEntity>>>post(BaseHttpConfig.BASE_URL + HttpConfig.GET_COINLIST)
                .execute(new JsonCallBack<HttpResult<List<CoinListEntity>>>(this) {
                    @Override
                    protected void onNext(HttpResult<List<CoinListEntity>> result) {
                        mView.setCoinList(result.getData());
                    }
                });
    }

    public void Transfer(int type, float num) {
        OkGo.<HttpResult<Object>>post(BaseHttpConfig.BASE_URL + HttpConfig.ASSETTRANSFER)
                .params("type", type)
                .params("num", num)
                .execute(new JsonCallBack<HttpResult<Object>>(this) {
                    @Override
                    protected void onNext(HttpResult<Object> result) {
                        if (result.getStatus() == 200) {
                            ToastUtils.show(result.getMsg());
//                            mView.transfersuccess();
                        }
                    }
                });
    }
}
