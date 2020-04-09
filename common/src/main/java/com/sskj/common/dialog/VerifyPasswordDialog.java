package com.sskj.common.dialog;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;
import com.lzy.okgo.OkGo;
import com.sskj.common.App;
import com.sskj.common.BaseApplication;
import com.sskj.common.R;
import com.sskj.common.R2;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;
import com.sskj.common.utils.CapUtils;
import com.sskj.common.utils.ClickUtil;
import com.sskj.common.utils.CopyUtils;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * 密码验证弹框，含短信和谷歌验证
 *
 * @author Hey
 */
public class VerifyPasswordDialog extends BottomSheetDialog {

    private String emailAddress;
    @BindView(R2.id.title_tv)
    TextView titleTv;
    @BindView(R2.id.cancel_tv)
    TextView cancelTv;
    @BindView(R2.id.ps_edt)
    EditText psEdt;
    @BindView(R2.id.ps_layout)
    LinearLayout psLayout;
    @BindView(R2.id.sms_code_edt)
    EditText smsCodeEdt;
    @BindView(R2.id.get_code_tv)
    TextView getCodeTv;
    @BindView(R2.id.sms_layout)
    LinearLayout smsLayout;
    @BindView(R2.id.email_layout)
    LinearLayout emailLayout;
    @BindView(R2.id.email_code_edt)
    EditText emailCodeEdt;
    @BindView(R2.id.get_email_code_tv)
    TextView getEmailCodeTv;

    @BindView(R2.id.google_code_edt)
    EditText googleCodeEdt;
    @BindView(R2.id.past_tv)
    TextView pastTv;
    @BindView(R2.id.google_layout)
    LinearLayout googleLayout;
    @BindView(R2.id.submit)
    Button submit;

    private boolean showPS;
    private boolean showSMS;
    private boolean showGoogle;
    private boolean showEmail;

    private OnConfirmListener onConfirmListener;
    private DisposableSubscriber<Long> disposableSubscriber;

    private int smsType;

    /**
     * @param context    Context
     * @param showSMS    显示短信验证
     * @param showGoogle 显示谷歌验证
     * @param showPs     显示资金密码
     * @param smsType    短信验证类型
     */
    public VerifyPasswordDialog(@NonNull Context context, boolean showSMS, boolean showGoogle, boolean showPs, int smsType) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.common_dialog_verify_password, null);
        setContentView(view);
        ButterKnife.bind(this, view);
        this.showSMS = showSMS;
        this.showGoogle = showGoogle;
        this.showPS = showPs;
        this.smsType = smsType;
        initView(context);
    }


    public VerifyPasswordDialog(@NonNull Context context, boolean showSMS, boolean showEmail, boolean showGoogle, boolean showPs, String email) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.common_dialog_verify_password, null);
        setContentView(view);
        ButterKnife.bind(this, view);
        this.showSMS = showSMS;
        this.showGoogle = showGoogle;
        this.showPS = showPs;
        this.showEmail = showEmail;
        this.emailAddress = email;
        initView(context);
    }

    private void initView(Context context) {
        psLayout.setVisibility(showPS ? View.VISIBLE : View.GONE);
        googleLayout.setVisibility(showGoogle ? View.VISIBLE : View.GONE);
        smsLayout.setVisibility(showSMS ? View.VISIBLE : View.GONE);
        emailLayout.setVisibility(showEmail ? View.VISIBLE : View.GONE);
        cancelTv.setOnClickListener(v -> {
            dismiss();
        });
        ClickUtil.click(submit, view -> {
            if (onConfirmListener != null) {
                if (showPS) {
                    if (TextUtils.isEmpty(psEdt.getText())) {
                        ToastUtils.show(psEdt.getHint());
                        return;
                    }
                }
                if (showSMS) {
                    if (TextUtils.isEmpty(smsCodeEdt.getText())) {
                        ToastUtils.show(smsCodeEdt.getHint());
                        return;
                    }
                }

                if (showGoogle) {
                    if (TextUtils.isEmpty(googleCodeEdt.getText())) {
                        ToastUtils.show(googleCodeEdt.getHint());
                        return;
                    }
                }

                if (showEmail) {
                    if (TextUtils.isEmpty(emailCodeEdt.getText())) {
                        ToastUtils.show(emailCodeEdt.getHint());
                        return;
                    }
                }
                if (showEmail) {
                    onConfirmListener.onConfirm(this, psEdt.getText().toString(), emailCodeEdt.getText().toString(), googleCodeEdt.getText().toString());
                } else {
                    onConfirmListener.onConfirm(this, psEdt.getText().toString(), smsCodeEdt.getText().toString(), googleCodeEdt.getText().toString());
                }
            }
        });

        pastTv.setOnClickListener(v -> {
            googleCodeEdt.setText(CopyUtils.getTextFromClip(getContext()));
        });
        //发送验证码

        ClickUtil.click(getCodeTv, view -> {
            CapUtils.registerCheck(context, smsType + "", () -> {
                startTimeDown(getCodeTv);
            });

        });
        //发送邮箱验证码
        ClickUtil.click(getEmailCodeTv, view -> {
            CapUtils.registerCheck(context,""+smsType,()->startTimeDown(getEmailCodeTv));
        });
    }

    public VerifyPasswordDialog setOnConfirmListener(OnConfirmListener onConfirmListener) {
        this.onConfirmListener = onConfirmListener;
        return this;
    }

    public void startTimeDown(TextView getCodeView) {
        getCodeView.post(() -> {
            getCodeView.setEnabled(false);
            getCodeView.setTextColor(ContextCompat.getColor(getContext(), R.color.common_hint));
        });

        disposableSubscriber = new DisposableSubscriber<Long>() {
            @Override
            public void onNext(Long aLong) {
                int time = (int) (60 - aLong);
                if (getCodeView != null) {
                    getCodeView.setText(time + App.INSTANCE.getString(R.string.common_baseActivity1));
                }
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.toString());
                ToastUtils.show(App.INSTANCE.getString(R.string.common_fssb));
                getCodeView.setEnabled(true);
            }

            @Override
            public void onComplete() {
                if (getCodeView != null) {
                    getCodeView.setText(App.INSTANCE.getString(R.string.common_baseActivity2));
                    getCodeView.setEnabled(true);
                    getCodeView.setTextColor(ContextCompat.getColor(getContext(), R.color.common_white));
                }
                if (!disposableSubscriber.isDisposed()) {
                    disposableSubscriber.dispose();
                    disposableSubscriber = null;
                }

            }
        };

        Flowable.interval(0, 1, TimeUnit.SECONDS, Schedulers.newThread())
                .take(60)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableSubscriber);

    }


    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (disposableSubscriber != null) {
            disposableSubscriber.dispose();
        }
    }

    public interface OnConfirmListener {
        /**
         * 点击确认按钮
         *
         * @param dialog
         */
        void onConfirm(VerifyPasswordDialog dialog, String ps, String sms, String google);
    }


}
