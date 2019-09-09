package com.sskj.mine;

import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.sskj.common.base.BasePresenter;
import com.sskj.common.http.BaseHttpConfig;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;
import com.sskj.common.user.data.UserBean;
import com.sskj.mine.MineFragment;
import com.sskj.mine.data.MIneBean;

import java.util.List;
import java.util.Map;


/**
 * @author Hey
 * Create at  2019/06/24
 */
public class MinePresenter extends BasePresenter<MineFragment> {

    public void getMoney() {
        OkGo.<HttpResult<MIneBean>>get(BaseHttpConfig.BASE_URL + HttpConfig.ASSET)
                .execute(new JsonCallBack<HttpResult<MIneBean>>() {
                    @Override
                    protected void onNext(HttpResult<MIneBean> result) {
                        Log.d("yds", "走了这"+result.getData().getTtl_money()+"-----------------"+result.getData().getTtl_cnymoney());
                        mView.getSuccess(result.getData().getTtl_money(), result.getData().getTtl_cnymoney());
                    }

                    @Override
                    public void onError(Response<HttpResult<MIneBean>> response) {
                        super.onError(response);
                        mView.getFailed(0, 0);
                    }
                });
    }

    public void about() {
        OkGo.<HttpResult<Map<String, String>>>get(BaseHttpConfig.BASE_URL + HttpConfig.ABOUT_US)
                .execute(new JsonCallBack<HttpResult<Map<String, String>>>(this,false) {
                    @Override
                    protected void onNext(HttpResult<Map<String, String>> result) {
                        mView.about(result.getData().get("value"));
                    }
                });
    }


    public void qd() {
        OkGo.<HttpResult<List<String>>>get(BaseHttpConfig.BASE_URL + HttpConfig.QD)
                .execute(new JsonCallBack<HttpResult<List<String>>>(this,false) {
                    @Override
                    protected void onNext(HttpResult<List<String>> result) {
                        mView.qd(result.getData().size() > 0 ? result.getData().get(0) : "0");
                    }
                });
    }

}
