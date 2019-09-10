package com.sskj.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.http.SslError;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.sskj.common.base.BaseActivity;

import butterknife.BindView;

/**
 * @author Hey
 * Create at  2019/07/04
 */
public class WebViewActivity extends BaseActivity<WebViewPresenter> {


    @BindView(R2.id.content)
    WebView content;

    private AgentWeb agentWeb;
    private String url;

    @Override
    public int getLayoutId() {
        return R.layout.common_activity_web_view;
    }

    @Override
    public WebViewPresenter getPresenter() {
        return new WebViewPresenter();
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void initView() {
        url = getIntent().getStringExtra("url");
        mToolBarLayout.setTitle(getIntent().getStringExtra("title"));
//        agentWeb = AgentWeb.with(this)
//                .setAgentWebParent(content, new LinearLayout.LayoutParams(-1, -1))
//                .useDefaultIndicator()
//                .setWebViewClient(new WebViewClient() {
//                    @Override
//                    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//                        CertifiUtils.OnCertificateOfVerification(handler, view.getUrl());
//                    }
//                })
//                .createAgentWeb()
//                .ready()
//                .go(url);
        // 首先设置支持JS脚本
        content.getSettings().setJavaScriptEnabled(true);
        // 支持Js在当前App打开应用，当页面跳转的时候依旧在当前的WebView之中
        content.setWebViewClient(new WebViewClient());
        content.loadUrl(url);
    }

    @Override
    public void initData() {
        mToolBarLayout.setLeftButtonOnClickListener(v -> {
            if (content.canGoBack()){
                content.goBack();
            }else {
                finish();
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //这是一个监听用的按键的方法，keyCode 监听用户的动作，如果是按了返回键，同时Webview要返回的话，WebView执行回退操作，因为mWebView.canGoBack()返回的是一个Boolean类型，所以我们把它返回为true
        if (keyCode == KeyEvent.KEYCODE_BACK && content.canGoBack()) {
            content.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (agentWeb != null) {
            agentWeb.destroy();
        }
        if (content != null) {
            content = null;
        }

    }

    public static void start(Context context, String url, String title) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }


}
