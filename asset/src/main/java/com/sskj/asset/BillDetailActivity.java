package com.sskj.asset;

import com.sskj.common.base.BaseActivity;
import com.sskj.asset.BillDetailPresenter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.sskj.asset.R;
import com.sskj.common.tab.TabItem;
import com.sskj.common.tab.TabLayout;
import com.sskj.common.utils.ClickUtil;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author Hey
 * Create at  2019/09/05 20:56:12
 */
public class BillDetailActivity extends BaseActivity<BillDetailPresenter> {
    @BindView(R2.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R2.id.content)
    FrameLayout content;
    @BindView(R2.id.left_img)
    ImageView leftImg;
    ArrayList<TabItem> tabs = new ArrayList<>();
    ArrayList<Fragment> fragments = new ArrayList<>();
    String pid;

    @Override
    public int getLayoutId() {
        return R.layout.asset_activity_bill_detail;
    }

    @Override
    public BillDetailPresenter getPresenter() {
        return new BillDetailPresenter();
    }

    @Override
    public void initView() {
        pid = getIntent().getStringExtra("pid");
        tabs.add(new TabItem(getString(R.string.asset_asset_activity_recharge20)));
        tabs.add(new TabItem(getString(R.string.asset_asset_activity_insert_address200)));
        tabs.add(new TabItem(getString(R.string.asset_exchange_other)));
        fragments.add(CoinFragment.newInstance(0, pid));
        fragments.add(CoinFragment.newInstance(1, pid));
        fragments.add(OtherFragment.newInstance(pid));
        tabLayout.setTextSelectColor(getResources().getColor(R.color.common_tip));
        tabLayout.setTabData(tabs, getSupportFragmentManager(), R.id.content, fragments);
        ClickUtil.click(leftImg, view -> {
            finish();
        });
    }

    @Override
    public void initData() {

    }

    public static void start(Context context, String pid) {
        Intent intent = new Intent(context, BillDetailActivity.class);
        intent.putExtra("pid", pid);
        context.startActivity(intent);
    }

}
