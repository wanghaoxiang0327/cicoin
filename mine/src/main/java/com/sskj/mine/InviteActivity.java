package com.sskj.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.widget.TextView;

import com.sskj.common.WebActivity;
import com.sskj.common.base.BaseActivity;
import com.sskj.common.http.HttpResult;
import com.sskj.common.utils.ClickUtil;
import com.sskj.common.utils.CopyUtils;
import com.sskj.common.view.LoadingView;
import com.sskj.common.view.ToolBarLayout;
import com.sskj.mine.data.ShareInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Hey
 * Create at  2019/06/25
 */
public class InviteActivity extends BaseActivity<InvitePresenter> {


    @BindView(R2.id.copy_tv)
    TextView copyTv;
    @BindView(R2.id.invite_code)
    TextView inviteCode;
    @BindView(R2.id.mine_constraintlayout)
    ConstraintLayout mineConstraintlayout;
    @BindView(R2.id.share_img)
    TextView shareImg;
    @BindView(R2.id.my_team)
    TextView myTeam;
    @BindView(R2.id.commission_detail)
    TextView commissionDetail;

    private ShareInfo shareInfo;

    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_invite;
    }

    @Override
    public InvitePresenter getPresenter() {
        return new InvitePresenter();
    }

    @Override
    public void initView() {
        mToolBarLayout.setRightButtonOnClickListener(v -> {
            WebActivity.start(this, 2);
        });
    }

    @Override
    public void initData() {
        //我的团队
        ClickUtil.click(myTeam, view -> {
            MyTeamActivity.start(this);
        });
        //推广海报
        ClickUtil.click(shareImg, view -> {
            ShareImgActivity.start(this,shareInfo.getQrc());
        });
        //佣金明细
        ClickUtil.click(commissionDetail, view -> {
            CommissionActivity.start(this);
        });
        //复制
        ClickUtil.click(copyTv, view -> {
            CopyUtils.copy(this, getText(inviteCode));
        });
    }

    @Override
    public void loadData() {
        mPresenter.getShareInfo();
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, InviteActivity.class);
        context.startActivity(intent);
    }


    public void setShareInfo(ShareInfo result) {
        shareInfo = result;
        inviteCode.setText(result.getTgno());
    }
}
