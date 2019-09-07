package com.sskj.asset;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sskj.asset.data.WithDrawEntity;
import com.sskj.common.adapter.BaseAdapter;
import com.sskj.common.adapter.ViewHolder;
import com.sskj.common.base.BaseFragment;
import com.sskj.common.helper.DataSource;
import com.sskj.common.helper.SmartRefreshHelper;
import com.sskj.common.utils.TimeFormatUtil;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Flowable;

/**
 * @author Hey
 * Create at  2019/09/05 21:00:01
 */
public class CoinFragment extends BaseFragment<CoinPresenter> {
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    int size = 10;
    int type = 0;
    String pid;

    @Override
    public int getLayoutId() {
        return R.layout.asset_fragment_coin;
    }

    @Override
    public CoinPresenter getPresenter() {
        return new CoinPresenter();
    }

    BaseAdapter<RechargeEntity> newsAdapter;
    BaseAdapter<WithDrawEntity> adapter;
    SmartRefreshHelper<RechargeEntity> rechargesmartRefreshHelper;
    SmartRefreshHelper<WithDrawEntity> withDrawsmartRefreshHelper;

    @Override
    public void initView() {
        type = getArguments().getInt("type");
        pid = getArguments().getString("pid");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        newsAdapter = new BaseAdapter<RechargeEntity>(R.layout.asset_item_recharge_record, null, recyclerView) {
            @Override
            public void bind(ViewHolder holder, RechargeEntity item) {
                holder.setText(R.id.tv_recharge_address, item.chongzhi_url)
                        .setText(R.id.tv_recharge_count, item.account)
                        .setText(R.id.tv_recharge_submit, TimeFormatUtil.SF_FORMAT_E.format(Long.valueOf(item.addtime) * 1000))
                        .setText(R.id.tv_recharge_review, TimeFormatUtil.SF_FORMAT_E.format(Long.valueOf(item.check_time) * 1000));
                if (type == 1) {
                    holder.setVisible(R.id.tv_status, true);
                } else {
                    holder.setVisible(R.id.tv_status, false);
                }
            }
        };
        adapter = new BaseAdapter<WithDrawEntity>(R.layout.asset_item_recharge_record, null, recyclerView) {
            @Override
            public void bind(ViewHolder holder, WithDrawEntity item) {
                holder.setText(R.id.tv_recharge_address, item.qianbao_url).setText(R.id.tv_recharge_count, item.account).setText(R.id.tv_recharge_submit, TimeFormatUtil.SF_FORMAT_E.format(Long.valueOf(item.addtime) * 1000)).setText(R.id.tv_recharge_review, TimeFormatUtil.SF_FORMAT_E.format(Long.valueOf(item.check_time) * 1000));
            }
        };
    }


    @Override
    public void initData() {
        if (type == 0) {
            rechargesmartRefreshHelper = new SmartRefreshHelper<>(getContext(), newsAdapter);
            rechargesmartRefreshHelper.setDataSource(new DataSource<RechargeEntity>() {
                @Override
                public Flowable<List<RechargeEntity>> loadData(int page) {
                    return mPresenter.getRechareDetailList(page, size, pid);
                }
            });
        } else if (type == 1) {
            withDrawsmartRefreshHelper = new SmartRefreshHelper<>(getContext(), adapter);
            withDrawsmartRefreshHelper.setDataSource(new DataSource<WithDrawEntity>() {
                @Override
                public Flowable<List<WithDrawEntity>> loadData(int page) {
                    return mPresenter.getWithDrawDetailList(page, size, pid);
                }
            });
        }
    }

    @Override
    public void loadData() {

    }

    public static CoinFragment newInstance(int type, String pid) {
        CoinFragment fragment = new CoinFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putString("pid", pid);
        fragment.setArguments(bundle);
        return fragment;
    }

}
