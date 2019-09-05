package com.sskj.mine;

import com.allen.library.SuperTextView;
import com.hjq.toast.ToastUtils;
import com.sskj.common.App;
import com.sskj.common.base.BaseActivity;
import com.sskj.common.user.data.UserBean;
import com.sskj.common.user.model.UserViewModel;
import com.sskj.common.utils.ClickUtil;
import com.sskj.common.utils.TipUtil;
import com.sskj.mine.VerifyHomePresenter;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sskj.mine.R;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * @author Hey
 * Create at  2019/09/04 20:37:04
 */
public class VerifyHomeActivity extends BaseActivity<VerifyHomePresenter> {


    @BindView(R2.id.ll_realName)
    LinearLayout ll_realName;
    @BindView(R2.id.ll_high)
    LinearLayout ll_high;

    @BindView(R2.id.ll_realName_drz)
    LinearLayout ll_realName_drz;
    @BindView(R2.id.ll_realName_yrz)
    LinearLayout ll_realName_yrz;
    @BindView(R2.id.ll_high_drz)
    LinearLayout ll_high_drz;
    @BindView(R2.id.ll_high_yrz)
    LinearLayout ll_high_yrz;
    @BindView(R2.id.tv_state)
    TextView tv_state;
    @BindView(R2.id.tv_high_state)
    TextView tv_high_state;
    @BindView(R2.id.tv_name)
    TextView tv_name;
    @BindView(R2.id.tv_idcard)
    TextView tv_idcard;

    private boolean firstVerify;

