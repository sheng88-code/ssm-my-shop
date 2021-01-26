package com.funtil.my.shop.web.admin.web.controller;

import com.funtil.my.shop.domain.TbContent;
import com.funtil.my.shop.domain.TbUser;
import com.funtil.my.shop.web.admin.abstracts.AbstractBaseController;
import com.funtil.my.shop.web.admin.service.TbContentService;
import com.funtil.my.shop.web.admin.service.TbUserService;
import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.dto.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 内容管理
 */

@Controller
@RequestMapping(value = "content")
public class ContentController extends AbstractBaseController<TbContent, TbContentService> {



    @ModelAttribute//切面编程
    public TbContent getTbContent(Long id){
        TbContent tbContent=null;

        if(id!=null){
            tbContent=service.getById(id);
        }

        else {
            tbContent=new TbContent();
        }

        return tbContent;
    }

    /**
     * 跳转到内容的列表页
     * @return
     */
    @Override
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(){
        return "content_list";
    }

    /**
     * 跳转用户表单页
     * @return
     */
    @Override
    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String form(){
        return "content_form";
    }

    /**
     * 保护信息
     * @param tbContent
     * @param redirectAttributes
     * @return
     */
    @Override
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(TbContent tbContent, Model model, RedirectAttributes redirectAttributes){
        BaseResult baseResult=service.save(tbContent);
        //保存成功
        if(baseResult.getStatus()==BaseResult.STATUS_SUCCESS){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/content/list";
        }

        //保存失败
        else{
            model.addAttribute("baseResult",baseResult);
            return "content_form";
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
            baseResult=BaseResult.success("删除内容成功");
            String[] idArray=ids.split(",");
            service.deleteMutil(idArray);
        }
        else {
            baseResult=BaseResult.fail("删除内容失败");
        }
        return baseResult;
    }



    /**
     * 显示详情
     * @param tbContent
     * @return
     */
    @Override
    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String deatil(TbContent tbContent){
        return "user_detail";
    }
}
