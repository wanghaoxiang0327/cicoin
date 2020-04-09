﻿﻿﻿# **Android代码规范**


**开发插件**

- PxCook 标注工具：https://www.fancynode.com.cn/pxcook

- Activity与Fragment快速生成 
    
    HeyMVP：http://114.116.114.182:8088/android/common/raw/master/HeyMVP.jar
    
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







