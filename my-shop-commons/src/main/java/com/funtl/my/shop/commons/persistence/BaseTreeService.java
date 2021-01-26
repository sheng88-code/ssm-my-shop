package com.funtl.my.shop.commons.persistence;

import com.funtl.my.shop.commons.dto.BaseResult;

import java.util.List;

/**
 * 通用的树形结构的接口
 * @param <T>
 */
public interface BaseTreeService<T extends BaseEntity> {
    /**
     * 查询用户表的全部信息
     * @return
     */
    public List<T> selectAll();

    /**
     * 新增
     * @param entity
     */
    BaseResult save(T entity);

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
     * 根据父级节点的id查询所有的子节点
     * @param pid
     * @return
     */
    List<T> selectByPid(Long pid);

    /**
     * 删除
     * @param ids
     */
    void deleteMutil(String[] ids);
}
