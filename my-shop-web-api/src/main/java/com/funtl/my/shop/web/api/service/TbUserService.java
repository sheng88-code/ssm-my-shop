package com.funtl.my.shop.web.api.service;

import com.funtil.my.shop.domain.TbUser;

public interface TbUserService {

    /**
     * 登录
     * @param tbUser
     * @return
     */
    TbUser login(TbUser tbUser);



    TbUser check(TbUser tbUser);

    void insert(TbUser tbUser);
}
