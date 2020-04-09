package com.sskj.mine;

import com.bumptech.glide.Glide;
import com.hjq.toast.ToastUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.sskj.common.App;
import com.sskj.common.base.BaseActivity;
import com.sskj.common.utils.ClickUtil;
import com.sskj.mine.VerifySecondPresenter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.sskj.mine.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Hey
 * Create at  2019/09/04 21:11:12
 */
public class VerifySecondActivity extends BaseActivity<VerifySecondPresenter> {
    @BindView(R2.id.verify_photo_a)
    ImageView verifyPhotoA;
    @BindView(R2.id.verify_photo_b)
    ImageView verifyPhotoB;
    @BindView(R2.id.verify_photo_hand)
    ImageView verifyPhotoHand;
    @BindView(R2.id.verify_submit)
    Button verifySubmit;
    private final int SELECT_HAND = 1000;
    private final int SELECT_FRONT = 1001;
    private final int SELECT_BACK = 1002;
    private int position = 1;
    private String imgFirst;
    private String imgSecond;
    private String imgThrid;

    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_verify_second;
    }

    @Override
    public VerifySecondPresenter getPresenter() {
        return new VerifySecondPresenter();
    }

    @Override
    public void initView() {

    }

    public static void start(Context context) {
        Intent intent = new Intent(context, VerifySecondActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void initEvent() {
        ClickUtil.click(verifyPhotoHand, view -> selectImage(SELECT_HAND));
        ClickUtil.click(verifyPhotoA, view -> selectImage(SELECT_FRONT));
        ClickUtil.click(verifyPhotoB, view -> selectImage(SELECT_BACK));
        ClickUtil.click(verifySubmit, view -> {
            if (TextUtils.isEmpty(imgFirst)) {
                ToastUtils.show(App.INSTANCE.getString(R.string.mine_mine_activity_verify_second19_j0));
                return;
            }
            if (TextUtils.isEmpty(imgSecond)) {
                ToastUtils.show(App.INSTANCE.getString(R.string.mine_mine_activity_verify_second25_j0));
                return;
            }
            if (TextUtils.isEmpty(imgThrid)) {
                ToastUtils.show(App.INSTANCE.getString(R.string.mine_verifySecondActivity6_j));
                return;
            }
            mPresenter.verifySecond(imgFirst, imgSecond, imgThrid);
        });
    }

    @Override
    public void initData() {

    }

    @SuppressLint("CheckResult")
    private void selectImage(int requestCode) {
        new RxPermissions(this)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .subscribe(granted -> {
                    if (granted) {
                        PictureSelector.create(this)
                                .openGallery(PictureMimeType.ofImage())
                                .previewImage(true)
                                .isCamera(true)
                                .imageFormat(PictureMimeType.JPEG)
                                .maxSelectNum(1)
                                .compress(true)
                                .forResult(requestCode);
                    }
                });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            if (selectList != null && selectList.size() > 0) {
                LocalMedia media = selectList.get(0);
                String url;
                if (media.isCompressed()) {
                    url = media.getCompressPath();
                } else {
                    url = media.getPath();
                }
                switch (requestCode) {
                    case SELECT_HAND:
                        position = 3;
                        Glide.with(this).load(url).into(verifyPhotoHand);
                        break;
                    case SELECT_FRONT:
                        position = 1;
                        Glide.with(this).load(url).into(verifyPhotoA);
                        break;
                    case SELECT_BACK:
                        position = 2;
                        Glide.with(this).load(url).into(verifyPhotoB);
                        break;
                }
                mPresenter.submitVerify(new File(url), position);
            }
        }

    }


    public void verifySecondSuccess() {
        finish();
    }

    public void putList(String url, int position) {
        switch (position) {
            case 1:
                imgFirst = url;
                break;
            case 2:
                imgSecond = url;
                break;
            case 3:
                imgThrid = url;
                break;
        }
    }

    public void uploadFail(int position) {
        switch (position) {
            case 1:
                Glide.with(this).load(getResources().getDrawable(R.mipmap.mine_advance_card3)).into(verifyPhotoHand);
                break;
            case 2:
                Glide.with(this).load(getResources().getDrawable(R.mipmap.mine_advance_card1)).into(verifyPhotoA);
                break;
            case 3:
                Glide.with(this).load(getResources().getDrawable(R.mipmap.mine_advance_card2)).into(verifyPhotoB);
                break;
        }
    }
}
