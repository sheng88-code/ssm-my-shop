package com.funtl.my.shop.web.api.web.controller.v1;

import com.funtil.my.shop.domain.TbContent;
import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.web.api.service.TbContentService;
import com.funtl.my.shop.web.api.web.dto.TbContentDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//返回数据都是json
@RestController
@RequestMapping(value = "${api.path.v1}/contents/ppt/")
public class TbContentController {

    @Autowired
    private TbContentService tbContentService;

    @ModelAttribute
    public TbContent getTbContent(Long id){
        TbContent tbContent=null;

        if(id==null){
            tbContent=new TbContent();
        }
        return tbContent;
    }

    @RequestMapping(value = "{category_id}",method = RequestMethod.GET)
    public BaseResult findContentByCategoryId(@PathVariable(value = "category_id")Long categoryId){
        List<TbContentDTO> tbContentDTOS=null;
        List<TbContent> tbContents=tbContentService.selectByCategoryId(categoryId);

        if(tbContents!=null&&tbContents.size()>0){
            tbContentDTOS=new ArrayList<>();
            for(TbContent tbContent:tbContents){
                TbContentDTO dto=new TbContentDTO();
                BeanUtils.copyProperties(tbContent,dto);
                tbContentDTOS.add(dto);
            }
        }
        return BaseResult.success("成功",tbContentDTOS);
    }
}