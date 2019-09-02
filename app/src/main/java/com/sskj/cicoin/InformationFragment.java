package com.sskj.cicoin;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sskj.common.adapter.BaseAdapter;
import com.sskj.common.adapter.ViewHolder;
import com.sskj.common.base.BaseFragment;
import com.sskj.common.helper.DataSource;
import com.sskj.common.helper.SmartRefreshHelper;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Flowable;

/**
 * 0行业资讯 1 平台公告
 * Create at  2019/06/26
 */
public class InformationFragment extends BaseFragment<InformationPresenter> {


    @BindView(R.id.news_list)
    RecyclerView newsList;

    private int type=1;

    int size = 10;

    BaseAdapter<NewsBean> newsAdapter;


    SmartRefreshHelper<NewsBean> smartRefreshHelper;

    @Override
    public int getLayoutId() {
        return R.layout.app_fragment_information;
    }

    @Override
    public InformationPresenter getPresenter() {
        return new InformationPresenter();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getArguments() != null) {
            type = getArguments().getInt("type");
        }
    }

    @Override
    public void initView() {
        newsList.setLayoutManager(new LinearLayoutManager(getContext()));
        newsAdapter = new BaseAdapter<NewsBean>(R.layout.app_item_news, null, newsList) {
            @Override
            public void bind(ViewHolder holder, NewsBean item) {
                if (type == 1) {
                    holder.getView(R.id.news_img).setVisibility(View.GONE);
                } else {
                    Glide.with(InformationFragment.this).load(item.getPic_addr()).into((ImageView) holder.getView(R.id.news_img));
                    holder.getView(R.id.news_img).setVisibility(View.VISIBLE);
                }
                holder.setText(R.id.news_title, item.getTitle())
                        .setText(R.id.time_tv, item.getDate());

            }
        };
    }

    @Override
    public void initData() {
        smartRefreshHelper = new SmartRefreshHelper<>(getContext(),newsAdapter);
        smartRefreshHelper.setDataSource(new DataSource<NewsBean>() {
            @Override
            public Flowable<List<NewsBean>> loadData(int page) {
                if (type == 1) {
                    return mPresenter.getNotice(page, size);
                } else {
                    return mPresenter.getInformation(page, size);
                }
            }
        });
    }

    @Override
    public void loadData() {
    }

    /**
     * @param type 0行业资讯 1 平台公告
     * @return
     */
    public static InformationFragment newInstance(int type) {
        InformationFragment fragment = new InformationFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }


}
