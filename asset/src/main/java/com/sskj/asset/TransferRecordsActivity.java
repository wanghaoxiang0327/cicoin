package com.sskj.asset;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.sskj.asset.data.TransferRecodsBean;
import com.sskj.asset.data.ZoomRecordBean;
import com.sskj.common.DividerLineItemDecoration;
import com.sskj.common.adapter.BaseAdapter;
import com.sskj.common.adapter.ViewHolder;
import com.sskj.common.base.BaseActivity;
import com.sskj.common.helper.DataSource;
import com.sskj.common.helper.SmartRefreshHelper;
import com.sskj.common.utils.DigitUtils;
import com.sskj.common.utils.NumberUtils;
import com.sskj.common.utils.TimeFormatUtil;
import com.sskj.common.view.ToolBarLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Flowable;

/**
 * @author Hey
 * Create at  2019/06/26
 */
public class TransferRecordsActivity extends BaseActivity<TransferRecordsPresenter> {


    @BindView(R2.id.records_list)
    RecyclerView recordsList;

    BaseAdapter<TransferRecodsBean> recordsAdapter;
    BaseAdapter<ZoomRecordBean> zoomRecordAdapter;
    SmartRefreshHelper<TransferRecodsBean> smartRefreshHelper;
    SmartRefreshHelper<ZoomRecordBean> smartRefreshHelper1;
    @BindView(R2.id.tbl)
    ToolBarLayout tbl;

    private String pid;

    private int size = 10;
    private int type = 1;

    @Override
    public int getLayoutId() {
        return R.layout.asset_activity_transfer_records;
    }

    @Override
    public TransferRecordsPresenter getPresenter() {
        return new TransferRecordsPresenter();
    }

    @Override
    public void initView() {
        recordsList.setLayoutManager(new LinearLayoutManager(this));
        recordsList.addItemDecoration(new DividerLineItemDecoration(this)
                .setDividerColor(color(R.color.common_divider))
                .setFirstDraw(false)
                .setLastDraw(false));
        recordsAdapter = new BaseAdapter<TransferRecodsBean>(R.layout.asset_item_transfer_records, null, recordsList) {
            @Override
            public void bind(ViewHolder holder, TransferRecodsBean item) {
                holder.setText(R.id.type_name, item.getMemo())
                        .setText(R.id.count_tv, NumberUtils.keepDown(item.getPrice(), DigitUtils.getDigit(item.getPname())) + " " + item.getPname())
                        .setText(R.id.time_tv, TimeFormatUtil.SF_FORMAT_E.format(item.getAddtime() * 1000))
                        .setText(R.id.account_tv, "ID: " + item.getAccountId());
            }
        };
        zoomRecordAdapter = new BaseAdapter<ZoomRecordBean>(R.layout.asset_item_zoom_records, null, recordsList) {
            @Override
            public void bind(ViewHolder holder, ZoomRecordBean item) {
                holder.setText(R.id.tv_account1, getString(item.getType()==1?R.string.asset_fragment_assetaccount2:R.string.asset_fragment_assetaccount1))
                        .setText(R.id.tv_number, NumberUtils.keepDown(item.getPrice()+"", 2))
                        .setText(R.id.tv_time, TimeFormatUtil.SF_FORMAT_E.format(Long.parseLong(item.getAddtime()) * 1000))
                        .setText(R.id.tv_account2, getString(item.getType()==1?R.string.asset_fragment_assetaccount1:R.string.asset_fragment_assetaccount2));
            }
        };
    }

    @Override
    public void initData() {
        wrapRefresh(recordsList);
        type = getIntent().getIntExtra("type", 1);
        switch (type) {
            case 1:
                smartRefreshHelper = new SmartRefreshHelper<>(this, recordsAdapter);
                smartRefreshHelper.setDataSource(new DataSource<TransferRecodsBean>() {
                    @Override
                    public Flowable<List<TransferRecodsBean>> loadData(int page) {

                        return mPresenter.getTransferRecord(pid, page, size);
                    }
                });
                break;
            case 2:
                tbl.setTitle(getString(R.string.asset_zoom_record));
                smartRefreshHelper1 = new SmartRefreshHelper<>(this, zoomRecordAdapter);
                smartRefreshHelper1.setDataSource(new DataSource<ZoomRecordBean>() {
                    @Override
                    public Flowable<List<ZoomRecordBean>> loadData(int page) {

                        return mPresenter.getZoomRecord(pid, page, size);
                    }
                });
                break;
        }


    }


    @Override
    public void loadData() {
        if (type == 1) {
            smartRefreshHelper.refresh();
        }else{
            smartRefreshHelper1.refresh();
        }
    }

    public static void start(Context context, int type) {
        Intent intent = new Intent(context, TransferRecordsActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
