package com.sskj.contact;

import com.lzy.okgo.OkGo;
import com.sskj.common.base.BasePresenter;
import com.sskj.common.data.BuySellData;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;
import com.sskj.contact.PankouFragment;

import java.util.List;


/**
 * @author Hey
 * Create at  2019/08/26 14:36:33
 */
class PankouPresenter extends BasePresenter<PankouFragment> {

    public void getPankouData(String code) {
        OkGo.<HttpResult<List<BuySellData>>>get(HttpConfig.BASE_URL + HttpConfig.GET_PANKOU)
                .params("code", code)
                .tag(this)
                .execute(new JsonCallBack<HttpResult<List<BuySellData>>>(this) {
                    @Override
                    protected void onNext(HttpResult<List<BuySellData>> result) {
                        if (result.getData() != null && result.getData().size() > 0) {
                            mView.setPankouData(result.getData().get(0));
                        }
                    }
                });
    }

}
