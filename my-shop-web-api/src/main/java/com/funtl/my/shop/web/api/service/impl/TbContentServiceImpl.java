package com.funtl.my.shop.web.api.service.impl;

import com.funtil.my.shop.domain.TbContent;
import com.funtil.my.shop.domain.TbContentCategory;
import com.funtl.my.shop.web.api.dao.TbContentDao;
import com.funtl.my.shop.web.api.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class TbContentServiceImpl implements TbContentService {

    @Autowired
    private TbContentDao tbContentDao;

    @Override
    public List<TbContent> selectByCategoryId(Long categoryId) {
        TbContentCategory tbContentCategory=new TbContentCategory();
        tbContentCategory.setId(categoryId);
        TbContent tbContent=new TbContent();

        tbContent.setTbContentCategory(tbContentCategory);

        return tbContentDao.selectByCategoryId(tbContent);
    }
}
