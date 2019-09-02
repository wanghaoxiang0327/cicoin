**开发框架**
- SuperTextView  https://github.com/lygttpod/SuperTextView   ![avatar](https://camo.githubusercontent.com/858932c75508773ecdb50a884804841962a0ddc7/687474703a2f2f6f736e6f65783676662e626b742e636c6f7564646e2e636f6d2f737570657274657874766965772e6a7067)     
- 下拉刷新 SmartRefreshHelper https://github.com/scwang90/SmartRefreshLayout    
- 屏幕相关 ScreentUtils 
- 下拉菜单 HeySpinner   
- 正则校验 PatternUtils 密码，手机号，身份证，邮箱，姓名    
- 富文本显示 RichText    https://github.com/zzhoujay/RichText   
- 小数位限制 MoneyValueFilter 
- ``小数位format``NumberUtils
- Tab TabLayout 支持选中改变字体大小，选中前监听
- 保存图片 ImgUtil
- Socket MarketWebSocket    
- RxBus 使用时请注意post与postPre的区别（注册为pre才能接收到postPre事件）

**新增**

- RefreshHelper 实现自动下拉刷新和加载更多 参考示例：http://114.116.114.182:8088/android/common/blob/master/app/src/main/java/com/sskj/demo/InformationFragment.java


﻿﻿﻿﻿# **Android代码规范**


**开发插件**

- PxCook 标注工具：https://www.fancynode.com.cn/pxcook

- Activity与Fragment快速生成 
    
    HeyMVP：http://114.116.114.182:8088/android/common/blob/master/common/HeyMVP.jar
    
    使用说明:
        右键需要创建Activity或Fragment的目录，选择第一项HeyMVP,输入名称,(Activity:xxxActivity，Fragment:xxxFragment),
        确定后会在该目录下生成Activity或Fragment,以及相应的Layout
- 代码的编写规范
    
    Alibaba Java Coding Guidelines: https://blog.csdn.net/tanga842428/article/details/78723150



**资源文件定义及引用**
- color
	- 背景
	    
        一般界面 -> common_background   

        顶部ToolBar -> common_action_bar    
    
	    底部导航栏 -> common_tab
	
	- 文字  
	    
        Title文字 -> common_action_text	

        提示性文字颜色 -> common_hint   

        高亮文字颜色  ->　common_tip    

        按钮文字颜色 ->  common_button_text 

        文字颜色 -> common_text
		
	- 分割线

        common_divider



**注释！注释！注释！**

- 类的头部加注释，作者，时间，作用

- 如果使用EventBus或者RxBus等之类，需在发送方和接受方注释，如：


 ```
 * 获取币种信息
 *
 * RxBus {@link com.sskj.gcc.ui.fragment.HangqingFragment#refreshCoin(CoinBean)}
 *
 * @param code 币种名称
 */
public void getProduct(String code) {
	RxBus.getDefault().post(httpData.getData().get(0));
}
```







