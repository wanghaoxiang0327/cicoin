package com.sskj.asset;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sskj.common.adapter.BaseAdapter;
import com.sskj.common.adapter.ViewHolder;
import com.sskj.common.base.BaseFragment;
import com.sskj.common.helper.DataSource;
import com.sskj.common.helper.SmartRefreshHelper;
import com.sskj.common.utils.TimeFormatUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Flowable;

/**
 * @author Hey
 * Create at  2019/09/05 21:26:00
 */
public class OtherFragment extends BaseFragment<OtherPresenter> {
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    BaseAdapter<RechargeEntity> newsAdapter;
    SmartRefreshHelper<RechargeEntity> rechargesmartRefreshHelper;
    int size = 10;
    String pid;

    @Override
    public int getLayoutId() {
        return R.layout.asset_fragment_other;
    }

    @Override
    public OtherPresenter getPresenter() {
        return new OtherPresenter();
    }

    @Override
    public void initView() {
        pid = getArguments().getString("pid");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        newsAdapter = new BaseAdapter<RechargeEntity>(R.layout.asset_item_recharge_record, null, recyclerView) {
            @Override
            public void bind(ViewHolder holder, RechargeEntity item) {
                holder.setText(R.id.tv_recharge_address, item.chongzhi_url).setText(R.id.tv_recharge_count, item.account).setText(R.id.tv_recharge_submit, TimeFormatUtil.SF_FORMAT_E.format(Long.valueOf(item.addtime) * 1000)).setText(R.id.tv_recharge_review, TimeFormatUtil.SF_FORMAT_E.format(Long.valueOf(item.check_time) * 1000));
            }
        };
    }


    @Override
    public void initData() {

    }

    @Override
    public void loadData() {
        rechargesmartRefreshHelper = new SmartRefreshHelper<>(getContext(), newsAdapter);
        rechargesmartRefreshHelper.setDataSource(new DataSource<RechargeEntity>() {
            @Override
            public Flowable<List<RechargeEntity>> loadData(int page) {
                return mPresenter.getOtherDetailList(page, size, pid);
            }
        });
    }

    public static OtherFragment newInstance(String pid) {
        OtherFragment fragment = new OtherFragment();
        Bundle bundle = new Bundle();
        bundle.putString("pid", pid);
        fragment.setArguments(bundle);
        return fragment;
    }

}
