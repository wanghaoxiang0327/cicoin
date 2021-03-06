package com.sskj.cicoin;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.sskj.common.App;
import com.sskj.common.BaseApplication;
import com.sskj.common.CommonConfig;
import com.sskj.common.base.BaseActivity;
import com.sskj.common.data.CoinBean;
import com.sskj.common.data.VersionBean;
import com.sskj.common.dialog.TipDialog;
import com.sskj.common.dialog.VersionUpdateDialog;
import com.sskj.common.http.BaseHttpConfig;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.language.LocalManageUtil;
import com.sskj.common.router.RoutePath;
import com.sskj.common.rxbus.BusCode;
import com.sskj.common.rxbus.RxBus;
import com.sskj.common.rxbus.Subscribe;
import com.sskj.common.rxbus.ThreadMode;
import com.sskj.common.socket.WebSocket;
import com.sskj.common.tab.TabItem;
import com.sskj.common.tab.TabLayout;
import com.sskj.common.tab.TabSelectListener;
import com.sskj.mine.MineFragment;
import com.sskj.miner.ui.fragment.MinerFragment;
import com.sskj.contact.ContractFragment;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;


/**
 * @author Hey
 */
@Route(path = RoutePath.MAIN)
public class MainActivity extends BaseActivity<MainPresenter> {
    @BindView(R.id.main_content)
    FrameLayout mainContent;
    @BindView(R.id.main_tab_layout)
    TabLayout mainTabLayout;
    ArrayList<TabItem> mainTabs = new ArrayList<>();
    ArrayList<Fragment> fragments = new ArrayList<>();
    private WebSocket marketWebSocket;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxBus.getDefault().register(this);
        checkPermission();
    }


    @Override
    public void initView() {
        mainTabs.add(new TabItem(getString(R.string.app_mainActivity1), R.mipmap.tab_home, R.mipmap.tab_home_p));
        mainTabs.add(new TabItem(getString(R.string.app_mainActivity2), R.mipmap.tab_heyue, R.mipmap.tab_heyue_p));
        mainTabs.add(new TabItem(getString(R.string.app_mainActivity3), R.mipmap.tab_wakuang, R.mipmap.tab_wakuang_p));
        mainTabs.add(new TabItem(getString(R.string.app_mainActivity4), R.mipmap.tab_mine, R.mipmap.tab_mine_p));
        fragments.add(HomeFragment.newInstance());
        fragments.add(ContractFragment.newInstance());
        fragments.add(MinerFragment.newInstance());
        fragments.add(MineFragment.newInstance());
        mainTabLayout.setTabData(mainTabs, getSupportFragmentManager(), R.id.main_content, fragments);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int isOpenMore = intent.getIntExtra("isOpenMore", 0);
        if (isOpenMore == 1) {
            mainTabLayout.setCurrentTab(1);
            ImmersionBar.with(MainActivity.this).fitsSystemWindows(true).statusBarColor(R.color.white).statusBarDarkFont(true, 0.2f).init();
        } else if (isOpenMore == 2) {
            mainTabLayout.setCurrentTab(2);
            initImmersionBar();
        }
    }

    @Override
    public void initData() {
        ImmersionBar.with(MainActivity.this).fitsSystemWindows(true).statusBarColor(R.color.white).statusBarDarkFont(true, 0.2f).init();
        JSONObject message = new JSONObject();
        message.put("code", "all");
        marketWebSocket = new WebSocket(HttpConfig.WS_URL, "market", message.toString());
        marketWebSocket.setListener(message1 -> {
            try {
                CoinBean coinBean = JSON.parseObject(message1, CoinBean.class);
                if (coinBean != null) {
                    RxBus.getDefault().post(coinBean);
                }
            } catch (Exception e) {

            }
        });
    }

    @Override
    public void initEvent() {
        mainTabLayout.setOnTabSelectListener(new TabSelectListener() {
            @Override
            public boolean onTabSelect(int position) {
//
                if (position == 0 || position == 1) {
                    ImmersionBar.with(MainActivity.this).fitsSystemWindows(true).statusBarColor(R.color.white).statusBarDarkFont(true, 0.2f).init();
                } else {
                    initImmersionBar();
                }
                return true;
            }

            @Override
            public boolean onTabReselect(int position) {
                return true;
            }
        });
    }


    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter();
    }

    @Override
    public void loadData() {
        mPresenter.checkVersion(BuildConfig.VERSION_NAME);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        marketWebSocket.closeWebSocket();
    }


    @SuppressLint("CheckResult")
    public void checkPermission() {
        new RxPermissions(this).request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(aBoolean -> {
                    if (!aBoolean) {
                        new TipDialog(this)
                                .setContent(getString(R.string.app_pleas_agree_permission))
                                .setConfirmListener(dialog -> {
                                    checkPermission();
                                })
                                .setCancelListener(dialog -> {
                                    finish();
                                }).show();
                    }
                });
    }


    public void checkVersion(HttpResult<VersionBean> result) {
        if (result != null) {
            new VersionUpdateDialog(this, result.getData()).show();
        }
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

    @Subscribe(code = BusCode.CHANGE_LANGUAGE, threadMode = ThreadMode.MAIN)
    public void changeLanguage() {
        HttpHeaders httpHeaders = OkGo.getInstance().getCommonHeaders();
        httpHeaders.put("lang", LocalManageUtil.getSetLanguageLocale(App.INSTANCE) == Locale.ENGLISH ? "eu" : "cn");
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }
}
