package com.sskj.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.sskj.common.WebActivity;
import com.sskj.common.adapter.BaseAdapter;
import com.sskj.common.adapter.ViewHolder;
import com.sskj.common.base.BaseActivity;
import com.sskj.common.dialog.SelectContractDialog;
import com.sskj.common.utils.ClickUtil;
import com.sskj.common.utils.NumberUtils;
import com.sskj.mine.data.DividendBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 董事分红
 *
 * @author Hey
 * Create at  2019/06/25
 */
public class DirectorActivity extends BaseActivity<DirectorPresenter> {


    @BindView(R2.id.dividend_list)
    RecyclerView dividendList;
    @BindView(R2.id.topList)
    RecyclerView topList;
    @BindView(R2.id.content_layout)
    NestedScrollView contentLayout;

    BaseAdapter<DividendBean.Res.Dividen> adapter;

    BaseAdapter<DividendBean.Res.TotalBean> topAdapter;

    int page = 1;
    int size = 10;
    @BindView(R2.id.tv_contract)
    TextView tvContract;
    private String type = "1";
    private SelectContractDialog selectContractDialog;
    private List<String> conteactlist = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_director;
    }

    @Override
    public DirectorPresenter getPresenter() {
        return new DirectorPresenter();
    }

    @Override
    public void initView() {

        mToolBarLayout.setRightButtonOnClickListener(v -> {
            WebActivity.start(this, 3);
        });
        conteactlist.add(getString(R.string.mine_contract1));
        conteactlist.add(getString(R.string.mine_contract2));
        conteactlist.add(getString(R.string.mine_contract3));
        dividendList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BaseAdapter<DividendBean.Res.Dividen>(R.layout.mine_item_dividend, null, dividendList) {
            @Override
            public void bind(ViewHolder holder, DividendBean.Res.Dividen item) {
                holder.setText(R.id.btc_count, NumberUtils.keepDown(item.getInc_BTC(), 6))
                        .setText(R.id.eos_count, NumberUtils.keepDown(item.getInc_EOS(), 2))
                        .setText(R.id.eth_count, NumberUtils.keepDown(item.getInc_ETH(), 5))
                        .setText(R.id.usdt_count, NumberUtils.keepDown(item.getInc_USDT(), 2))
                        .setText(R.id.week, item.getWeek());
            }
        };
        topList.setLayoutManager(new GridLayoutManager(this, 2));
        topAdapter = new BaseAdapter<DividendBean.Res.TotalBean>(R.layout.mine_item_director_total, null, topList) {
            @Override
            public void bind(ViewHolder holder, DividendBean.Res.TotalBean item) {

                holder.setText(R.id.name, item.getCode() + "：")
                        .setText(R.id.count, item.getNum());

            }
        };
        ClickUtil.click(tvContract,view -> {
            showSelectDialog();
        });

    }

    private void showSelectDialog() {
        if (selectContractDialog == null) {
            selectContractDialog = new SelectContractDialog(this,((dialog, coin) -> {
                for (int i = 0;i<conteactlist.size();i++){
                    if (coin.equals(conteactlist.get(i))){
                        type = (i+1)+"";
                        mPresenter.getCommsion(type,page,size);
                        tvContract.setText(coin);
                        selectContractDialog.dismiss();
                        break;
                    }
                }
            }));
        }
        selectContractDialog.setData(conteactlist);
        selectContractDialog.show();
    }

    @Override
    public void initData() {
        wrapRefresh(contentLayout);
    }

    @Override
    public void loadData() {
        mPresenter.getCommsion(type, page, size);
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        page++;
        mPresenter.getCommsion(type, page, size);
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        page = 1;
        mPresenter.getCommsion(type, page, size);
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, DirectorActivity.class);
        context.startActivity(intent);
    }


    public void setData(DividendBean data) {
        if (data != null) {
            if (page == 1) {
                adapter.setNewData(data.getData().getList());
            } else {
                adapter.addData(data.getData().getList());
            }
            if (data.getData().getList() == null || data.getData().getList().isEmpty()) {
                mRefreshLayout.setNoMoreData(true);
            }
            topAdapter.setNewData(data.getData().getTotal());
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
