package com.sskj.mine;

import com.lzy.okgo.OkGo;
import com.sskj.common.base.BasePresenter;
import com.sskj.common.http.BaseHttpConfig;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;
import com.sskj.common.user.data.UserBean;
import com.sskj.mine.MineFragment;
import com.sskj.mine.data.MIneBean;


/**
 * @author Hey
 * Create at  2019/06/24
 */
public class MinePresenter extends BasePresenter<MineFragment> {

    public void getMoney(UserBean bean) {
        OkGo.<HttpResult<MIneBean>>get(BaseHttpConfig.BASE_URL + HttpConfig.ASSET)
                .execute(new JsonCallBack<HttpResult<MIneBean>>() {
                    @Override
                    protected void onNext(HttpResult<MIneBean> result) {
                        mView.getSuccess( bean, result.getData().getTtl_money(),result.getData().getTtl_cnymoney());
                    }
                });

    }

}
