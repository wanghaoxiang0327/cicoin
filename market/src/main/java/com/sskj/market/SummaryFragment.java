package com.sskj.market;


import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.widget.TextView;

import com.sskj.common.App;
import com.sskj.common.base.BaseFragment;
import com.sskj.common.utils.CopyUtils;
import com.sskj.market.data.Summary;

import butterknife.BindView;

/**
 * 币种简介
 *
 * @author Hey
 * created at 2018/4/10 17:18
 */
public class SummaryFragment extends BaseFragment<SummaryPresenter> {

    @BindView(R2.id.summary_name)
    TextView summaryName;
    @BindView(R2.id.summary_crateTime)
    TextView summaryCrateTime;
    @BindView(R2.id.summary_create_num)
    TextView summaryCreateNum;
    @BindView(R2.id.summary_price)
    TextView summaryPrice;
    @BindView(R2.id.summary_white_paper)
    TextView summaryWhitePaper;
    @BindView(R2.id.summary_website)
    TextView summaryWebsite;
    @BindView(R2.id.summary_content)
    AppCompatTextView summaryContent;
    String code;

    public SummaryFragment() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.market_fragment_summary;
    }

    @Override
    public SummaryPresenter getPresenter() {
        return new SummaryPresenter();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        code = getArguments().getString("code");

    }

    @Override
    public void loadData() {

    }

    @Override
    public void lazyLoad() {
        mPresenter.getData(code);
    }

    public void setData(Summary data) {
        setText(summaryName, splitText(data.getPname()));
        setText(summaryCrateTime, data.getFxtime());
        setText(summaryCreateNum, data.getFxnum()+ App.INSTANCE.getString(R.string.marketStrSummaryFragment201));
        setText(summaryPrice, data.getFxprice());
        setText(summaryWhitePaper, data.getFxbook());
        setText(summaryWebsite, data.getFxweb());
        setText(summaryContent, data.getMemo());
        summaryWebsite.setOnLongClickListener(v -> {
            CopyUtils.copy(getContext(),summaryWebsite.getText().toString());
            return true;
        });
        summaryWhitePaper.setOnLongClickListener(v -> {
            CopyUtils.copy(getContext(),summaryWhitePaper.getText().toString());
            return true;
        });

    }

    public String splitText(String text){
        if (text.contains("_")){
            text= text.split("_")[0];
        }
        return text;
    }


    /**
     * @param code 币种编号
     * @return
     */
    public static SummaryFragment newInstance(String code) {
        SummaryFragment fragment = new SummaryFragment();
        Bundle bundle = new Bundle();
        bundle.putString("code", code);
        fragment.setArguments(bundle);
        return fragment;
    }

}
