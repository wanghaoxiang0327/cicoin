package com.sskj.mine;

import android.app.Dialog;
import android.os.Bundle;
import android.support.constraint.Barrier;
import android.support.constraint.Group;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.sskj.common.App;
import com.sskj.common.BaseApplication;
import com.sskj.common.CommonConfig;
import com.sskj.common.WebViewActivity;
import com.sskj.common.adapter.BaseAdapter;
import com.sskj.common.adapter.ViewHolder;
import com.sskj.common.base.BaseFragment;
import com.sskj.common.dialog.TipsNewDialog;
import com.sskj.common.http.BaseHttpConfig;
import com.sskj.common.router.RoutePath;
import com.sskj.common.rxbus.BusCode;
import com.sskj.common.rxbus.RxBus;
import com.sskj.common.user.data.UserBean;
import com.sskj.common.utils.ClickUtil;
import com.sskj.common.utils.ItemDivider;
import com.sskj.common.utils.NumberUtils;
import com.sskj.common.utils.PatternUtils;
import com.sskj.common.utils.SpUtil;
import com.sskj.mine.data.CentenItemBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Create at  2019/06/24
 */
public class MineFragment extends BaseFragment<MinePresenter> {
    @BindView(R2.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R2.id.view_top)
    View viewTop;
    @BindView(R2.id.view_barrier)
    Barrier viewBarrier;
    @BindView(R2.id.group_unLogin)
    Group groupUnLogin;
    @BindView(R2.id.tv_login)
    TextView tvLogin;
    @BindView(R2.id.tv_tips)
    TextView tvTips;
    @BindView(R2.id.group_login)
    Group groupLogin;
    @BindView(R2.id.view_info_center)
    View viewInfoCenter;
    @BindView(R2.id.tv_name)
    TextView tvName;
    @BindView(R2.id.tv_qd)
    TextView tvQd;
    @BindView(R2.id.img_qd)
    ImageView imgQd;
    @BindView(R2.id.tv_uid)
    TextView tvUid;
    @BindView(R2.id.tv_zc)
    TextView tvZc;
    @BindView(R2.id.tv_zc_info)
    TextView tvZcInfo;
    @BindView(R2.id.img_kj)
    ImageView imgKj;
    @BindView(R2.id.tv_price)
    TextView tvPrice;
    @BindView(R2.id.tv_cny)
    TextView tvCny;
    @BindView(R2.id.rl_content)
    RecyclerView rlContent;
    UserBean userBean;
    private boolean showAsset = false;
    private List<CentenItemBean> data = new ArrayList<>();
    private String usdrt, money;

    @Override
    public int getLayoutId() {
        return R.layout.mine_fragment_mine;
    }

    @Override
    public MinePresenter getPresenter() {
        return new MinePresenter();
    }

    @Override
    public void initView() {
        data.add(new CentenItemBean(R.drawable.center_zcgl, getString(R.string.mine_asset_manager)));
        data.add(new CentenItemBean(R.drawable.center_aqzx, getString(R.string.mine_mine_activity_security20)));
        data.add(new CentenItemBean(R.drawable.center_yqfy, getString(R.string.mine_invite_title)));
        data.add(new CentenItemBean(R.drawable.center_gywm, getString(R.string.mine_about_us)));
        data.add(new CentenItemBean(R.drawable.center_yjfk, getString(R.string.mine_yjfk)));
        data.add(new CentenItemBean(R.drawable.center_bzzx, getString(R.string.mine_helper_center)));
        data.add(new CentenItemBean(R.drawable.center_lxkf, getString(R.string.mine_mine_fragment_mine190)));
        data.add(new CentenItemBean(R.drawable.center_shezhi, getString(R.string.mine_setting)));
        rlContent.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        rlContent.addItemDecoration(new ItemDivider().setDividerColor(ContextCompat.getColor(getActivity(), R.color.common_background)).setDividerWith(2));
        BaseAdapter<CentenItemBean> adapter = new BaseAdapter<CentenItemBean>(R.layout.center_item, data, rlContent) {
            @Override
            public void bind(ViewHolder holder, CentenItemBean item) {
                holder.setImageResource(R.id.item_icon, item.getIcon())
                        .setText(R.id.item_name, item.getItem());

            }
        };

        adapter.setOnItemClickListener((adapter1, view, position) ->
        {
            if (!BaseApplication.isLogin() && position < 3) {
                ARouter.getInstance().build(RoutePath.LOGIN_LOGIN).navigation();
                return;
            }
            switch (position) {
                case 0:
                    ARouter.getInstance().build(RoutePath.ASSET).navigation();
                    break;
                case 1:
                    SecurityActivity.start(getContext());
                    break;
                case 2:
                    InviteHomeActivity.start(getContext());
                    break;
                case 3:
//                    ARouter.getInstance().build(RoutePath.APP_GUIDE_WEB).withBoolean(Constans.IS_ABOUT_US, true).navigation();
                    mPresenter.about();
                    break;
                case 4:
                    if (!BaseApplication.isLogin()) {
                        ARouter.getInstance().build(RoutePath.LOGIN_LOGIN).navigation();
                    }
                    FeedbackActivity.start(getContext());
                    break;
                case 5:
                    WebViewActivity.start(getContext(), BaseHttpConfig.BASE_URL + "/wap/help/index.html", App.INSTANCE.getString(R.string.mine_helper_center));
                    break;
                case 6:
                    WebViewActivity.start(getContext(), "https://dwz.cn/gkmdm2c9", App.INSTANCE.getString(R.string.mine_kf));
                    break;
                case 7:
                    SettingActivity.start(getActivity());

                    break;
                default:
                    break;
            }
        });
        refresh.setEnableLoadMore(false);
    }

