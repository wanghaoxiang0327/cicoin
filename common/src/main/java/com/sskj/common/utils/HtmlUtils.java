package com.sskj.common.utils;

/**
 * Created by Administrator on 2017/12/6 0006.
 */

public class HtmlUtils {

    /**
     * 添加头部
     */
    public static String getHtmlData(String bodyHTML) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>img{max-width: 100%; width:auto; height:auto;}</style>" +
                "</head>";
        return "<html>" + head + "<body style='background:#001925;color:white;'>" + bodyHTML + "</body><ml>";
    }
}
