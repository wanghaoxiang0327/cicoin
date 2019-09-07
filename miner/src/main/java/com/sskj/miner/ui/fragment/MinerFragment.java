package com.sskj.miner.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.sskj.common.App;
import com.sskj.common.BaseApplication;
import com.sskj.common.base.BaseFragment;
import com.sskj.common.data.WaterBean;
import com.sskj.common.dialog.TipsNewDialog;
import com.sskj.common.router.RoutePath;
import com.sskj.common.rxbus.BusCode;
import com.sskj.common.rxbus.RxBus;
import com.sskj.common.user.data.UserBean;
import com.sskj.common.utils.ClickUtil;
import com.sskj.common.view.WaterView;
import com.sskj.miner.R;
import com.sskj.miner.R2;
import com.sskj.miner.bean.TotalAsset;
import com.sskj.miner.presenter.MinerPresenter;
import com.sskj.miner.ui.activity.ForceActivity;
import com.sskj.miner.ui.activity.SyActivity;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
    TextSwitcher tvMsgMiner;
    @BindView(R2.id.view_water_miner)
    WaterView viewWaterMiner;
    @BindView(R2.id.tv_yl_miner)
    TextView tvYlMiner;
    @BindView(R2.id.tv_jy_miner)
    TextView tvJyMiner;
    @BindView(R2.id.tv_details_miner)
    TextView tvDetailsMiner;
    @BindView(R2.id.tv_money_miner)
    TextView tvMoneyMiner;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.miner_fragment_home;
    }

    @Override
    protected MinerPresenter getPresenter() {
        return new MinerPresenter();
    }

    @Override
    public void initView() {
        tvMsgMiner.setFactory(() -> {
            TextView textView = new TextView(getActivity());
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            textView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
            textView.setGravity(Gravity.CENTER);
            textView.setSingleLine();
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setTextColor(ContextCompat.getColor(App.INSTANCE, R.color.common_white));
            return textView;
        });
    }

    @Override
    public void initData() {

        ClickUtil.click(tvRuleMiner, view -> mPresenter.getRule());
        ClickUtil.click(tvJyMiner, view -> {
            //交易
            ARouter.getInstance().build(RoutePath.MAIN).withInt("isOpenMore", 1).navigation();
        });
        ClickUtil.click(tvYlMiner, view -> {
            if (BaseApplication.isLogin()) {
                ForceActivity.start(getActivity());
            } else {
                ARouter.getInstance().build(RoutePath.LOGIN_LOGIN)
                        .navigation();
            }
        });
        ClickUtil.click(tvDetailsMiner, view -> {
                    if (BaseApplication.isLogin()) {
                        SyActivity.start(getActivity());
                    } else {
                        ARouter.getInstance().build(RoutePath.LOGIN_LOGIN)
                                .navigation();
                    }
                }
        );
        viewWaterMiner.setOnWaterViewClick((waterData, view) -> {
            if (!BaseApplication.isLogin()) {
                ARouter.getInstance().build(RoutePath.LOGIN_LOGIN)
                        .navigation();
                return;
            }
            if (Integer.parseInt(waterData.getStatus()) > 3) {
                new TipsNewDialog(getActivity())
                        .setTitle(App.INSTANCE.getString(R.string.miner_tixing))
                        .setContent(App.INSTANCE.getString(R.string.miner_tips_heyue))
                        .setConfirmText(App.INSTANCE.getString(R.string.miner_confirm_tips))
                        .setConfirmListener(dialog -> {
                            RxBus.getDefault().send(BusCode.SECOND);
                            dialog.dismiss();
                        })
                        .show();
                return;
            }
            mPresenter.receivePao(waterData.getId(), view);
        });
    }

    @Override
    public void loadData() {

    }

    @Override
    public void lazyLoad() {
        super.lazyLoad();
        mPresenter.getNotices();

        userViewModel.getUser().observe(this, userBean -> {
            if (userBean != null) {
                mPresenter.getAsset();
                mPresenter.getPao();
            }
        });
    }


    public static MinerFragment newInstance() {
        MinerFragment fragment = new MinerFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }


    @SuppressLint("SetTextI18n")
    public void updateUi(TotalAsset miningQPBean) {
        tvMoneyMiner.setText(TextUtils.isEmpty(miningQPBean.getUsdt()) ? "0" : miningQPBean.getUsdt());
        tvYlMiner.setText(App.INSTANCE.getString(R.string.miner_yuanli) + " " + miningQPBean.getForce());
    }


    public void removeWaterView(View view) {
        viewWaterMiner.removeWater(view);
    }


    public void showNotice(String content) {
        new TipsNewDialog(getContext())
                .setTitle(App.INSTANCE.getString(R.string.miner_miningActivity1))
                .setContent(content)
                .setConfirmText(App.INSTANCE.getString(R.string.miner_miningActivity2))
                .setConfirmListener(Dialog::dismiss).show();
    }


    public void updatePao(List<WaterBean> data) {
        if (data.size() == 0) {
            return;
        }
        viewWaterMiner.setWaters(data);

    }

    private void showFail(WaterBean bean) {
//        taskTip = TipUtil.getSureTip(this, App.INSTANCE.getString(R.string.miner_miningActivity3), largePaoBean.getTaskIntro(), App.INSTANCE.getString(R.string.miner_miningActivity4), () -> {
//
//            taskTip.dismiss();
//            switch (largePaoBean.getType()) {
//                case "1":
//                    RxBus.getDefault().send(RxBusCode.CHANGE_THIRD);
//                    finish();
//                    break;
//                case "2":
//                    ARouter.getInstance().build(RConfig.MINE_INVITE_URL).navigation();
//                    finish();
//                    break;
//                case "3":
//                case "4":
//                case "5":
//                    ARouter.getInstance().build(RConfig.MINE_RECHARGE).navigation();
//                    finish();
//                    break;
//            }
//        });
    }


    public void notice(List<String> notice) {
        if (tvMsgMiner != null) {
            if (notice.size() > 0) {
                Disposable noticeDispo = Flowable.interval(0, 5, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(aLong -> {
                            String title = notice.get((int) (aLong % notice.size()));
                            if (tvMsgMiner != null) {
                                tvMsgMiner.setText(title);
                            }
                        });
            }
        }
    }
}
