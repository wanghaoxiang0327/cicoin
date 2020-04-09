package com.sskj.asset;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sskj.asset.data.OtherRecordEntity;
import com.sskj.common.adapter.BaseAdapter;
import com.sskj.common.adapter.ViewHolder;
import com.sskj.common.base.BaseFragment;
import com.sskj.common.helper.DataSource;
import com.sskj.common.helper.SmartRefreshHelper;
import com.sskj.common.utils.NumberUtils;
import com.sskj.common.utils.TimeFormatUtil;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Flowable;

/**
 * @author Hey
 * Create at  2019/09/05 21:26:00
 */
public class OtherFragment extends BaseFragment<OtherPresenter> {
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    BaseAdapter<OtherRecordEntity> newsAdapter;
    SmartRefreshHelper<OtherRecordEntity> rechargesmartRefreshHelper;
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
        String name = getArguments().getString("code");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        newsAdapter = new BaseAdapter<OtherRecordEntity>(R.layout.asset_item_other_record, null, recyclerView) {
            @Override
            public void bind(ViewHolder holder, OtherRecordEntity item) {
                holder.setText(R.id.tv_recharge_submit, pid.equals("0") ? item.type : item.memo)
                        .setText(R.id.tv_recharge_count,
                                NumberUtils.keepMaxDown(item.price, 4) + name)
                        .setText(R.id.tv_recharge_address, TimeFormatUtil.SF_FORMAT_E.format(Long.valueOf(item.addtime) * 1000));
            }
        };
    }


    @Override
    public void initData() {

    }

    @Override
    public void loadData() {

    }

    @Override
    public void lazyLoad() {
        super.lazyLoad();
        rechargesmartRefreshHelper = new SmartRefreshHelper<>(getContext(), newsAdapter);
        rechargesmartRefreshHelper.setDataSource(new DataSource<OtherRecordEntity>() {
            @Override
            public Flowable<List<OtherRecordEntity>> loadData(int page) {
                return mPresenter.getOtherDetailList(page, size, pid);
            }
        });
    }

    public static OtherFragment newInstance(String pid, String code) {
        OtherFragment fragment = new OtherFragment();
        Bundle bundle = new Bundle();
        bundle.putString("pid", pid);
        bundle.putString("code", code);
        fragment.setArguments(bundle);
        return fragment;
    }

}