    public static final int CODE = 1;
    private UserBean mUserInfo;

    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_verify_home;
    }

    @Override
    public VerifyHomePresenter getPresenter() {
        return new VerifyHomePresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        userViewModel.update();
    }

    @Override
    public void initView() {
        userViewModel.getUser().observe(this, bean -> mUserInfo = bean);

    }

    @Override
    public void initData() {
        //初级认证
        ClickUtil.click(ll_realName, view -> {
            if (mUserInfo == null) {
                return;
            }
            startActivityForResult(new Intent(this, VerifyFirstActivity.class), CODE);
            switch (mUserInfo.getStatus()) {  // 实名认证状态   1   已认证   0  未认证
                case 0:
//                    VerifyFirstActivity.start(this);
//                    startActivityForResult(new Intent(this, VerifyFirstActivity.class), CODE);
                    break;
                case 2:
                    ToastUtils.show(App.INSTANCE.getString(R.string.mine_verifyHomeActivity2));
                    break;
                case 1:
                    ToastUtils.show(App.INSTANCE.getString(R.string.mine_verifyFirstActivity23));
//                    ARouter.getInstance().build(RConfig.MINE_VERIFY_RESULT)
//                            .withBoolean("isSuccess", true)
//                            .navigation();
                    break;
                case 4:
//                    ToastUtils.show(App.INSTANCE.getString(R.string.mine_verifyHomeActivity3));
                    startActivityForResult(new Intent(this, VerifyFirstActivity.class), CODE);
                    break;
            }
        });
        //高级认证
        ClickUtil.click(ll_high, view -> {
            if (mUserInfo.getStatus() == 0) {
                ToastUtils.show(App.INSTANCE.getString(R.string.mine_verifyHomeActivity4));
                return;
            }
            switch (mUserInfo.getAuth_status()) {  // 高级实名认证   1:未认证 2:待审核 3:已认证 4:审核未通过
                case 1:
                    VerifySecondActivity.start(this);
                    break;
                case 2:
                    ToastUtils.show(App.INSTANCE.getString(R.string.mine_verifyHomeActivity5));
                    break;
                case 3:
                    ToastUtils.show(App.INSTANCE.getString(R.string.mine_verifyHomeActivity51));
//                    ARouter.getInstance().build(RConfig.MINE_VERIFY_RESULT)
//                            .withBoolean("isSuccess", true)
//                            .navigation();
                    break;
                case 4:
//                    TipUtil.getSureTip(this, App.INSTANCE.getString(R.string.mine_verifyFirstActivity11), App.INSTANCE.getString(R.string.mine_verifyFirstActivity12) + mUserInfo.getReason(), App.INSTANCE.getString(R.string.mine_verifyFirstActivity13), () -> {
//                        VerifySecondActivity.start(this);
//                    });
//                    VerifySecondActivity.start(this);
//
//                    ARouter.getInstance().build(RConfig.MINE_VERIFY_RESULT)
//                            .withBoolean("isSuccess", false)
//                            .navigation();
                    break;
            }
        });

    }

    public static void start(Context context) {
        Intent intent = new Intent(context, VerifyHomeActivity.class);
        context.startActivity(intent);
    }

    public void updateUi(UserBean user) {
        switch (user.getStatus()) {  // 实名认证状态
            case 0:
//                verifyFirst.setRightString(App.INSTANCE.getString(R.string.mine_verifyHomeActivity6));
                ll_realName_drz.setVisibility(View.VISIBLE);
                ll_realName_yrz.setVisibility(View.GONE);
                break;
            case 2:
//                verifyFirst.setRightString(App.INSTANCE.getString(R.string.mine_verifyHomeActivity7));
                ll_realName_drz.setVisibility(View.VISIBLE);
                ll_realName_yrz.setVisibility(View.GONE);
                tv_state.setText(App.INSTANCE.getString(R.string.mine_verifyHomeActivity7));
                break;
            case 1:
//                verifyFirst.setRightString(App.INSTANCE.getString(R.string.mine_verifyHomeActivity8));
                ll_realName_drz.setVisibility(View.GONE);
                ll_realName_yrz.setVisibility(View.VISIBLE);
//                tv_name.setText(user.getUsername());
//                tv_idcard.setText(user.getIdCard());
                switch (user.getAuth_status()) {  // 高级实名认证
                    case 1:
//                        verifySecond.setRightString(App.INSTANCE.getString(R.string.mine_verifyHomeActivity6));
                        ll_high_drz.setVisibility(View.VISIBLE);
                        ll_high_yrz.setVisibility(View.GONE);
                        tv_high_state.setText(App.INSTANCE.getString(R.string.mine_verifyHomeActivity6));
                        break;
                    case 2:
//                        verifySecond.setRightString(App.INSTANCE.getString(R.string.mine_verifyHomeActivity7));
                        ll_high_drz.setVisibility(View.VISIBLE);
                        ll_high_yrz.setVisibility(View.GONE);
                        tv_high_state.setText(App.INSTANCE.getString(R.string.mine_verifyHomeActivity7));
                        break;
                    case 3:
//                        verifySecond.setRightString(App.INSTANCE.getString(R.string.mine_verifyHomeActivity8));
                        ll_high_drz.setVisibility(View.GONE);
                        ll_high_yrz.setVisibility(View.VISIBLE);
                        break;
                    case 4:
//                        verifySecond.setRightString(App.INSTANCE.getString(R.string.mine_assetFragment3));
                        ll_high_drz.setVisibility(View.VISIBLE);
                        ll_high_yrz.setVisibility(View.GONE);
                        tv_high_state.setText(App.INSTANCE.getString(R.string.mine_assetFragment3));

                        break;
                }
                break;
            case 4:
//                verifyFirst.setRightString(App.INSTANCE.getString(R.string.mine_assetFragment3));
                tv_state.setText(App.INSTANCE.getString(R.string.mine_assetFragment3));
                ll_realName_drz.setVisibility(View.VISIBLE);
                ll_realName_yrz.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == CODE) {
            String name = data.getExtras().getString("name");
            String card = data.getExtras().getString("card");
            if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(card)) {
                tv_name.setText(name);
                tv_idcard.setText(card);
            }
        }
    }

}