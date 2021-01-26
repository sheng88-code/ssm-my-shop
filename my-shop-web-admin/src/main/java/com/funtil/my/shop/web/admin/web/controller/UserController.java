package com.funtil.my.shop.web.admin.web.controller;

import com.funtil.my.shop.domain.TbUser;
import com.funtil.my.shop.web.admin.abstracts.AbstractBaseController;
import com.funtil.my.shop.web.admin.service.TbUserService;
import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.dto.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;




/**
 * 用户管理
 */

@Controller
@RequestMapping(value = "user")//所有请求都有user开头user/
public class UserController extends AbstractBaseController<TbUser,TbUserService> {


    @ModelAttribute//切面编程
    public TbUser getTbUser(Long id){
        TbUser tbUser=null;

        if(id!=null){
            tbUser=service.getById(id);
        }

        else {
            tbUser=new TbUser();
        }

        return tbUser;
    }

    /**
     * 跳转到用户的列表页
     * @return
     */
    @Override
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(){
        return "user_list";
    }

    /**
     * 跳转用户表单页
     * @return
     */
    @Override
    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String form(){
        return "user_form";
    }

    /**
     * 保护用户信息
     * @param tbUser
     * @param redirectAttributes
     * @return
     */
    @Override
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(TbUser tbUser,Model model, RedirectAttributes redirectAttributes){
        BaseResult baseResult=service.save(tbUser);

        //保存成功
        if(baseResult.getStatus()==BaseResult.STATUS_SUCCESS){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/user/list";
        }

        //保存失败
        else{
            model.addAttribute("baseResult",baseResult);
            return "user_form";
        }
    }

    /**
     * 删除用户信息
     * @param ids
     * @return
     */
    @Override
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    @ResponseBody
    public BaseResult delete(String ids){
        BaseResult baseResult=null;
        if(StringUtils.isNoneBlank(ids)){
            baseResult=BaseResult.success("删除用户成功");
            String[] idArray=ids.split(",");
            service.deleteMutil(idArray);
        }
        else {
            baseResult=BaseResult.fail("删除用户失败");
        }
        return baseResult;
    }


    /**
     * 显示用户详情
     * @param tbUser
     * @return
     */
    @Override
    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String deatil(TbUser tbUser){
        return "user_detail";
    }
}
