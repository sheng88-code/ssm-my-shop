package com.funtl.my.shop.web.api.dao;

import com.funtil.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

/**
 * 会员管理
 */
@Repository
public interface TbUserDao {

    /**
     * 登录
     * @param tbUser
     * @return
     */
    TbUser login(TbUser tbUser);

    /**
     * 验证是否重复，如果重复返回null
     * @param tbUser
     * @return
     */
    int check(TbUser tbUser);

    /**
     * 插入
     * @param tbUser
     */
    void insert(TbUser tbUser);
}
