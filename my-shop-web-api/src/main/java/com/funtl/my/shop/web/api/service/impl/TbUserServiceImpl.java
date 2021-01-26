package com.funtl.my.shop.web.api.service.impl;

import com.funtil.my.shop.domain.TbUser;
import com.funtl.my.shop.web.api.dao.TbUserDao;
import com.funtl.my.shop.web.api.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;

@Service
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    private TbUserDao tbUserDao;

    @Override
    public TbUser login(TbUser tbUser) {
        TbUser user=tbUserDao.login(tbUser);

        if(user!=null){
            String password= DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes());
            if(password.equals(user.getPassword())){
                return user;
            }
        }

        return null;
    }

    @Override
    public TbUser check(TbUser tbUser) {
        int count=tbUserDao.check(tbUser);
        if (count!=0){
            return null;
        }
        else {
            return tbUser;
        }
    }

    @Override
    public void insert(TbUser tbUser) {
        //明文加密处理
        tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
        tbUser.setUpdated(new Date());
        tbUser.setCreated(new Date());
        tbUserDao.insert(tbUser);
    }


}
