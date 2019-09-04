package com.sskj.miner.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.hjq.toast.ToastUtils;
import com.sskj.common.base.BaseFragment;
import com.sskj.common.data.WaterBean;
import com.sskj.common.utils.ClickUtil;
import com.sskj.common.view.WaterView;
import com.sskj.miner.R;
import com.sskj.miner.R2;
import com.sskj.miner.presenter.MinerPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Flowable;

/**
 * 项目包名：com.sskj.miner.ui.fragment
 * 项目所属模块：miner
 * 作者：布兜兜不打豆豆
 * 创建时间：2019年09月02日
 * 类描述：挖矿fragment展示
 * 备注：
 */
public class MinerFragment extends BaseFragment<MinerPresenter> {
    @BindView(R2.id.tv_rule_miner)
    TextView tvRuleMiner;
    @BindView(R2.id.tv_msg_miner)
    TextView tvMsgMiner;
    @BindView(R2.id.view_water_miner)
    WaterView viewWaterMiner;
    @BindView(R2.id.tv_jy_miner)
    TextView tvJyMiner;
    @BindView(R2.id.tv_details_miner)
    TextView tvDetailsMiner;
    @BindView(R2.id.tv_money_miner)
    TextView tvMoneyMiner;

    @Override
    protected int getLayoutId() {
        return R.layout.miner_fragment_home;
    }

    @Override
    protected MinerPresenter getPresenter() {
        return new MinerPresenter();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        ClickUtil.click(tvRuleMiner, view -> ToastUtils.show("规则"));
        ClickUtil.click(tvDetailsMiner, view -> ToastUtils.show("查看详情"));
        ClickUtil.click(tvMoneyMiner, view -> {
            ToastUtils.show("收益详情");
        });
        List<WaterBean> beans = new ArrayList<>();
        beans.add(new WaterBean("12"));
        beans.add(new WaterBean("123"));
        beans.add(new WaterBean("11"));
        beans.add(new WaterBean("21"));
        beans.add(new WaterBean("61"));
        beans.add(new WaterBean("671"));
        beans.add(new WaterBean("1"));
        Flowable.timer(10, TimeUnit.SECONDS).subscribe(aLong -> viewWaterMiner.setWaters(beans));
    }

    @Override
    public void initData() {

    }

    @Override
    public void loadData() {

    }


    public static MinerFragment newInstance() {
        MinerFragment fragment = new MinerFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

}
