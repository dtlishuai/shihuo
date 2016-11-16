package com.shihuo.shihuo.network;

/**
 * Created by cm_qiujiaheng on 2016/11/15.
 * 网络请求帮助类
 */

public class NetWorkHelper {

    public static final String ApiHost = "https://59.110.10.19";
    public static final String ApiPath = "/shihuo/app";
    //获取验证码接口
    public static final String VERIFY_CODE = "/user/verifyCode";
    /**
     * 手机号注册
     */
    public static final String API_REGISTER = "/user/register";
    /**
     * 手机号登录
     */
    public static final String API_LOGIN = "/user/login";
    /**
     * 忘记密码
     */
    public static final String API_FORGOTPASSWORD = "/user/forgotPassword";
    /**
     * 我的基本信息
     */
    public static final String API_BASICINFO = "/user/basicInfo";
    /**
     * 商品收藏列表
     */
    public static final String API_FAVGOOD = "/user/favGood";
    /**
     * 店铺收藏列表
     */
    public static final String API_FAVSTORE = "/user/favStore";

    /**
     * 我的订单
     */
    public static final String API_ORDER = "/user/order";

    /**
     * 我的地址
     */
    public static final String API_ADDRESS = "/user/address";
    /**
     * 商家入住
     */
    public static final String API_OPENSHOP = "/userInfo/openShop";

    /**
     * 获取商品分类
     */
    public static final String API_GETGOODSTYPE = "/userInfo/getGoodsType";
    /**
     * 获取商圈分类
     */
    public static final String API_GETCIRCLE = "/userInfo/getCircle";

    /**
     * 获取商圈区域分类
     */
    public static final String API_GETCIRCLEAREA = "/userInfo/getCircleArea";
    /**
     * 商铺管理
     */
    public static final String API_STOREINFO = "/storeInfo";

    /**
     * 商品发布
     */
    public static final String API_PUBLISHGOOD = "/goods/publishGood";


    /**
     * 获取相关APIURL
     *
     * @param url 接口的后缀
     * @return 完整的apiurl
     */
    public static String getApiUrl(String url) {
        return ApiHost + ApiPath + url;
    }
}
