package com.sskj.common.http;

import com.sskj.common.BuildConfig;

public class HttpConfig {

    public static final String CUSTOME = "/Home/Users/my_customer";
    public static final String UPDATE_LOGIN_PWD = "/Home/Users/xiugai_pwd";
    public static final String UPDATE_TRADE_PWD = "/Home/Users/reset_tpwd";
    public static final String ABOUT_US = "/Home/Users/about";
    public static final String SHARE_DETAIL = "/Home/Users/my_broker";
    public static final String GASSETLIST = "";
    public static final String ZOOM_RECORD = "";


    public static String BASE_URL = BuildConfig.IP;


    //==================================================

    public static final String CHECK_VERSION = "/Home/Version/check_version";


    /**
     * 行情数据
     */
    public static final String GET_PRODUCT = "/home/ajax/getpro";

    public static final String BANNER = "/Home/Sign/bannerfind";

    public static final String NOTICE_LIST = "/Home/Qbw/zixun";
    public static final String INFORMATION = "/Home/Users/zixun";
    public static final String INFORMATION_DETAIL = "/Home/Users/zixun_detail";

    public static final String NOTICE_DETAIL = "/Home/Qbw/zixun_detail";


    public static final String WEB_AGGREE = "/Home/ajax/get_web_agree";

    //=======================交易==============================
    //币种资产
    public static final String COINASSET = "/home/user/wall_list";

    //交易币种
    public static final String TRADECOIN = "/home/Contract/pro_list";

    //创建订单
    public static final String CREATE_ORDER = "/home/Contract/create_order";

    //我的订单
    public static final String MYORDER = "/Home/Contract/contract_record";

    public static final String ORDER_DETAIL = "/Home/contract/contract_detail";

    public static final String ADDRESS_MANAGE = "/Home/Users/AddrManage";
    public static final String GET_TRANSFER = "/home/order/bTranfer";
    public static final String ASSETTRANSFER = "/home/order/tranfer";
    //========================mine================================

    public static final String USER_INFO = "/Home/users/user_info";

    public static final String ASSET_RECORS = "/home/users/finance_list";

    public static final String BIND_EMAIL = "/Home/users/binding_email";

    public static final String BIND_MOBILE = "/Home/users/binding_mobile";

    public static final String BIND_GOOGLE = "/home/google/checkGoogleCommand";

    public static final String FORGET_PS = "/home/qbw/reset_opwd";


    public static final String LOGIN = "/Home/Users/user_login";

    public static final String SEND_SMS = "/Home/Qbw/send_sms";

    public static final String REGISTER = "/Home/Qbw/register";

    public static final String FORGET_LOGIN_PS = "/Home/Users/reset_opwd";


    public static final String GOOGLE_CHECK = "/Home/users/is_start_google";

    /**
     * 我的推广二维码
     */
    public static final String MY_SPREAD = "/Home/Users/link";

    public static final String PAIMING = "/Home/users/candy_list";
    public static final String REWARD = "/Home/users/candy_frame";
    public static final String RECIVE_REWARD = "/Home/users/candy_rece";
    public static final String PM_RECORD = "/Home/users/mycandy_list";
    public static final String TEAM = "/home/Product/get_team";

    public static final String REWARD_RULE = "/Home/users/candy_reward";
    public static final String LEVEL_LIST = "/home/Product/get_level_list";
    public static final String SHARE = "/Home/Users/tui_user";
    public static final String CUSTOM = "/Home/Users/my_customer";

    public static final String PAY_IN = "/Home/Users/chongzhi";


    public static final String SEND_EMAIL = "/Home/Mail/send_mail";

    public static final String RESET_LOGIN_PS = "/Home/users/xiugai_pwd";

    public static final String RESET_PAY_PS = "/Home/users/reset_tpwd";

    public static final String SET_SMS_STATE = "/Home/User/sms_check";

    public static final String SET_GOOGLE_STATE = "/home/google/set_google_state";

    public static final String GET_GOOGLE_INFO = "/Home/Google/createGoogleCommand";

    public static final String SHARE_INFO = "/Home/users/link";

    public static final String MY_TEAM = "/Home/users/my_down";

    public static final String COMISSION = "/Home/contract/commission_log";

    public static final String DIRECTOR_PROFIT = "/Home/contract/director_bonus";


    //=========================market=====================================

    public static final String PROFIT_ORDER = "/Home/Contract/income_record";


    //==========================Asset==========================================

    public static final String ASSETLIST = "/Home/users/get_asset";

    public static final String ASSET_RECORDS = "/home/users/finance_list";

    public static final String ASSET_TYPE = "/Home/users/caiwu_type";

    public static final String RECHARGE_INFO = "/Home/Users/bpay";

    public static final String TRANSFER_INFO = "/home/users/bTranfer";

    public static final String TRANSFER = "/home/users/tranfer";

    public static final String TRANSFER_RECORD = "/Home/users/tranfer_record";

    public static final String WITHDRAW_INFO = "/Home/Users/tb_home";

    public static final String WITHDRAW = "/Home/Users/ti_bi";

    public static final String WITHDRAW_RECORDS = "/Home/Users/tb_list";

    public static final String RECHARGE_RECORDS = "/Home/Users/cb_list";


    //==============================Address=======================================
    public static final String ADDRESS_LIST = "/Home/Users/Add_list";


    public static final String ADDRESS_EDIT = "/Home/Users/Addredit";

    public static final String ADDRESS_DELETE = "/Home/Users/Addel";
    //==============================MINING===============================================

    public static final String MINING_STOP = "/home/Product/over_product";

    public static final String MINING_ONLINE = "/home/Product/get_mypro";

    public static final String MINING_HOME = "/home/Product/miner_index";

    public static final String MINING_GET = "/home/Product/get_energy";

    public static final String MINING_RECORDS = "/home/Product/run_product";

    //==============================VERIFY===============================================

    public static final String VERIFY_FIRST = "/Home/Users/set_aut";

    public static final String VERIFY_SECOND = "/Home/Users/setAuth";

    public static final String UPLOAD_IMG = "/Home/Users/upload_pic";

    //===========================Market=========================================================

    public static final String MACHINE_LIST = "/home/Product/index";

    public static final String MACHINE_BUY = "/home/Product/buy_product";

    public static final String CODE_INFO = "/home/qbw/get_info";

    public static final String GET_DEEP = "/Home/Ajax/get_shendu";

    public static final String GET_ALL_TRADE = "/home/ajax/RealTimeDeal";


    public static String WS_PANKOU = "ws://47.56.161.148:7275";//盘口
    public static String WS_DEPTH = "ws://47.56.161.148:7274";//深度图
    public static final String GET_LEVER = "/Home/api/get_lever";
    public static final String GET_BALANCE = "/Home/Users/get_balance";

    public static final String GET_DEAL_ORDER = "/Home/Order/chengjiao";
    public static final String CONTACT_CREATE_ORDER = "/Home/Order/add_order";

    public static final String CONTACT_SET_POINT = "/Home/Order/set_poit";
    public static final String GET_ENTRUST_ORDER = "/Home/Order/weituo";
    public static final String CONTACT_CANCEL_ORDER = "/Home/Order/cancellations";

    public static final String GET_HOLD_ORDER = "/Home/Order/chicang";
    public static final String CONTACT_CLOSE_ORDER = "/Home/Order/pingcang";

    public static final String CONTACT_POINT_INFO = "/Home/Order/set_poit_pre";

    public static final String GET_TONGJI = "/Home/Order/tongji";


    //=================Depth======================================
    public static final String GET_PANKOU = "/Home/Ajax/getdepth";
}
