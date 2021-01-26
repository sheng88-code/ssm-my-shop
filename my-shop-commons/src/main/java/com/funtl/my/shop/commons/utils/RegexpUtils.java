package com.funtl.my.shop.commons.utils;

public class RegexpUtils {

    /**
     * 验证手机号码
     */
    public static final String PHONE="^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";

    /**
     * 验证邮箱地址
     */
    public static final String EMAIL="^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";

    /**
     * 验证手机号
     * @param phone
     * @return
     */
    public static boolean checkPhone(String phone){
        return phone.matches(PHONE);
    }

    /**
     * 验证邮箱地址
     * @param email
     * @return
     */
    public static boolean checkEmail(String email){
        return email.matches(EMAIL);
    }
}
