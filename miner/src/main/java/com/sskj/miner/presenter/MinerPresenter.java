package com.sskj.miner.presenter;

import android.view.View;

import com.lzy.okgo.OkGo;
import com.sskj.common.base.BasePresenter;
import com.sskj.common.data.WaterBean;
import com.sskj.common.http.BaseHttpConfig;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;
import com.sskj.miner.bean.ForceDescriptionBean;
import com.sskj.miner.bean.TotalAsset;
import com.sskj.miner.ui.fragment.MinerFragment;

import java.util.List;

/**
 * 项目包名：com.sskj.miner.presenter
 * 项目所属模块：miner
 * 作者：布兜兜不打豆豆
 * 创建时间：2019年09月02日
 * 类描述：挖矿相关presenter
 * 备注：
 */
public class MinerPresenter extends BasePresenter<MinerFragment> {

    //获取佣金详情
    public void getAsset() {
        OkGo.<HttpResult<TotalAsset>>get(BaseHttpConfig.BASE_URL + HttpConfig.GET_ASSET)
                .execute(new JsonCallBack<HttpResult<TotalAsset>>(this, false) {
                    @Override
                    protected void onNext(HttpResult<TotalAsset> result) {
                        if (result.getStatus() == BaseHttpConfig.OK) {
                            mView.updateUi(result.getData());
                        }
                    }
                });
    }

    /**
     * 跑马灯
     */
    public void getNotices() {
        OkGo.<HttpResult<List<String>>>get(BaseHttpConfig.BASE_URL + HttpConfig.NOTICE)
                .execute(new JsonCallBack<HttpResult<List<String>>>(this, false) {
                    @Override
                    protected void onNext(HttpResult<List<String>> result) {
                        if (result.getStatus() == BaseHttpConfig.OK) {
                            mView.notice(result.getData());
                        }
                    }
                });

    }

    /**
     * 获取规则
     */
    public void getRule() {
        OkGo.<HttpResult<ForceDescriptionBean>>post(BaseHttpConfig.BASE_URL + HttpConfig.FORCE_DESCRIPTION)
                .execute(new JsonCallBack<HttpResult<ForceDescriptionBean>>(this, false) {

                    @Override
                    protected void onNext(HttpResult<ForceDescriptionBean> result) {
                        if (result.getData() != null) {
                            mView.showNotice(result.getData());
                        }
                    }
                });
    }

    /**
     * 获取气泡
     */
    public void getPao() {
        OkGo.<HttpResult<List<WaterBean>>>get(BaseHttpConfig.BASE_URL + HttpConfig.GET_PAO)
                .execute(new JsonCallBack<HttpResult<List<WaterBean>>>(this, false) {
                    @Override
                    protected void onNext(HttpResult<List<WaterBean>> result) {
                        if (result.getStatus() == BaseHttpConfig.OK) {
                            mView.updatePao(result.getData());
                        }
                    }
                });
    }

    /**
     * 移除气泡
     *
     * @param id   id
     * @param view view
     */
    public void receivePao(String id, View view) {
        OkGo.<HttpResult>post(BaseHttpConfig.BASE_URL + HttpConfig.RECEIVE_PAO)
                .params("pid", id)
                .params("type", "usdt")
                .execute(new JsonCallBack<HttpResult>(this, false) {
                    @Override
                    protected void onNext(HttpResult result) {
                        if (result.getStatus() == BaseHttpConfig.OK) {
                            mView.removeWaterView(view);
                        }
                    }
                });
    }
}
