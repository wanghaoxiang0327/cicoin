package com.sskj.common.base;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.nukc.stateview.StateView;
import com.hjq.toast.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.sskj.common.R;
import com.sskj.common.dialog.LoadingProgressDialog;
import com.sskj.common.user.model.UserViewModel;
import com.sskj.common.view.ToolBarLayout;

public abstract class BaseDialogFragment<P extends BasePresenter> extends DialogFragment implements IBaseView {

    protected View rootView;

    protected P mPresenter;


    protected SmartRefreshLayout mRefreshLayout;

    private boolean enableRefresh = true;

    private boolean enableLoadMore = true;

    protected ToolBarLayout mToolBarLayout;

    protected StateView stateView;

    protected UserViewModel userViewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = getPresenter();
        mPresenter.attachView(this);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        initView();
        initData();
        initEvent();
        loadData();
    }

    protected abstract int getLayoutId();

    protected abstract P getPresenter();

    @Override
    public void initEvent() {

    }

    public void showError() {
        if (stateView == null) {
            stateView = StateView.inject(rootView, true);
        }
        stateView.showRetry();
    }


    /**
     * 包裹模式 在view的外层包裹上RefreshLayout
     *
     * @param view
     */
    public void wrapRefresh(View view) {
        if (view != null && view.getParent() != null) {
            initRefreshLayout();
            ViewGroup parentView = (ViewGroup) view.getParent();
            parentView.removeView(view);
            mRefreshLayout.addView(view);
            parentView.addView(mRefreshLayout);
        }
    }


    public void initRefreshLayout() {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.common_refresh_layout, null);
        mRefreshLayout = rootView.findViewById(R.id.refreshLayout);
        mRefreshLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mRefreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
        mRefreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        mRefreshLayout.setEnableRefresh(enableRefresh);
        mRefreshLayout.setEnableLoadMore(enableLoadMore);
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            onRefresh(refreshLayout);
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            onLoadMore(refreshLayout);
        });
    }

    public void onRefresh(RefreshLayout refreshLayout) {

    }

    public void onLoadMore(RefreshLayout refreshLayout) {

    }

    public void stopRefresh() {
        if (mRefreshLayout != null) {
            mRefreshLayout.finishRefresh();
            mRefreshLayout.finishLoadMore();
        }
    }

    public void setEnableRefresh(boolean enableRefresh) {
        this.enableRefresh = enableRefresh;
        mRefreshLayout.setEnableRefresh(enableRefresh);
    }


    public void setEnableLoadMore(boolean enableLoadMore) {
        this.enableLoadMore = enableLoadMore;
        mRefreshLayout.setEnableLoadMore(enableLoadMore);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private LoadingProgressDialog loadingDialog;

    private int request;

    @Override
    public boolean isEmpty(TextView textView) {
        return textView == null || TextUtils.isEmpty(textView.getText());
    }


    @Override
    public boolean isEmptyShow(TextView textView) {
        if (textView == null) {
            return true;
        } else {
            if (TextUtils.isEmpty(textView.getText())) {
                ToastUtils.show(textView.getHint());
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public String getText(TextView textView) {
        if (textView != null) {
            return textView.getText().toString();
        }
        return "";
    }

    @Override
    public int color(@ColorRes int id) {
        return ContextCompat.getColor(getContext(), id);
    }

    @Override
    public Drawable drawable(@DrawableRes int id) {
        return ContextCompat.getDrawable(getContext(), id);
    }

    @Override
    public void setText(TextView textView, String text) {
        if (textView != null && text != null) {
            textView.setText(text);
        }
    }

    @Override
    public void showLoading() {
        request++;
        if (request == 1) {
            if (loadingDialog == null) {
                loadingDialog = new LoadingProgressDialog(getContext());
            }
            if (!loadingDialog.isShowing()) {
                loadingDialog.show();
            }
        }
    }

    @Override
    public void hideLoading() {
        request--;
        if (request == 0) {
            if (loadingDialog != null) {
                loadingDialog.dismiss();
            }
        }
        stopRefresh();
    }

}
