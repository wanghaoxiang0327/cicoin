package com.sskj.common.dialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sskj.common.R;
import com.sskj.common.utils.ScreenUtil;
import com.sskj.common.view.LoadingView;

public class LoadingProgressDialog extends AlertDialog {

    ImageView loadingView;

    public LoadingProgressDialog(Context context) {
        super(context,R.style.common_custom_dialog);
        View view = LayoutInflater.from(context).inflate(R.layout.common_loading_dialog, null);
        setView(view);
        loadingView = view.findViewById(R.id.loading_view);

        setCancelable(false);
    }


    @Override
    public void show() {
        super.show();
        Glide.with(getContext())
                .load(R.mipmap.ci_loading)
                .into(loadingView);
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        int width = (int) (ScreenUtil.getScreenWidth(getContext()) * 0.4);
        int height = (int) (ScreenUtil.getScreenWidth(getContext()) * 0.4);
        layoutParams.width = width;
        layoutParams.height = height;
        getWindow().setAttributes(layoutParams);
    }

    @Override
    public void dismiss() {
        super.dismiss();
//        loadingView.getIndeterminateDrawable().stop();
    }

}
