package com.sskj.market;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sskj.common.base.BaseActivity;
import com.sskj.common.data.CoinBean;
import com.sskj.common.router.RoutePath;
import com.sskj.common.view.ToolBarLayout;
import com.sskj.market.adapter.MarketDetailAdapter;
import com.sskj.market.data.MarketDetail;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Hey
 * Create at  2019/07/31 09:11:09
 */
@Route(path = RoutePath.MARKET_DETAIL)
public class NewMarketDetailActivity extends BaseActivity<NewMarketDetailPresenter> {
    @BindView(R2.id.toolbar)
    ToolBarLayout toolbar;
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @Autowired
    CoinBean coinBean;
    MarketDetailAdapter detailAdapter;

    private List<MarketDetail> data = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.market_activity_new_marke_tdetail;
    }

    @Override
    public NewMarketDetailPresenter getPresenter() {
        return new NewMarketDetailPresenter();
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);
        coinBean = (CoinBean) getIntent().getSerializableExtra("coinBean");
        toolbar.setTitle(coinBean.getName());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        detailAdapter = new MarketDetailAdapter(null, getSupportFragmentManager());
        recyclerView.setAdapter(detailAdapter);
    }

    @Override
    public void initData() {
        MarketDetail topData = new MarketDetail(MarketDetail.TOP);
        topData.setTopData(coinBean);
        data.add(topData);
        data.add(new MarketDetail(MarketDetail.CHART));
        data.add(new MarketDetail(MarketDetail.BOTTOM));
        detailAdapter.setNewData(data);
    }

    public static void start(Context context, CoinBean coinBean) {
        Intent intent = new Intent(context, NewMarketDetailActivity.class);
        intent.putExtra("coinBean", coinBean);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detailAdapter = null;
    }
}
