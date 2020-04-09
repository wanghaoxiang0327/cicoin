package com.sskj.common.utils;

import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.ImageView;

import com.sskj.common.R;

public class EditUtil {

    public static boolean togglePs(EditText editText, ImageView imageView) {
        if (editText.getTransformationMethod() instanceof PasswordTransformationMethod) {
            editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            editText.setSelection(editText.getText().length());
            imageView.setImageResource(R.mipmap.common_icon_show);
            return false;
        } else {
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            editText.setSelection(editText.getText().length());
            imageView.setImageResource(R.mipmap.common_icon_hide);
            return true;
        }
    }
    // 隐藏手机号
    public static String getPhoneHide(String mobile) {
        if (TextUtils.isEmpty(mobile)) {
            return "";
        }
        try {
            return mobile.substring(0, 3) + "****" + mobile.substring(7, 11);
        } catch (Exception e) {
            return mobile;
        }
    }

    // 隐藏邮箱号
    public static String getEmialAddress(String email) {
        if (TextUtils.isEmpty(email)) {
            return "";
        }
        try {
            String num = email.split("@")[0];
            if (num.length() <= 5) {
                return email.substring(0, 1) + "****" + email.substring(num.length(), email.length());
            } else {
                return email.substring(0, 1) + "****" + email.substring(5, email.length());
            }
        } catch (Exception e) {
            return email;
        }
    }

}
