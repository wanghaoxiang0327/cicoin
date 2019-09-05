package com.sskj.asset;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sskj.common.base.BaseActivity;
import com.sskj.common.data.CoinListEntity;
import com.sskj.common.router.RoutePath;
import com.sskj.common.utils.ClickUtil;
import com.sskj.common.view.ToolBarLayout;

import java.util.List;

import butterknife.BindView;

/**
 * @author Hey
 * Create at  2019/08/23 14:57:15
 */
@Route(path = RoutePath.ZOOM)
public class ZoomActivity extends BaseActivity<ZoomPresenter> {
    @BindView(R2.id.toolbar)
    ToolBarLayout toolbar;
    @BindView(R2.id.tv_old_coin)
    TextView tvOldCoin;
    @BindView(R2.id.tv_old_amount)
    TextView tvOldAmount;
    @BindView(R2.id.iv_exchange)
    ImageView ivExchange;
    @BindView(R2.id.tv_new_coin)
    TextView tvNewCoin;
    @BindView(R2.id.tv_new_amount)
    TextView tvNewAmount;
    @BindView(R2.id.et_input_exchange_num)
    EditText etInputExchangeNum;
    @BindView(R2.id.et_arrival_num)
    EditText etArrivalNum;
    @BindView(R2.id.et_asset_pwd)
    EditText etAssetPwd;
    @BindView(R2.id.submit)
    Button submit;

    @Override
    public int getLayoutId() {
        return R.layout.asset_activity_zoom;
    }

    @Override
    public ZoomPresenter getPresenter() {
        return new ZoomPresenter();
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        mPresenter.getExchangeInfo();
    }

    @Override
    public void initEvent() {
        super.initEvent();
        toolbar.setRightButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExchangeDetailActivity.start(ZoomActivity.this);
            }
        });
    }

    @Override
    public void loadData() {
        super.loadData();
        mPresenter.getExchangeInfo();
    }

    public static void start(Context context, int type) {
        Intent intent = new Intent(context, ZoomActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    public void setCoinList(List<CoinListEntity> data) {
        if (data != null && data.size() > 0) {

        }
    }
}
