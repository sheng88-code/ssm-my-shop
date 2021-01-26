package com.funtil.my.shop.web.admin.web.controller;


import com.funtil.my.shop.domain.TbUser;
import com.funtil.my.shop.web.admin.service.TbUserService;
import com.funtl.my.shop.commons.ConstantUtils;
import com.funtl.my.shop.commons.CookieUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController {


    @Autowired
    private TbUserService tbUserService;
    /**
     * 跳转登录页面
     * @return
     */
    @RequestMapping(value = {"","login"},method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    /**
     * 登录逻辑@RequestParam(required = true)必须带参数
     * @param email
     * @param password
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(@RequestParam(required = true) String email, @RequestParam(required = true)String password, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Model model) throws ServletException, IOException {

        TbUser tbUser=tbUserService.login(email, password);

        //登录失败
        if(tbUser==null){
            model.addAttribute("message","用户名或密码错误，请重新输入");
            return login();
        }

        //登录成功
        else {
            //将登录信息放入会话
            httpServletRequest.getSession().setAttribute(ConstantUtils.SESSION_USER, tbUser);
            return "redirect:/main";
        }

    }

    /**
     * 注销
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
        //CookieUtil.deleteCookie(httpServletRequest, httpServletResponse,COOKIE_NAME_USER_INFO);
        httpServletRequest.getSession().invalidate();
        return login();
    }
}
