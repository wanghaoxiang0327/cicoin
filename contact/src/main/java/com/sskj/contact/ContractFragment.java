package com.sskj.contact;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.sskj.common.BaseApplication;
import com.sskj.common.adapter.BaseAdapter;
import com.sskj.common.adapter.ViewHolder;
import com.sskj.common.base.BaseFragment;
import com.sskj.common.data.CoinBean;
import com.sskj.common.dialog.TipDialog;
import com.sskj.common.dialog.TipsNewDialog;
import com.sskj.common.event.ContactChangeCoin;
import com.sskj.common.router.RoutePath;
import com.sskj.common.rxbus.RxBus;
import com.sskj.common.rxbus.Subscribe;
import com.sskj.common.rxbus.ThreadMode;
import com.sskj.common.utils.ClickUtil;
import com.sskj.common.utils.CoinIcon;
import com.sskj.common.utils.DigitUtils;
import com.sskj.common.utils.NumberUtils;
import com.sskj.common.utils.TipUtil;
import com.sskj.contact.data.CoinInfo;

import java.util.List;

import butterknife.BindView;

/**
 * 合约交易
 *
 * @author Hey
 * Create at  2019/08/22 17:36:31
 */
public class ContractFragment extends BaseFragment<ContractPresenter> {
    @BindView(R2.id.drawLayout)
    DrawerLayout drawLayout;
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.tv_burst_rate)
    TextView tvBurstRate;
    @BindView(R2.id.iv_close)
    ImageView ivClose;
    @BindView(R2.id.tv_all_order)
    TextView tvAllOrder;
    @BindView(R2.id.tv_select_coin)
    TextView tvSelectCoin;
    @BindView(R2.id.img_market)
    ImageView imgMarket;
    BaseAdapter<CoinBean> adapter;
    private String code = "eth_usdt";
    CoinBean coinBean;
    private String rata;

    @Override
    public int getLayoutId() {
        return R.layout.contact_fragment_contract;
    }

    @Override
    public ContractPresenter getPresenter() {
        return new ContractPresenter();
    }

    @Override
    public void initView() {
        RxBus.getDefault().register(this);
        //设置菜单内容之外其他区域的背景色
        drawLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);//禁止滑动开启
        getChildFragmentManager().beginTransaction()
                .add(R.id.contact_left, ContractLeftFragment.newInstance(code))
                .add(R.id.contact_right, PankouFragment.newInstance(code))
                .add(R.id.contact_depth, (Fragment) ARouter.getInstance().build(RoutePath.DEPTH_FRAGMENT).withString("code", code).navigation())
                .add(R.id.contact_order, EntrustFragment.newInstance())
                .commit();
        initDrawLayout();
    }

    private void initDrawLayout() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.getItemAnimator().setChangeDuration(0);
        adapter = new BaseAdapter<CoinBean>(R.layout.contact_item_coin_list, null, recyclerView) {
            @Override
            public void bind(ViewHolder holder, CoinBean item) {
                if (item.getName().contains("_")) {
                    holder.setText(R.id.coin_name, item.getName().split("_")[0]);
                } else {
                    holder.setText(R.id.coin_name, item.getName());
                }
                holder.setText(R.id.coin_cny_price, "¥" + NumberUtils.keepDown(item.getCnyPrice(), 2));
                holder.setText(R.id.coin_price, NumberUtils.keepDown(item.getPrice(), DigitUtils.getDigit(item.getCode())))
                        .setText(R.id.coin_change_rate, item.getChange() > 0 ? "+" + item.getChangeRate() : item.getChangeRate());
                if (item.isUp()) {
                    holder.setTextColor(R.id.coin_price, color(R.color.common_red));
                } else {
                    holder.setTextColor(R.id.coin_price, color(R.color.common_green));
                }
                holder.setImageResource(R.id.coin_img, CoinIcon.getIcon(item.getCode()));
                ClickUtil.click(holder.itemView, view -> {
                    code = item.getCode();
                    coinBean = item;
                    if (item.getName().contains("_")) {
                        tvSelectCoin.setText(item.getName().replace("_", "/"));
                    } else {
                        tvSelectCoin.setText(item.getName() + "/USDT");
                    }
                    mPresenter.getCoinInfo(code);
                    ContactChangeCoin contactChangeCoin = new ContactChangeCoin(item.getName());
                    contactChangeCoin.setPrice(item.getPrice() + "");
                    contactChangeCoin.setCnyPrice(item.getCnyPrice());
                    contactChangeCoin.setCode(code);
                    RxBus.getDefault().post(contactChangeCoin);
                    //关闭抽屉
                    drawLayout.closeDrawer(Gravity.LEFT);
                });
            }
        };
    }

    public void setCoinInfo(CoinInfo data) {
        if (data != null) {
            rata = data.getTrans_ware();
            tvBurstRate.setText(getString(R.string.contact_contact_fragment_contract130) + (TextUtils.isEmpty(data.getTrans_ware()) ? "---" : data.getTrans_ware()));
            RxBus.getDefault().post(data);
        }
    }

    @Override
    public void initData() {
        ClickUtil.click(tvAllOrder, view -> {
            if (BaseApplication.isLogin()) {
                OrderRecordsActivity.start(getContext(), code);
            } else {
                ARouter.getInstance().build(RoutePath.LOGIN_LOGIN).navigation();
            }
        });

        ClickUtil.click(tvSelectCoin, view -> {
            //打开抽屉
            drawLayout.openDrawer(Gravity.LEFT);
//            showDialog();
        });
        ClickUtil.click(ivClose, view -> {
            //关闭抽屉
            drawLayout.closeDrawer(Gravity.LEFT);
        });

        ClickUtil.click(imgMarket, view -> {
            ARouter.getInstance().build(RoutePath.MARKET_DETAIL).withSerializable("coinBean", coinBean).navigation();
        });
        ClickUtil.click(tvBurstRate, view -> {
            new TipsNewDialog(getContext())
                    .setContent(String.format(getString(R.string.contact_burst_rate_des), rata))
                    .setConfirmText(getString(R.string.common_common_tip_dialog80))
                    .setConfirmTextColor(R.color.common_red)
                    .setCancelVisible(View.GONE)
                    .show();
        });
    }

    @Override
    public void loadData() {
        mPresenter.getCoinInfo(code);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCoin(CoinBean coinBean) {
        if (adapter != null) {
            for (int i = 0; i < adapter.getData().size(); i++) {
                if (adapter.getData().get(i).getCode().equals(coinBean.getCode())) {
                    coinBean.setPid(adapter.getData().get(i).getPid());
                    adapter.getData().set(i, coinBean);
                    adapter.notifyItemChanged(i);
                }
            }
        }
    }

    @Override
    public void lazyLoad() {
        mPresenter.getMarketList();
    }

    public static ContractFragment newInstance() {
        ContractFragment fragment = new ContractFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }


    public void setCoinList(List<CoinBean> data) {
        //选择币种
        if (data != null && data.size() > 0) {
            code = data.get(0).getCode();
            coinBean = data.get(0);
            tvSelectCoin.setText(coinBean.getName() + "/USDT");
            adapter.setNewData(data);
            RxBus.getDefault().post(data.get(0));
        }
    }


}
