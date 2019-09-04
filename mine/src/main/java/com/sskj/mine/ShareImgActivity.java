package com.sskj.mine;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.hjq.toast.ToastUtils;
import com.sskj.common.adapter.BaseAdapter;
import com.sskj.common.adapter.ViewHolder;
import com.sskj.common.base.BaseActivity;
import com.sskj.common.utils.ImgUtil;
import com.sskj.mine.data.Imgbean;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Hey
 * Create at  2019/06/25
 */
public class ShareImgActivity extends BaseActivity<ShareImgPresenter> {


    @BindView(R2.id.qr_code_img)
    ImageView qrCodeImg;
    @BindView(R2.id.share_layout)
    FrameLayout shareLayout;
    @BindView(R2.id.rv_img)
    RecyclerView rvImg;

    private String url;
    private List<Imgbean> imglist = new ArrayList<>();
    private BaseAdapter<Imgbean> adapter;
    private boolean isfrist = true;

    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_share_img;
    }

    @Override
    public ShareImgPresenter getPresenter() {
        return new ShareImgPresenter();
    }
View view1;
    @Override
    public void initView() {
        mToolBarLayout.setRightButtonOnClickListener(view -> {
            new RxPermissions(this)
                    .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe(aBoolean -> {
                        if (aBoolean) {
                            ImgUtil.saveImageToGallery(this, view1);
                        }
                    }, e -> {
                        e.printStackTrace();
                    });
        });
    }

    @Override
    public void initData() {
        url = getIntent().getStringExtra("url");
        Imgbean imgbean = new Imgbean();
        imgbean.setUrl(url);
        imgbean.setImgid(R.mipmap.mine_share_img);
        imgbean.setIsshow(true);
        imglist.add(imgbean);
        Imgbean imgbean1 = new Imgbean();
        imgbean1.setUrl(url);
        imgbean1.setImgid(R.mipmap.mine_share_img2);
        imgbean1.setIsshow(false);
        imglist.add(imgbean1);
        rvImg.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        adapter = new BaseAdapter<Imgbean>(R.layout.mine_item_shareimg,imglist,rvImg,false) {
            @Override
            public void bind(ViewHolder holder, Imgbean item) {
                Glide.with(getBaseContext()).load(item.getUrl()).into((ImageView) holder.getView(R.id.qr_code_img));
                holder.setBackgroundRes(R.id.share_layout,item.getImgid());
                if (isfrist){
                    if (item.isIsshow()) {
                        view1 = holder.getView(R.id.share_layout);
                    }
                }
                if (item.isIsshow()) {
                    ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) holder.getView(R.id.share_layout).getLayoutParams();
                    p.setMargins(5, 5, 5, 5);
                    holder.getView(R.id.share_layout).setLayoutParams(p);
                }else{
                    ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) holder.getView(R.id.share_layout).getLayoutParams();
                    p.setMargins(0, 0, 0, 0);
                    holder.getView(R.id.share_layout).setLayoutParams(p);
                }
            }
        };
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper(){
            @Override
            public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
                isfrist = false;
                int ta = super.findTargetSnapPosition(layoutManager,velocityX,velocityX);
                for (int i = 0;i<imglist.size();i++){
                    if (i == ta){
                        imglist.get(i).setIsshow(true);
                    }else{
                        imglist.get(i).setIsshow(false);
                    }
                }
                adapter.setNewData(imglist);
                return ta;
            }

            @Nullable
            @Override
            public View findSnapView(RecyclerView.LayoutManager layoutManager) {
                view1 = super.findSnapView(layoutManager);
                return view1;
            }
        };
        pagerSnapHelper.attachToRecyclerView(rvImg);
//        Glide.with(this).load(url).into(qrCodeImg);
//        rvImg.addOnScrollListener(new RecyclerView.OnScrollListener() {
//        });
    }

    public static void start(Context context, String url) {
        Intent intent = new Intent(context, ShareImgActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
