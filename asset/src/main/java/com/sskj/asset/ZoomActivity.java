package com.sskj.asset;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjq.toast.ToastUtils;
import com.sskj.asset.data.GAssetBean;
import com.sskj.asset.data.TransferInfoBean;
import com.sskj.common.base.BaseActivity;
import com.sskj.common.router.RoutePath;
import com.sskj.common.utils.ClickUtil;
import com.sskj.common.utils.NumberUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Hey
 * Create at  2019/08/23 14:57:15
 */
@Route(path = RoutePath.ZOOM)
public class ZoomActivity extends BaseActivity<ZoomPresenter> {


    @BindView(R2.id.acount1)
    TextView acount1;
    @BindView(R2.id.acount2)
    TextView acount2;
    @BindView(R2.id.iv_switch)
    ImageView ivSwitch;
    @BindView(R2.id.tv_number)
    TextView tvNumber;
    @BindView(R2.id.edt_input_number)
    EditText edtInputNumber;
    @BindView(R2.id.tv_all)
    TextView tvAll;
    @BindView(R2.id.submit)
    Button submit;
    private int type = 1;
    private double asset;
    private double wall;

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
        mToolBarLayout.setRightButtonOnClickListener(view -> {
            TransferRecordsActivity.start(this,2);
        });
        ClickUtil.click(submit,view -> {
            if (isEmptyShow(edtInputNumber)){
                return;
            }
            float number = Float.parseFloat(getText(edtInputNumber));
            if (type == 1){
                if (number > asset){
                    ToastUtils.show(getString(R.string.asset_no_yue));
                    return;
                }
            }else{
                if (number > wall){
                    ToastUtils.show(getString(R.string.asset_no_yue));
                    return;
                }
            }
        mPresenter.Transfer(type, Float.parseFloat(NumberUtils.keepDown(getText(edtInputNumber),4)));
        });
        ClickUtil.click(tvAll,view -> {
            edtInputNumber.setText(tvNumber.getText());
        });
        ClickUtil.click(ivSwitch,view -> {
            type = type==1?2:1;
            initTextview();
        });
    }
    private void initTextview(){
        switch (type){
            case 1:
                acount1.setText(getString(R.string.asset_fragment_assetaccount1));
                acount2.setText(getString(R.string.asset_fragment_assetaccount2));
                tvNumber.setText(asset+"");
                break;
            case 2:
                acount1.setText(getString(R.string.asset_fragment_assetaccount2));
                acount2.setText(getString(R.string.asset_fragment_assetaccount1));
                tvNumber.setText(wall+"");
                break;
        }
    }
    @Override
    public void initData() {
        type = getIntent().getIntExtra("type",1);
        initTextview();
    }

    @Override
    public void loadData() {
        super.loadData();
        mPresenter.getTransfer();
    }

    public static void start(Context context, int type) {
        Intent intent = new Intent(context, ZoomActivity.class);
        intent.putExtra("type",type);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public void updateui(TransferInfoBean data) {
        asset = data.getAsset();
        wall = data.getWall();
        if (type == 1){
            tvNumber.setText(data.getAsset()+"");
        }else{
            tvNumber.setText(data.getWall()+"");
        }
    }

    public void transfersuccess() {
        finish();
    }
}
