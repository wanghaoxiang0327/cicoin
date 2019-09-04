package com.sskj.asset.viewpagercard;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.sskj.asset.R;
import com.sskj.common.CommonConfig;
import com.sskj.common.rxbus.RxBus;
import com.sskj.common.utils.SpUtil;

import java.util.ArrayList;
import java.util.List;

public class CardPagerAdapter extends PagerAdapter implements CardAdapter {

    private List<CardView> mViews;
    private List<CardItem> mData;
    private float mBaseElevation;
    private boolean showAsset = true;
    private updateInterface anInterface;

    public CardPagerAdapter() {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
    }

    public void addCardItem(CardItem item) {
        mViews.add(null);
        mData.add(item);
    }

    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.asset_adapter, container, false);
        container.addView(view);
        bind(mData.get(position), view, position);
        CardView cardView = (CardView) view.findViewById(R.id.cardView);

        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }

        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        mViews.set(position, cardView);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }

    private TextView titleTextView1;
    private TextView titleTextView;
    private TextView contentTextView;

    private void bind(CardItem item, View view1, int position) {
        titleTextView1 = (TextView) view1.findViewById(R.id.asset_textview);
        titleTextView = (TextView) view1.findViewById(R.id.total_asset_tv);
        contentTextView = (TextView) view1.findViewById(R.id.cny_asset_tv);
        CardView cardView = (CardView) view1.findViewById(R.id.cardView);
        if (position == 0) {
            cardView.setCardBackgroundColor(view1.getResources().getColor(R.color.common_tip));
        } else {
            cardView.setCardBackgroundColor(view1.getResources().getColor(R.color.common_normal_text));
        }
        titleTextView1.setText(item.getAssetacount());
        titleTextView.setText(item.getTitle());
        contentTextView.setText("≈" + item.getText() + "CNY");
        ImageView hideAssetImg = view1.findViewById(R.id.hide_asset_img);
        showAsset = SpUtil.getBoolean(CommonConfig.SHOWASSET,true);
        if (showAsset){
            titleTextView.setText(item.getTitle());
            contentTextView.setText("≈" + item.getText() + "CNY");
            hideAssetImg.setImageResource(R.mipmap.asset_icon_show);
        }else{
            titleTextView.setText("****");
            contentTextView.setText("****");
            hideAssetImg.setImageResource(R.mipmap.asset_icon_hide);
        }
        hideAssetImg.setOnClickListener(view -> {
            if (showAsset) {
                titleTextView.setText("****");
                contentTextView.setText("****");
                showAsset = false;
                SpUtil.put(CommonConfig.SHOWASSET, showAsset);
                hideAssetImg.setImageResource(R.mipmap.asset_icon_hide);

            } else {
                titleTextView.setText(item.getTitle());
                contentTextView.setText("≈" + item.getText() + "CNY");
                showAsset = true;
                SpUtil.put(CommonConfig.SHOWASSET, showAsset);
                hideAssetImg.setImageResource(R.mipmap.asset_icon_show);
            }
            RxBus.getDefault().post("isShowAsset");
        });
    }

    public interface updateInterface {
        void update(List<CardItem> cardItems);
    }

}
