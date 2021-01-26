package com.funtil.my.shop.web.admin.service;

import com.funtil.my.shop.domain.TbUser;
import com.funtl.my.shop.commons.persistence.BaseService;

public interface TbUserService extends BaseService<TbUser> {
    /**
     * 用户登录
     * @param email
     * @param password
     * @return
     */
    TbUser login(String email,String password);
}
