package com.funtil.my.shop.web.admin.service.impl;


import com.funtil.my.shop.domain.TbContentCategory;
import com.funtil.my.shop.web.admin.abstracts.AbstractBaseTreeServiceImpl;
import com.funtil.my.shop.web.admin.dao.TbContentCategoryDao;
import com.funtil.my.shop.web.admin.service.TbContentCategoryService;
import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.validator.BeanValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(readOnly = true)
public class TbContentCategoryServiceImpl extends AbstractBaseTreeServiceImpl<TbContentCategory,TbContentCategoryDao> implements TbContentCategoryService{
    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbContentCategory eneity) {
        String validator= BeanValidator.validator(eneity);

        if(validator!=null){
            return BaseResult.fail(validator);
        }
        else {
            TbContentCategory parent= (TbContentCategory) eneity.getParent();

            //如果没有父级节点，则默认设置为根目录
            if (parent==null||parent.getId()==null){
                //0L代表根目录
                parent.setId(0L);
                //根目录一定是父目录
                eneity.setIsParent(true);
            }

            eneity.setUpdated(new Date());
            //新增用户
            if(eneity.getId()==null) {
                //新密码需要加密处理
                eneity.setCreated(new Date());
                eneity.setStatus(1);

                if(eneity.getIsParent()==null){
                    eneity.setIsParent(false);
                }

                //判断当前新增的节点有没有父级节点
                if (parent.getId()!=0L){
                    TbContentCategory currentCategoryParent = getById(parent.getId());
                    if (currentCategoryParent!=null){
                        //为父级节点设置isParent为true
                        currentCategoryParent.setIsParent(true);
                        update(currentCategoryParent);
                    }
                }
                dao.insert(eneity);
            }

            //编辑用户
            else {
                 update(eneity);
            }
            return BaseResult.success("保存信息成功");
        }
    }


}
