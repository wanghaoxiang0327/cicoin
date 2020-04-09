package com.sskj.mine;

import com.hjq.toast.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import  com.sskj.common.base.BasePresenter;
import com.sskj.common.http.BaseHttpConfig;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;
import com.sskj.mine.VerifySecondActivity;
import com.sskj.mine.data.ImgHighBean;

import java.io.File;


/**
 * @author Hey
 * Create at  2019/09/04 21:11:12
 */
class VerifySecondPresenter extends BasePresenter<VerifySecondActivity> {
    public void submitVerify(File file, int position) {
        OkGo.<HttpResult<ImgHighBean>>post(HttpConfig.BASE_URL + HttpConfig.HIGH_VERIFY_IMG)
                .isMultipart(false)
                .params("file", file)
                .execute(new JsonCallBack<HttpResult<ImgHighBean>>(this) {
                    @Override
                    protected void onNext(HttpResult<ImgHighBean> result) {
                        if (result.getStatus() == BaseHttpConfig.OK) {
                            ToastUtils.show(result.getMsg());
                            ImgHighBean hignBean = (ImgHighBean) result.getData();
                            mView.putList(hignBean.getUrl(), position);
                        }
                    }

                    @Override
                    public void onError(Response<HttpResult<ImgHighBean>> response) {
                        super.onError(response);
                        HttpResult body = response.body();
                        mView.uploadFail(position);
                        ToastUtils.show(body.getMsg());
                    }
                });
    }


    // 实名二级认证
    public void verifySecond(String cardimg1, String cardimg2, String cardimg3) {
        OkGo.<HttpResult>post(HttpConfig.BASE_URL + HttpConfig.HIGH_VERIFY)
                .params("cardimg1", cardimg1)  // 正面
                .params("cardimg2", cardimg2)//身份证背面
                .params("cardimg3", cardimg3)
                .execute(new JsonCallBack<HttpResult>(this) {
                    @Override
                    protected void onNext(HttpResult result) {
                        if (result.getStatus() == BaseHttpConfig.OK) {
                            ToastUtils.show(result.getMsg());
                            mView.verifySecondSuccess();
                        }
                    }
                });
    }
}
