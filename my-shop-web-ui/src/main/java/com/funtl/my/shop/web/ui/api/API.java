package com.funtl.my.shop.web.ui.api;

public class API {
    //主机地址
    public static final String HOST="http://localhost:8081/api/v1";

    //内容查询接口
    public static final String API_CONTENTS_PPT=HOST+"/contents/ppt/";

    //会员管理接口 - 登录
    public static final String API_USERS_LOGIN=HOST+"/users/login";

    //会员注册接口 - 注册
    public static final String API_USERS_REGISTER=HOST+"/users/register";
}
