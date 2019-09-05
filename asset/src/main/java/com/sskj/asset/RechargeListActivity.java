package com.sskj.asset;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.allen.library.SuperTextView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.sskj.asset.data.RechargeListBean;
import com.sskj.common.adapter.BaseAdapter;
import com.sskj.common.adapter.ViewHolder;
import com.sskj.common.base.BaseActivity;
import com.sskj.common.data.CoinAsset;
import com.sskj.common.dialog.SelectCoinDialog;
import com.sskj.common.utils.ClickUtil;
import com.sskj.common.utils.ItemDivider;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Flowable;

/**
 * @author Hey
 * Create at  2019/09/06 01:01:36
 */
public class RechargeListActivity extends BaseActivity<RechargeListPresenter> {


    @BindView(R2.id.select_coin)
    SuperTextView selectCoin;
    @BindView(R2.id.list)
    RecyclerView list;
    private int P = 1;
    private int size = 30;
    private String codeOrigin;
    private SelectCoinDialog selectCoinDialog;
    private List<CoinAsset> coinList;
    private String pid;
    private BaseAdapter<RechargeListBean.ResBean> adapter;

    @Override
    public int getLayoutId() {
        return R.layout.asset_activity_recharge_list;
    }

    @Override
    public RechargeListPresenter getPresenter() {
        return new RechargeListPresenter();
    }

    @Override
    public void initView() {
        list.setLayoutManager(new LinearLayoutManager(this));
        list.addItemDecoration(new ItemDivider().setDividerColor(getResources().getColor(R.color.common_divider)).setDividerWith(2));
        adapter = new BaseAdapter<RechargeListBean.ResBean>(R.layout.asset_recharge_item, null, list) {
            @Override
            public void bind(ViewHolder holder, RechargeListBean.ResBean item) {
                holder.setText(R.id.t1, item.getChongzhi_url())
                        .setText(R.id.t2, item.getPrice())
                        .setText(R.id.t3, item.getAddtime())
                        .setText(R.id.t4, item.getCheck_time());

            }
        };

    }

    @Override
    public void initData() {
        wrapRefresh(list);
        ClickUtil.click(selectCoin.getRightTextView(),view -> mPresenter.getCoinAsset(true));

    }

    @Override
    public void loadData() {
        super.loadData();
//        mPresenter.getList();
        mPresenter.getCoinAsset(false);
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        P = 1;
        mPresenter.getList(pid, P, size);
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        P++;
        mPresenter.getList(pid, P, size);
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, RechargeListActivity.class);
        context.startActivity(intent);
    }

    public void setList(List<RechargeListBean.ResBean> bean) {
        if (P == 1) {
            adapter.setNewData(bean);
        } else {
            adapter.addData(bean);
        }
        if (bean.size() == 0) {
            mRefreshLayout.setNoMoreData(true);
        }
    }

    public void showCoinDialog(List<CoinAsset> data) {
        if (selectCoinDialog == null) {
            selectCoinDialog = new SelectCoinDialog(this, (dialog, coin, position) -> {
                changeCoin(coin);
                dialog.dismiss();
            });
        }
        selectCoinDialog.setData(data);
        selectCoinDialog.show();
    }

    public void setCoinList(List<CoinAsset> data) {
        if (data != null && !data.isEmpty()) {
            coinList = data;
            codeOrigin = data.get(0).getPname();
            Flowable.fromIterable(data)
                    .filter(coinAsset -> coinAsset.getPname().equals(codeOrigin))
                    .subscribe(coinAsset -> {
                        changeCoin(coinAsset);
                    });
        }

    }


    public void changeCoin(CoinAsset coin) {
        pid = coin.getPid();
        selectCoin.setRightString(coin.getPname());
        mPresenter.getList(pid,P,size);
    }

}
