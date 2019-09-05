package com.sskj.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.hjq.toast.ToastUtils;
import com.sskj.common.App;
import com.sskj.common.base.BaseActivity;
import com.sskj.common.utils.ClickUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Hey
 * Create at  2019/09/05 10:46:48
 */
public class FeedbackActivity extends BaseActivity<FeedbackPresenter> {


    @BindView(R2.id.et_contact)
    EditText etContact;
    @BindView(R2.id.et_content)
    EditText etContent;
    @BindView(R2.id.submit)
    Button submit;

    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_feedback;
    }

    @Override
    public FeedbackPresenter getPresenter() {
        return new FeedbackPresenter();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

        ClickUtil.click(submit, view -> {
            if (TextUtils.isEmpty(etContent.getText().toString())) {
                ToastUtils.show(App.INSTANCE.getString(R.string.mine_feedbackActivity2));
                return;
            }
            if (TextUtils.isEmpty(etContact.getText().toString())) {
                ToastUtils.show(App.INSTANCE.getString(R.string.mine_feedbackActivity3));
                return;
            }
            mPresenter.sendRequest(etContent.getText().toString(), etContact.getText().toString());
        });
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, FeedbackActivity.class);
        context.startActivity(intent);
    }

    public void success() {
        finish();
    }


}
