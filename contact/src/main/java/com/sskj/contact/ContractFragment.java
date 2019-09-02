package com.sskj.contact;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.sskj.common.adapter.BaseAdapter;
import com.sskj.common.adapter.ViewHolder;
import com.sskj.common.base.BaseFragment;
import com.sskj.common.data.CoinBean;
import com.sskj.common.dialog.SelectCoinDialog;
import com.sskj.common.router.RoutePath;
import com.sskj.common.utils.ClickUtil;
import com.sskj.common.utils.CoinIcon;
import com.sskj.common.utils.DigitUtils;
import com.sskj.common.utils.NumberUtils;

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
    private String code = "BTC/USDT";

    private SelectCoinDialog selectCoinDialog;

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
                if (item.getName().contains("/")) {
                    holder.setText(R.id.coin_name, item.getName().split("/")[0]);
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
//                    contractFragment1.setCode(code);
//                    RxBus.getDefault().post(new ChangePriceData(item.getPrice() + "", item.getCnyPrice(), code));
                    //关闭抽屉
                    drawLayout.closeDrawer(Gravity.LEFT);
                });
            }
        };
    }


    @Override
    public void initData() {
        ClickUtil.click(tvAllOrder, view -> {
            OrderRecordsActivity.start(getContext(), code);
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
            ARouter.getInstance().build(RoutePath.MARKET_DETAIL).withString("code", code).navigation();
        });
    }

    @Override
    public void loadData() {

    }

    @Override
    public void lazyLoad() {
        mPresenter.getMarketList(false);
    }

    public static ContractFragment newInstance() {
        ContractFragment fragment = new ContractFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }


    public void setCoinList(List<CoinBean> data, boolean showDialog) {
        //选择币种
        if (data != null && data.size() > 0) {
//            coinAssets.clear();
            code = data.get(0).getCode();
            tvSelectCoin.setText(code);
            adapter.setNewData(data);
//            for (CoinBean bean : data) {
//                CoinAsset coinAsset = new CoinAsset();
//                coinAsset.setName(bean.getCode());
//                coinAsset.setPname(bean.getCode());
//                coinAsset.setPid(bean.getPid());
//                coinAsset.setPrice(bean.getPrice() + "");
//                coinAsset.setCnyPrice(bean.getCnyPrice());
//                coinAssets.add(coinAsset);
//            }
        }
    }


}
