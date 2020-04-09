package com.sskj.common.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.SuperButton;
import com.hjq.toast.ToastUtils;
import com.lzy.okgo.OkGo;
import com.sskj.common.App;
import com.sskj.common.CommonConfig;
import com.sskj.common.R;
import com.sskj.common.R2;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;
import com.sskj.common.utils.CapUtils;
import com.sskj.common.utils.ClickUtil;
import com.sskj.common.utils.CopyUtils;
import com.sskj.common.utils.ScreenUtil;
import com.sskj.common.utils.SpUtil;
import com.zzhoujay.richtext.RichText;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * 项目包名：com.sskj.common.dialog
 * 项目所属模块：
 * 作者：布兜兜不打豆豆
 * 创建时间：2019年09月04日
 * 类描述：
 * 备注：
 */
public class TipsGogleDialog extends AlertDialog {

    @BindView(R2.id.title)
    TextView title;
    @BindView(R2.id.google_code_edt)
    EditText googleCodeEdt;
    @BindView(R2.id.past_tv)
    TextView pastTv;
    @BindView(R2.id.sms_code_edt)
    EditText smsCodeEdt;
    @BindView(R2.id.getCode)
    TextView getCode;
    @BindView(R2.id.cancel)
    SuperButton cancel;
    @BindView(R2.id.submit)
    SuperButton submit;

    private OnConfirmListener onConfirmListener;
    private DisposableSubscriber<Long> disposableSubscriber;

    private String mobile;

    private View view;


    public TipsGogleDialog(@NonNull Context context,boolean isOpen) {
        super(context, R.style.common_custom_dialog);
        view = LayoutInflater.from(context).inflate(R.layout.common_dialog_verify_gogle, null);
        setView(view);
        ButterKnife.bind(this, view);
        initView(context);
        title.setText(isOpen?App.INSTANCE.getString(R.string.common_gbgogle):App.INSTANCE.getString(R.string.kqgogle));
    }

    private void initView(Context context) {
        ClickUtil.click(cancel, view -> dismiss());

        ClickUtil.click(submit, view -> {
            if (onConfirmListener != null) {
                if (TextUtils.isEmpty(googleCodeEdt.getText().toString())) {
                    return;
                }
                if (TextUtils.isEmpty(smsCodeEdt.getText().toString())) {
                    return;
                }
                onConfirmListener.onConfirm(this, googleCodeEdt.getText().toString(), mobile, smsCodeEdt.getText().toString());
            }
        });

        pastTv.setOnClickListener(v -> {
            googleCodeEdt.setText(CopyUtils.getTextFromClip(getContext()));
        });
        //发送验证码
        ClickUtil.click(getCode, view -> {
            CapUtils.registerCheck(context,"7",()-> startTimeDown(getCode));
        });

    }

    @Override
    public void show() {
        super.show();
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = (int) (ScreenUtil.getScreenWidth(getContext()) * 0.88);
        getWindow().setAttributes(params);
    }

    public TipsGogleDialog setTitle(String name) {
        title.setText(name);
        return this;
    }


    public TipsGogleDialog setOnConfirmListener(OnConfirmListener onConfirmListener) {
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
                getCodeView.setEnabled(true);
                System.out.println(t.toString());
                ToastUtils.show(App.INSTANCE.getString(R.string.common_fssb));
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
        void onConfirm(TipsGogleDialog dialog, String gogole, String mobile, String code);
    }


}

