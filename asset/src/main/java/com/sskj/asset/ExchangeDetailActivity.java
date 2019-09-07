package com.sskj.asset;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sskj.asset.data.ExchangeListEntity;
import com.sskj.common.adapter.BaseAdapter;
import com.sskj.common.adapter.ViewHolder;
import com.sskj.common.base.BaseActivity;
import com.sskj.common.helper.DataSource;
import com.sskj.common.helper.SmartRefreshHelper;
import com.sskj.common.utils.TimeFormatUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Flowable;

/**
 * @author Hey
 * Create at  2019/09/05 18:45:54
 */
public class ExchangeDetailActivity extends BaseActivity<ExchangeDetailPresenter> {
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    SmartRefreshHelper<ExchangeListEntity.Exchange> smartRefreshHelper;
    BaseAdapter<ExchangeListEntity.Exchange> newsAdapter;
    int size = 10;

    @Override
    public int getLayoutId() {
        return R.layout.asset_activity_exchange_detail;
    }

    @Override
    public ExchangeDetailPresenter getPresenter() {
        return new ExchangeDetailPresenter();
    }

    @Override
    public void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsAdapter = new BaseAdapter<ExchangeListEntity.Exchange>(R.layout.asset_item_exchange_detail, null, recyclerView) {
            @Override
            public void bind(ViewHolder holder, ExchangeListEntity.Exchange item) {
                holder.setText(R.id.tv_exchange_asset, item.memo).
                        setText(R.id.tv_exchange_count, item.num)
                        .setText(R.id.tv_daozhang_count, item.exnum)
                        .setText(R.id.tv_exchange_time, TimeFormatUtil.SF_FORMAT_E.format(Long.valueOf(item.addtime) * 1000));
            }

        };
    }

    @Override
    public void initData() {
        smartRefreshHelper = new SmartRefreshHelper<>(this, newsAdapter);
        smartRefreshHelper.setDataSource(new DataSource<ExchangeListEntity.Exchange>() {
            @Override
            public Flowable<List<ExchangeListEntity.Exchange>> loadData(int page) {
                return mPresenter.getExchangeDetail(page + "", size + "");
            }
        });
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, ExchangeDetailActivity.class);
        context.startActivity(intent);
    }

}
