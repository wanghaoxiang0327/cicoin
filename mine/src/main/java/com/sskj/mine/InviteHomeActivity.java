package com.sskj.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.allen.library.SuperTextView;
import com.sskj.common.base.BaseActivity;
import com.sskj.common.router.RoutePath;
import com.sskj.common.utils.ClickUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Hey
 * Create at  2019/09/05 08:58:17
 */
@Route(path = RoutePath.INVITE_HOME)
public class InviteHomeActivity extends BaseActivity<InviteHomePresenter> {


    @BindView(R2.id.t1)
    TextView t1;
    @BindView(R2.id.t2)
    TextView t2;
    @BindView(R2.id.t3)
    TextView t3;
    @BindView(R2.id.sp1)
    SuperTextView sp1;
    @BindView(R2.id.sp2)
    SuperTextView sp2;
    @BindView(R2.id.sp3)
    SuperTextView sp3;

    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_invite_home;
    }

    @Override
    public InviteHomePresenter getPresenter() {
        return new InviteHomePresenter();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        ClickUtil.click(sp1, view -> InviteActivity.start(this));
        ClickUtil.click(sp2, view -> TeamActivity.start(this));
        ClickUtil.click(sp3, view -> MoneyActivity.start(this));
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, InviteHomeActivity.class);
        context.startActivity(intent);
    }


}
