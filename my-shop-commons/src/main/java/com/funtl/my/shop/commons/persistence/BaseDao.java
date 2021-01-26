package com.funtl.my.shop.commons.persistence;

import java.util.List;
import java.util.Map;

/**
 * 使用数据服务层的基类
 */
public interface BaseDao <T extends BaseEntity>{
    /**
     * 查询用户表的全部信息
     * @return
     */
    public List<T> selectAll();

    /**
     * 新增
     * @param entity
     */
    void insert(T entity);

    /**
     * 删除
     * @param id
     */
    void delete(Long id);

    /**
     * 感觉id查询用户的信息
     * @param id
     * @return
     */
    T getById(Long id);

    /**
     * 更新tbUser
     * @param entity
     */
    void update(T entity);

    /**
     * 批量删除
     * @param ids
     */
    void deleteMutil(String[] ids);

    /**
     * 分页查询
     * @param params 需要两个参数，start/记录开始位置 length/每页记录数
     * @return
     */
    List<T> page(Map<String,Object> params);

    /**
     * 查询总笔数
     * @return
     */
    int count(T entity);
}
