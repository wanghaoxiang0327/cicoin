package com.sskj.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.sskj.common.base.BaseActivity;
import com.sskj.common.utils.ClickUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Hey
 * Create at  2019/09/04 21:11:04
 */
public class VerifyFirstActivity extends BaseActivity<VerifyFirstPresenter> {


    @BindView(R2.id.et_name)
    EditText etName;
    @BindView(R2.id.ps_edt)
    EditText psEdt;
    @BindView(R2.id.submit)
    Button submit;

    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_verify_first;
    }

    @Override
    public VerifyFirstPresenter getPresenter() {
        return new VerifyFirstPresenter();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        ClickUtil.click(submit,view -> {
            if (isEmpty(etName)) {
                return;
            }
            if (isEmpty(psEdt)) {
                return;
            }
            mPresenter.firstVerify(getText(etName),getText(psEdt));
        });


    }

    public static void start(Context context) {
        Intent intent = new Intent(context, VerifyFirstActivity.class);
        context.startActivity(intent);
    }

    public void verifySuccess() {
        finish();
    }

}
