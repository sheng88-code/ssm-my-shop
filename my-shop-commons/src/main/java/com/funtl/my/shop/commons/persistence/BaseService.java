package com.funtl.my.shop.commons.persistence;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.dto.PageInfo;

import java.util.List;

/**
 * 使用业务逻辑层的基类
 */
public interface BaseService<T extends BaseEntity> {
    /**
     * 查询全部
     * @return
     */
    public List<T> selectAll();

    /**
     * 保存信息
     * @param eneity
     * @return
     */
    BaseResult save(T eneity);

    /**
     * 删除信息
     * @param id
     */
    void delete(Long id);

    /**
     * 通过id查找信息
     * @param id
     * @return
     */
    T getById(Long id);

    /**
     * 编辑信息
     * @param eneity
     */
    void update(T eneity);

    /**
     * 批量删除
     * @param ids
     */
    void deleteMutil(String[] ids);

    /**
     * 分页查询
     * @param start
     * @param length
     * @return
     */
    PageInfo<T> page(int start, int length, int draw, T eneity);

    /**
     * 查询总比数
     * @return
     */
    int count(T eneity);
}
