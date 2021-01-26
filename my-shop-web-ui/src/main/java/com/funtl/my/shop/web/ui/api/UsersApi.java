package com.funtl.my.shop.web.ui.api;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.utils.HttpClientUtils;
import com.funtl.my.shop.commons.utils.MapperUtils;
import com.funtl.my.shop.web.ui.dto.TbUser;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 会员管理接口
 */
public class UsersApi {

    /**
     * 登录
     * @param tbUser
     */
    public static TbUser login(TbUser tbUser) throws Exception {
        List<BasicNameValuePair> params=new ArrayList<>();
        params.add(new BasicNameValuePair("username",tbUser.getUsername()));
        params.add(new BasicNameValuePair("password",tbUser.getPassword()));

        //集合转数组
        String json = HttpClientUtils.doPost(API.API_USERS_LOGIN, params.toArray(new BasicNameValuePair[params.size()]));
        //将json中指定的数据转化为javabean
        TbUser user= MapperUtils.json2pojoByTree(json,"data",TbUser.class);

        return user;
    }

    /**
     * 注册
     * @param tbUser
     */
    public static TbUser register(TbUser tbUser) throws Exception {
        List<BasicNameValuePair> params=new ArrayList<>();
        params.add(new BasicNameValuePair("username",tbUser.getUsername()));
        params.add(new BasicNameValuePair("password",tbUser.getPassword()));
        params.add(new BasicNameValuePair("email",tbUser.getEmail()));
        params.add(new BasicNameValuePair("phone",tbUser.getPhone()));

        //集合转数组
        String json = HttpClientUtils.doPost(API.API_USERS_REGISTER, params.toArray(new BasicNameValuePair[params.size()]));

//        if(!=null) {
//            //将json中指定的数据转化为javabean
//            TbUser user = MapperUtils.json2pojoByTree(json, "data", TbUser.class);
//            return user;
//        }
//        else {
//            return null;
//        }
        TbUser user=MapperUtils.json2pojoByTree(json, "data", TbUser.class);
        return user;

    }
}