    @Override
    public void initData() {
        userViewModel.update();
        userViewModel.getUser().observe(this, userBean -> {
            if (userBean != null) {
                this.userBean = userBean;
                if (TextUtils.isEmpty(SpUtil.getString(CommonConfig.MOBILE, ""))) {
                    tvName.setText(BaseApplication.getMobile());
                } else {
                    tvName.setText(BaseApplication.getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
                }

                tvQd.setText(userBean.getQd() == 0 ? getString(R.string.mine_sign_in) : getString(R.string.mine_no_sign_in));
                tvUid.setText("uid:" + userBean.getUid());
                groupLogin.setVisibility(View.VISIBLE);
                groupUnLogin.setVisibility(View.GONE);
            } else {
                groupLogin.setVisibility(View.GONE);
                groupUnLogin.setVisibility(View.VISIBLE);
            }
        });

        ClickUtil.click(tvLogin, view -> ARouter.getInstance().build(RoutePath.LOGIN_LOGIN).navigation());
        ClickUtil.click(tvQd, view -> {
            mPresenter.qd();
        });
        ClickUtil.click(50, imgKj, view -> {
            mPresenter.getMoney();
            showAsset = !showAsset;
            if (showAsset) {
                tvPrice.setText(NumberUtils.keepDown(usdrt, 4));
                tvCny.setText("≈" + NumberUtils.keepDown(money, 2) + "CNY");
                imgKj.setImageResource(R.mipmap.mine_icon_show);
            } else {
                tvPrice.setText("****");
                tvCny.setText("≈****");
                imgKj.setImageResource(R.mipmap.mine_icon_hide);
            }
        });
    }

    @Override
    public void loadData() {
        userViewModel.update();
    }

    @Override
    public void lazyLoad() {
        super.lazyLoad();
        if (userBean != null) {
            mPresenter.getMoney();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (userBean != null) {
            mPresenter.getMoney();
        }
    }

    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }


    public void getSuccess(String usdrt, String money) {
        this.usdrt = usdrt;
        this.money = money;
        Log.d("yds",DateUtils.formatDateTime(getContext(),System.currentTimeMillis(),0) + "------------------------" + usdrt);
        if (showAsset) {
            tvPrice.setText(NumberUtils.keepDown(usdrt, 4));
            tvCny.setText("≈" + NumberUtils.keepDown(money, 2) + "CNY");
            imgKj.setImageResource(R.mipmap.mine_icon_show);
        } else {
            tvPrice.setText("****");
            tvCny.setText("≈****");
            imgKj.setImageResource(R.mipmap.mine_icon_hide);
        }
    }

    public void getFailed(int s1, int s2) {
        usdrt = "0";
        money = "0";
        tvPrice.setText("****");
        tvCny.setText("≈****");
        imgKj.setImageResource(R.mipmap.mine_icon_hide);
    }

    public void about(String s) {
        new TipsNewDialog(getActivity())
                .setTitle(getString(R.string.mine_about_us))
                .setContent(s)
                .setConfirmText(getString(R.string.mine_confirm))
                .setConfirmListener(Dialog::dismiss)
                .show();
    }

    public void qd(String yl) {
        tvQd.setText(getString(R.string.mine_no_sign_in));
        new TipsNewDialog(getActivity())
                .setTitle(getString(R.string.mine_sign_in))
                .setContent(App.INSTANCE.getString(R.string.mine_sign_des) + String.format(App.INSTANCE.getString(R.string.mine_sign_des1), yl))
                .setConfirmText(getString(R.string.mine_to_complete_task))
                .setConfirmListener(Dialog::dismiss).show();
        userViewModel.update();
    }
}
