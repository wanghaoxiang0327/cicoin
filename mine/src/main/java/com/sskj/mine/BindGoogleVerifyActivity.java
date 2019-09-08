package com.sskj.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.sskj.common.BaseApplication;
import com.sskj.common.CommonConfig;
import com.sskj.common.base.BaseActivity;
import com.sskj.common.utils.CapUtils;
import com.sskj.common.utils.ClickUtil;
import com.sskj.common.utils.CopyUtils;
import com.sskj.common.utils.SpUtil;
import com.sskj.mine.data.GoogleInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Hey
 * Create at  2019/06/25
 */
public class BindGoogleVerifyActivity extends BaseActivity<BindGoogleVerifyPresenter> {
    @BindView(R2.id.verify_name)
    TextView verifyName;
    @BindView(R2.id.google_code)
    TextView googleCode;
    @BindView(R2.id.verify_code_name)
    TextView verifyCodeName;
    @BindView(R2.id.edt_verify_code)
    EditText edtVerifyCode;
    @BindView(R2.id.qr_code_img)
    ImageView qrCodeImg;
    @BindView(R2.id.copy_tv)
    TextView copyTv;
    @BindView(R2.id.submit)
    Button submit;
    @BindView(R2.id.edt_verify_mobile)
    EditText edtVerifyMobile;
    @BindView(R2.id.get_code_tv)
    TextView getCodeTv;
    private GoogleInfo code;

    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_bind_googl_everify;
    }

    @Override
    public BindGoogleVerifyPresenter getPresenter() {
        return new BindGoogleVerifyPresenter();
    }

    @Override
    public void initView() {
        code = (GoogleInfo) getIntent().getSerializableExtra("code");
        ClickUtil.click(submit, view -> {
            if (isEmptyShow(edtVerifyCode)) {
                return;
            }
            if (isEmptyShow(edtVerifyMobile)) {
                return;
            }
            mPresenter.bindGoogle(getText(edtVerifyCode), edtVerifyMobile.getText().toString(), BaseApplication.getMobile());
        });
        setGoogleInfo(code);
    }

    @Override
    public void initData() {
        ClickUtil.click(getCodeTv, view -> {
            if (isEmptyShow(edtVerifyCode)) {
                return;
            }
            CapUtils.registerCheck(this,"7",()-> startTimeDown(getCodeTv));
        });
        qrCodeImg.setOnLongClickListener(v -> true);

    }

    public static void start(Context context, GoogleInfo code) {
        Intent intent = new Intent(context, BindGoogleVerifyActivity.class);
        intent.putExtra("code", code);
        context.startActivity(intent);
    }


    public void setGoogleInfo(GoogleInfo result) {
        googleCode.setText(result.getCommand());
        Glide.with(this).load(result.getLocal_url()).into(qrCodeImg);
        ClickUtil.click(copyTv, view -> {
            CopyUtils.copy(this, result.getCommand());
        });
    }

    public void bindGoogleSuccess() {
        finish();
    }

    public void sendVerifyCodeSuccess() {

    }

}
