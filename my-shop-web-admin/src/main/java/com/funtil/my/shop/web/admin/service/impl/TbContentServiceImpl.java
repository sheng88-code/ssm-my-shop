package com.funtil.my.shop.web.admin.service.impl;

import com.funtil.my.shop.domain.TbContent;
import com.funtil.my.shop.web.admin.abstracts.AbstractBaseServiceImpl;
import com.funtil.my.shop.web.admin.dao.TbContentDao;
import com.funtil.my.shop.web.admin.service.TbContentService;
import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.validator.BeanValidator;
import org.springframework.stereotype.Service;
import java.util.Date;


@Service
public class TbContentServiceImpl extends AbstractBaseServiceImpl<TbContent,TbContentDao> implements TbContentService {
    @Override
    public BaseResult save(TbContent tbContent) {
        String valiator= BeanValidator.validator(tbContent);

        if (valiator!=null){
            return BaseResult.fail(valiator);
        }
        else {
            tbContent.setUpdated(new Date());
            //新增用户
            if(tbContent.getId()==null) {

                tbContent.setCreated(new Date());
                dao.insert(tbContent);
            }
            //编辑用户
            else {
                update(tbContent);
            }
            return BaseResult.success("保存信息成功");
        }
    }
}
