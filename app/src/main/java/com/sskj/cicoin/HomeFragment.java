package com.sskj.cicoin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lwj.widget.viewpagerindicator.ViewPagerIndicator;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.sskj.cicoin.data.BannerBean;
import com.sskj.common.BaseApplication;
import com.sskj.common.base.BaseFragment;
import com.sskj.common.data.CoinBean;
import com.sskj.common.glide.GlideImageLoader;
import com.sskj.common.glide.ZoomOutPageTransformer;
import com.sskj.common.http.BaseHttpConfig;
import com.sskj.common.http.Page;
import com.sskj.common.language.LanguageSPUtil;
import com.sskj.common.language.LocalManageUtil;
import com.sskj.common.router.RoutePath;
import com.sskj.common.rxbus.RxBus;
import com.sskj.common.rxbus.Subscribe;
import com.sskj.common.rxbus.ThreadMode;
import com.sskj.common.utils.ClickUtil;
import com.sskj.market.MarketListFragment;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Hey
 * Create at  2019/06/21
 */
public class HomeFragment extends BaseFragment<HomePresenter> {
    @BindView(R.id.change_language)
    TextView changeLanguage;
    @BindView(R.id.change_skin)
    LinearLayout changeSkip;
    @BindView(R.id.ll_invite)
    LinearLayout llInvite;
    @BindView(R.id.ll_mining)
    LinearLayout llMining;
    @BindView(R.id.ll_trading_guide)
    LinearLayout llTradingGuide;
    @BindView(R.id.ll_notice)
    LinearLayout llNotice;
    @BindView(R.id.bannerView)
    Banner bannerView;
    @BindView(R.id.indicator_line)
    ViewPagerIndicator indicatorLine;
    List<String> bannerImages = new ArrayList<>();
    @BindView(R.id.home_content)
    NestedScrollView homeContent;
    @BindView(R.id.tvNotice)
    TextSwitcher tvNotice;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.home_coin_list)
    FrameLayout homeCoinList;
    @BindView(R.id.tv_mode)
    TextView tvMode;
    List<Fragment> fragmentList = new ArrayList<>();
    CoinFragmentPager coinFragmentPager;
    private MarketListFragment marketListFragment;
    private Disposable noticeDisposable;

    @Override
    public int getLayoutId() {
        return R.layout.app_fragment_home;
    }

    @Override
    public HomePresenter getPresenter() {
        return new HomePresenter();
    }

    @Override
    public void initView() {
        RxBus.getDefault().register(this);
        bannerView.setImageLoader(new GlideImageLoader());
        bannerView.setOffscreenPageLimit(1);
        bannerView.setPageTransformer(false, new ZoomOutPageTransformer());
        marketListFragment = MarketListFragment.newInstance();
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.replace(R.id.home_coin_list, marketListFragment);
        ft.commitAllowingStateLoss();
        fragmentList.add(CoinFragment.newInstance(1));
//        fragmentList.add(CoinFragment.newInstance(2));
        coinFragmentPager = new CoinFragmentPager(getFragmentManager(), fragmentList);
        viewPager.setAdapter(coinFragmentPager);
        indicatorLine.setViewPager(viewPager);
        initTextnotice();
    }

    private void initTextnotice() {
        tvNotice.removeAllViews();
        tvNotice.setFactory(() -> {
            TextView textView = new TextView(getActivity());
            textView.setMaxLines(2);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
            textView.setGravity(Gravity.CENTER_VERTICAL);
            textView.setPadding(20, 0, 0, 0);
            return textView;
        });
    }


    @Override
    public void initData() {
        switch (LanguageSPUtil.getInstance(getContext()).getSelectLanguage()) {
            case 1:
//                changeLanguage.setText(getString(R.string.app_homeFragment1));
                break;
            case 2:
//                changeLanguage.setText(getString(R.string.app_homeFragment2));
                break;
            case 3:
                changeLanguage.setText("English");
                break;
            case 4:
                changeLanguage.setText("한글");
                break;
            default:
                changeLanguage.setText("English");
                break;
        }
        wrapRefresh(homeContent);
        setEnableLoadMore(false);
        ClickUtil.click(llInvite, view -> {
            if (BaseApplication.isLogin()) {
                ARouter.getInstance().build(RoutePath.INVITE_HOME).navigation();
            } else {
                ARouter.getInstance().build(RoutePath.LOGIN_LOGIN)
                        .navigation();
            }
        });
        ClickUtil.click(llTradingGuide, view -> {
            TradingGuideActivity.start(getContext());
        });
        ClickUtil.click(llMining, view -> {
            ARouter.getInstance().build(RoutePath.MAIN).withInt("isOpenMore", 2).navigation();
        });
    }

    @Override
    public void loadData() {
        mPresenter.getMarketList();
        mPresenter.getBanner(LocalManageUtil.getLanguage(getContext()));
        mPresenter.getNotice();
    }

    public void setData(List<CoinBean> data) {
        if (marketListFragment != null) {
            marketListFragment.setData(data);
        }
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        loadData();
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        bannerView.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        bannerView.stopAutoPlay();
    }

    public void setBanner(List<BannerBean> data) {
        bannerImages.clear();
        for (BannerBean bean : data) {
            bannerImages.add(BaseHttpConfig.BASE_URL + bean.getPath());
        }
        bannerView.setImages(bannerImages);
        bannerView.start();
    }

    public void setNotice(Page<NewsBean> data) {
        if (data != null) {
            noticeDisposable = Flowable.interval(0, 5, TimeUnit.SECONDS)
                    .onBackpressureDrop()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(i -> {
                        if (tvNotice != null && data.getRes() != null && !data.getRes().isEmpty()) {
                            int position = (int) (i % data.getRes().size());
                            String text = data.getRes().get(position).getTitle();
                            tvNotice.setText(text);
                            ClickUtil.click(llNotice, view -> {
                                NoticeListActivity.start(getContext());
                            });
                        }
                    }, throwable -> {
                        throwable.printStackTrace();
                    });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.getDefault().unregister(this);
        if (noticeDisposable != null) {
            noticeDisposable.dispose();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateUI(String data) {
        if (data != null) {
            initTextnotice();
        }
    }

}
