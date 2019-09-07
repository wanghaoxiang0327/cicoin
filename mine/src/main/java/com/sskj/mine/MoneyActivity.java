package com.sskj.mine;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sskj.common.DividerLineItemDecoration;
import com.sskj.common.adapter.BaseAdapter;
import com.sskj.common.adapter.ViewHolder;
import com.sskj.common.base.BaseActivity;
import com.sskj.common.helper.DataSource;
import com.sskj.common.helper.SmartRefreshHelper;
import com.sskj.common.utils.TimeFormatUtil;
import com.sskj.mine.data.CommissionBean;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Flowable;

/**
 * @author Hey
 * Create at  2019/09/05 09:37:33
 */
public class MoneyActivity extends BaseActivity<MoneyPresenter> {
    @BindView(R2.id.list)
    RecyclerView detailList;
    private int size = 15;
    BaseAdapter<CommissionBean> commissionDetailAdapter;
    SmartRefreshHelper<CommissionBean> smartRefreshHelper;

    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_money;
    }

    @Override
    public MoneyPresenter getPresenter() {
        return new MoneyPresenter();
    }

    @Override
    public void initView() {
        detailList.addItemDecoration(new DividerLineItemDecoration(this)
                .setDividerColor(color(R.color.common_divider))
                .setLastDraw(false)
                .setFirstDraw(false));
        detailList.setLayoutManager(new LinearLayoutManager(this));
        commissionDetailAdapter = new BaseAdapter<CommissionBean>(R.layout.mine_item_comission_detail, null, detailList) {
            @Override
            public void bind(ViewHolder holder, CommissionBean item) {
                holder.setText(R.id.user_id, item.realname)
                        .setText(R.id.count, item.nums)
                        .setText(R.id.time, TimeFormatUtil.SF_FORMAT_E.format(item.time * 1000));
            }
        };
    }

    @Override
    public void initData() {
        smartRefreshHelper = new SmartRefreshHelper<>(this, commissionDetailAdapter);
    }

    @Override
    public void loadData() {
        smartRefreshHelper.setDataSource(new DataSource<CommissionBean>() {
            @Override
            public Flowable<List<CommissionBean>> loadData(int page) {
                return mPresenter.getCommsion(page, size);
            }
        });
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, MoneyActivity.class);
        context.startActivity(intent);
    }
}
