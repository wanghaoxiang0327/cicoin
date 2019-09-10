package com.sskj.cicoin;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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
public class TransGuideFragment extends BaseFragment<TransGuidePresenter> {
    @BindView(R.id.news_list)
    RecyclerView newsList;
    int size = 10;
    BaseAdapter<TransGuideEntity> newsAdapter;
    SmartRefreshHelper<TransGuideEntity> smartRefreshHelper;

    @Override
    public int getLayoutId() {
        return R.layout.app_fragment_information;
    }

    @Override
    public TransGuidePresenter getPresenter() {
        return new TransGuidePresenter();
    }

    @Override
    public void initView() {
        newsList.setLayoutManager(new LinearLayoutManager(getContext()));
        newsAdapter = new BaseAdapter<TransGuideEntity>(R.layout.app_item_news, null, newsList) {
            @Override
            public void bind(ViewHolder holder, TransGuideEntity item) {
                holder.getView(R.id.news_img).setVisibility(View.GONE);
                holder.setText(R.id.news_title, item.title);
                holder.getView(R.id.time_tv).setVisibility(View.GONE);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NewsBean newsBean = new NewsBean();
                        newsBean.setId(item.id);
                        newsBean.setContent(item.content);
                        newsBean.setDate(item.create_time);
                        newsBean.setTitle(item.title);
                        NewsDetailActivity.start(mContext, newsBean, 3);
                    }
                });
            }

        };
    }

    @Override
    public void initData() {
        smartRefreshHelper = new SmartRefreshHelper<>(getContext(), newsAdapter);
        smartRefreshHelper.setDataSource(new DataSource<TransGuideEntity>() {
            @Override
            public Flowable<List<TransGuideEntity>> loadData(int page) {
                return mPresenter.getInformation(page, size);
            }
        });
    }

    @Override
    public void loadData() {
    }

    public static TransGuideFragment newInstance() {
        TransGuideFragment fragment = new TransGuideFragment();
        return fragment;
    }


}
