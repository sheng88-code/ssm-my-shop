package com.funtl.my.shop.web.ui.controller;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.web.ui.api.UsersApi;
import com.funtl.my.shop.web.ui.constant.SystemConstants;
import com.funtl.my.shop.web.ui.dto.TbUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 注册控制器
 */
@Controller
public class RegisterController {

    /**
     * 跳转注册页
     * @return
     */
    @RequestMapping(value="register",method = RequestMethod.GET)
    public String register(){
        return "register";
    }

    /**
     * 登录
     */
    @RequestMapping(value = "register",method = RequestMethod.POST)
    public String register(TbUser tbUser, Model model, HttpServletRequest request) throws Exception {

        TbUser user= UsersApi.register(tbUser);

        //注册失败
        if(user==null){
            model.addAttribute("baseResult",BaseResult.fail("用户名,邮箱或手机号已注册，请重新输入"));
            return "register";
        }

        //注册成功
        else {
            return "redirect:/login";
        }
    }
}
