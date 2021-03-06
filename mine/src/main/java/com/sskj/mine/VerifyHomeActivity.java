package com.sskj.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjq.toast.ToastUtils;
import com.sskj.common.App;
import com.sskj.common.base.BaseActivity;
import com.sskj.common.router.RoutePath;
import com.sskj.common.user.data.UserBean;
import com.sskj.common.utils.ClickUtil;
import com.sskj.common.utils.SpUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Hey
 * Create at  2019/09/04 20:37:04
 */
@Route(path = RoutePath.VERIFY_HOME)
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
    @BindView(R2.id.state1)
    ImageView state1;
    @BindView(R2.id.state2)
    TextView state2;
    @BindView(R2.id.state3)
    ImageView state3;
    @BindView(R2.id.state4)
    TextView state4;
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
        userViewModel.getUser().observe(this, bean -> {
            mUserInfo = bean;
            switch (mUserInfo.getStatus()) {  // 实名认证状态   1 未认证 2 待审核 3 已通过  4拒绝
                case 1:
                    state1.setVisibility(View.GONE);
                    state2.setText(getString(R.string.mine_no_decertification));
                    break;
                case 2:
                    state2.setText(getString(R.string.mine_verifyHomeActivity7));
                    break;
                case 3:
                    state1.setVisibility(View.VISIBLE);
                    state2.setText(getString(R.string.mine_decertification_success));
                    assert bean != null;
                    tv_name.setText(bean.getRealname());
                    tv_idcard.setText(bean.getIdcard());
                    break;
                case 4:
                    state2.setText(getString(R.string.mine_decertification_fail));
                    break;
            }
            switch (mUserInfo.getAuth_status()) {  // 高级实名认证   1:未认证 2:待审核 3:已认证 4:审核未通过
                case 1:
                    state3.setVisibility(View.GONE);
                    state4.setText(getString(R.string.mine_no_decertification));
                    break;
                case 2:
                    state4.setText(getString(R.string.mine_verifyHomeActivity7));
                    break;
                case 3:
                    state3.setVisibility(View.VISIBLE);
                    state4.setText(getString(R.string.mine_decertification_success));
                    break;
                case 4:
                    state4.setText(getString(R.string.mine_decertification_fail));
                    break;
            }
        });


    }

    @Override
    public void initData() {
        //初级认证
        ClickUtil.click(ll_realName, view -> {
            if (mUserInfo == null) {
                return;
            }
            switch (mUserInfo.getStatus()) {  // 实名认证状态   1 未认证 2 待审核 3 已通过  4拒绝
                case 1:
                    VerifyFirstActivity.start(this);
                    break;
                case 2:
                    ToastUtils.show(App.INSTANCE.getString(R.string.mine_verifyHomeActivity2));
                    break;
                case 3:
                    ToastUtils.show(App.INSTANCE.getString(R.string.mine_verifyFirstActivity23));
                    break;
                case 4:
                    startActivityForResult(new Intent(this, VerifyFirstActivity.class), CODE);
                    break;
            }

        });
        //高级认证
        ClickUtil.click(ll_high, view -> {
            if (mUserInfo.getStatus() == 1) {
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
                    break;
                case 4:
                    VerifySecondActivity.start(this);
                    break;
            }
        });
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, VerifyHomeActivity.class);
        context.startActivity(intent);
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
