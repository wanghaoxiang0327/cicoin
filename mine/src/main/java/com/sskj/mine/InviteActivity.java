package com.sskj.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sskj.common.WebActivity;
import com.sskj.common.base.BaseActivity;
import com.sskj.common.utils.ClickUtil;
import com.sskj.common.utils.CopyUtils;
import com.sskj.mine.data.ShareInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Hey
 * Create at  2019/06/25
 */
public class InviteActivity extends BaseActivity<InvitePresenter> {


    @BindView(R2.id.code)
    ImageView code;
    @BindView(R2.id.nu)
    TextView nu;
    @BindView(R2.id.copy)
    TextView copy;
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

        //复制
        ClickUtil.click(copy, view -> {
            CopyUtils.copy(this, getText(nu));
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
        nu.setText(result.getTgno());
        Glide.with(this)
                .load(result.getQrc())
                .into(code);
    }

}
