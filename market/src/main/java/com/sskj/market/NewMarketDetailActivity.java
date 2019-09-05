package com.sskj.market;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.gyf.barlibrary.ImmersionBar;
import com.sskj.common.base.BaseActivity;
import com.sskj.common.data.CoinBean;
import com.sskj.common.router.RoutePath;
import com.sskj.common.utils.ClickUtil;
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
    @BindView(R2.id.tv_make_more)
    TextView tvMakeMore;
    @BindView(R2.id.tv_make_empty)
    TextView tvMakeEmpty;
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void initView() {
        toolbar.mLeftButton.setCompoundDrawableTintList(ColorStateList.valueOf(getResources().getColor(R.color.common_white)));
        ARouter.getInstance().inject(this);
        coinBean = (CoinBean) getIntent().getSerializableExtra("coinBean");
        toolbar.setTitle(coinBean.getName());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        detailAdapter = new MarketDetailAdapter(null, getSupportFragmentManager());
        recyclerView.setAdapter(detailAdapter);
        ClickUtil.click(tvMakeMore, view -> {
            ARouter.getInstance().build(RoutePath.MAIN).withInt("isOpenMore", 1).navigation();
            finish();
        });
        ClickUtil.click(tvMakeEmpty, view -> {
            ARouter.getInstance().build(RoutePath.MAIN).withInt("isOpenMore", 1).navigation();
            finish();
        });
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

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this)
                .transparentStatusBar()
                .fitsSystemWindows(false)
                //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .statusBarDarkFont(false, 0.2f)
                .init();
    }
}
