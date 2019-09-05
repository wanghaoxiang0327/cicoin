package com.sskj.miner.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.gyf.barlibrary.ImmersionBar;
import com.hjq.toast.ToastUtils;
import com.sskj.common.App;
import com.sskj.common.base.BaseFragment;
import com.sskj.common.data.WaterBean;
import com.sskj.common.dialog.TipsNewDialog;
import com.sskj.common.rxbus.RxBus;
import com.sskj.common.utils.ClickUtil;
import com.sskj.common.utils.TipUtil;
import com.sskj.common.view.WaterView;
import com.sskj.miner.R;
import com.sskj.miner.R2;
import com.sskj.miner.bean.BibPaoBean;
import com.sskj.miner.bean.IWaterBean;
import com.sskj.miner.bean.MinerAssetBean;
import com.sskj.miner.bean.PaoBean;
import com.sskj.miner.bean.TotalAsset;
import com.sskj.miner.bean.UsdtPaoBean;
import com.sskj.miner.presenter.MinerPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
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
        RxBus.getDefault().register(this);
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
        ClickUtil.click(tvDetailsMiner, view -> ToastUtils.show("查看详情"));
        ClickUtil.click(tvMoneyMiner, view -> {
            ToastUtils.show("收益详情");
        });
    }

    @Override
    public void loadData() {
        mPresenter.getNotices();
        mPresenter.getAsset();


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

    public void updatePao(List<PaoBean> data) {

        if (data == null) {
            return;
        }
        List<WaterBean> waterBeans = new ArrayList<>();
        for (PaoBean p : data) {
waterBeans.add(new WaterBean());
        }
//        viewWaterMiner.setWaters(iWaterBeans);

//        for (int i = 0; i < data.getUSDT().getNum(); i++) {
//            iWaterBeans.add(new UsdtPaoBean(data.getUSDT().getCode(), data.getUSDT().getEveryValue(), R.drawable.miner_icon_usdt_small));
//        }
//        for (int i = 0; i < data.getBIB().getNum(); i++) {
//            iWaterBeans.add(new BibPaoBean(data.getBIB().getCode(), data.getBIB().getEveryValue(), R.drawable.miner_icon_bib));
//        }
//        Flowable.fromIterable(data.getLargePao())
//                .filter(largePaoBean -> largePaoBean.getTaskComplete() != 3)
//                .toList()
//                .subscribe((largePaoBeans, throwable) -> {
//                    iWaterBeans.addAll(largePaoBeans);
//                    waterView.setWaters(iWaterBeans);
//                    waterView.setOnWaterViewClick((waterData, view) -> {
//                        Object tag = view.getTag();
//                        if (tag instanceof UsdtPaoBean) {
//                            UsdtPaoBean usdtPaoBean = (UsdtPaoBean) tag;
//                            mPresenter.receivePao(usdtPaoBean.getCode(), usdtPaoBean.getNum(), "2", null, view);
//                        } else if (tag instanceof BibPaoBean) {
//                            BibPaoBean usdtPaoBean = (BibPaoBean) tag;
//
//                            mPresenter.receivePao(usdtPaoBean.getCode(), usdtPaoBean.getNum(), "2", null, view);
//
//                        } else if (tag instanceof PaoBean.LargePaoBean) {
//
//                            PaoBean.LargePaoBean largePaoBean = (PaoBean.LargePaoBean) tag;
//                            if (largePaoBean.getTaskComplete() == 1) {
//                                showFail(largePaoBean);
//                                return;
//                            }
//                            mPresenter.receivePao("USDT", largePaoBean.getValue(), "1", largePaoBean.getType(), view);
//
//                        }
//                    });
//                });

    }

    private void showFail(PaoBean bean) {
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
                            tvMsgMiner.setText(title);
                        });
//                        .subscribe(i -> {
//                            if (tvMsgMiner != null) {
//                                tvMsgMiner.setText(notice.get((int) (i % notice.size());
//                            }
//                        }, System.out::println);
            }
        }
    }
}
