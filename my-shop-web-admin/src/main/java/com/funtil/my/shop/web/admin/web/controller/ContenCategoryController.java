package com.funtil.my.shop.web.admin.web.controller;

import com.funtil.my.shop.domain.TbContentCategory;
import com.funtil.my.shop.web.admin.abstracts.AbstractBaseTreeController;
import com.funtil.my.shop.web.admin.service.TbContentCategoryService;
import com.funtl.my.shop.commons.dto.BaseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * 内容分类管理
 */
@Controller
@RequestMapping(value = "content/category")
public class ContenCategoryController extends AbstractBaseTreeController<TbContentCategory,TbContentCategoryService> {


    @ModelAttribute
    public TbContentCategory getTbContentCategory(Long id){
        TbContentCategory tbContentCategory=null;

        //id不为空
        if(id!=null){
            tbContentCategory=service.getById(id);
        }

        else {
            tbContentCategory=new TbContentCategory();
        }

        return tbContentCategory;
    }


    @Override
    @RequestMapping(value = "list")
    public String list(Model model){
        List<TbContentCategory> targetList=new ArrayList<>();
        List<TbContentCategory> sourceList=service.selectAll();
        //排序
        sortList(sourceList,targetList,0L);

        model.addAttribute("tbContentCategories",targetList);
        return "content_category_list";
    }

    @Override
    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String form(TbContentCategory tbContentCategory){//编辑时会覆盖掉ModelAttributede的tbContentCategory

        return "content_category_form";
    }





    /**
     * 保存
     * @param tbContentCategory
     * @param model
     * @param redirectAttributes
     * @return
     */
    @Override
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(TbContentCategory tbContentCategory,Model model, RedirectAttributes redirectAttributes){

        BaseResult baseResult=service.save(tbContentCategory);
        //保存成功
        if(baseResult.getStatus()==BaseResult.STATUS_SUCCESS){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/content/category/list";
        }
        //保存失败
        else{
            model.addAttribute("baseResult",baseResult);
            return "content_category_form";
        }
    }


    /**
     * 树形结构
     * @return
     */
    @Override
    @ResponseBody
    @RequestMapping(value = "tree/data",method = RequestMethod.POST)
    public List<TbContentCategory> treeData(Long id){
        if (id==null){
            id=0L;
        }

        return service.selectByPid(id);
    }

    /**
     * 删除
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


}
