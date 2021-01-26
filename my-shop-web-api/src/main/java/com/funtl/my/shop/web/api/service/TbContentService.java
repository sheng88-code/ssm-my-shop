package com.funtl.my.shop.web.api.service;

import com.funtil.my.shop.domain.TbContent;

import java.util.List;

public interface TbContentService {
    /**
     * 根据类别ID查询内容列表
     * @param category
     * @return
     */
    List<TbContent> selectByCategoryId(Long category);
}
