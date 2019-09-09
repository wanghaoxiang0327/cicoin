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
 * Create at  2019/09/05 09:37:16
 */
public class TeamActivity extends BaseActivity<TeamPresenter> {
    @BindView(R2.id.list)
    RecyclerView teamList;
    BaseAdapter<CommissionBean> commissionDetailAdapter;
    SmartRefreshHelper<CommissionBean> smartRefreshHelper;
    private int size = 10;

    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_team;
    }

    @Override
    public TeamPresenter getPresenter() {
        return new TeamPresenter();
    }

    @Override
    public void initView() {
        teamList.addItemDecoration(new DividerLineItemDecoration(this)
                .setDividerColor(color(R.color.common_divider))
                .setLastDraw(false)
                .setFirstDraw(false));
        teamList.setLayoutManager(new LinearLayoutManager(this));
        commissionDetailAdapter = new BaseAdapter<CommissionBean>(R.layout.mine_item_team, null, teamList) {
            @Override
            public void bind(ViewHolder holder, CommissionBean item) {
                holder.setText(R.id.name_tv, item.realname)
                        .setText(R.id.mobile_tv, item.mobile)
                        .setText(R.id.time_tv, TimeFormatUtil.SF_FORMAT_E.format(item.time * 1000));
            }
        };

    }

    public static void start(Context context) {
        Intent intent = new Intent(context, TeamActivity.class);
        context.startActivity(intent);
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

}
