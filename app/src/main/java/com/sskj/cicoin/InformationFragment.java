package com.sskj.cicoin;

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
    int size = 10;
    BaseAdapter<NewsEntity> newsAdapter;
    SmartRefreshHelper<NewsEntity> smartRefreshHelper;

    @Override
    public int getLayoutId() {
        return R.layout.app_fragment_information;
    }

    @Override
    public InformationPresenter getPresenter() {
        return new InformationPresenter();
    }

    @Override
    public void initView() {
        newsList.setLayoutManager(new LinearLayoutManager(getContext()));
        newsAdapter = new BaseAdapter<NewsEntity>(R.layout.app_item_news, null, newsList) {
            @Override
            public void bind(ViewHolder holder, NewsEntity item) {
                Glide.with(InformationFragment.this).load(item.pic_addr).into((ImageView) holder.getView(R.id.news_img));
                holder.getView(R.id.news_img).setVisibility(View.VISIBLE);
                holder.setText(R.id.news_title, item.bm_title)
                        .setText(R.id.time_tv, item.timestamp);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NewsBean newsBean = new NewsBean();
                        newsBean.setId(item.id);
                        newsBean.setTitle(item.bm_title);
                        NewsDetailActivity.start(mContext, newsBean, 2);
                    }
                });
            }

        };
    }

    @Override
    public void initData() {
        smartRefreshHelper = new SmartRefreshHelper<>(getContext(), newsAdapter);
        smartRefreshHelper.setDataSource(new DataSource<NewsEntity>() {
            @Override
            public Flowable<List<NewsEntity>> loadData(int page) {
                return mPresenter.getNotice(page, size);
            }
        });
    }

    @Override
    public void loadData() {
    }

    public static InformationFragment newInstance() {
        InformationFragment fragment = new InformationFragment();
        return fragment;
    }


}
