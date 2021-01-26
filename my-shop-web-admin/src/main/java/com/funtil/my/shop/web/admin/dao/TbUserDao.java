package com.funtil.my.shop.web.admin.dao;

import com.funtil.my.shop.domain.TbUser;
import com.funtl.my.shop.commons.persistence.BaseDao;
import org.springframework.stereotype.Repository;


@Repository
public interface TbUserDao extends BaseDao<TbUser> {
    /**
     * 根据邮箱查询用户信息
     * @param email
     * @return
     */
    TbUser getByEmail(String email);
}
