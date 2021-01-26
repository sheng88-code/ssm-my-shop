package com.funtil.my.shop.web.admin.service.test;

import com.funtil.my.shop.domain.TbUser;
import com.funtil.my.shop.web.admin.service.TbUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml","classpath:spring-context-druid.xml","classpath:spring-context-mybatis.xml"})
public class TbUserServiceTest {
    @Autowired
    private TbUserService tbUserService;

    @Test
    public void testSelectAll(){
        List<TbUser> tbUsers=tbUserService.selectAll();
        for(TbUser tbUser:tbUsers){
            System.out.println(tbUser.getUsername());
        }
    }

    @Test
    public void testInsert(){
        TbUser tbUser=new TbUser();
        tbUser.setUsername("hs");
        tbUser.setPhone("158523458");
        tbUser.setEmail("1159");
        tbUser.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));//加密密码md5格式
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());

        tbUserService.save(tbUser);
    }

    @Test
    public void testGetById(){
        TbUser tbUser=tbUserService.getById(36L);
        System.out.println(tbUser.getUsername());
    }

    @Test
    public void testDelete(){

        tbUserService.delete(39L);
    }

    @Test
    public void testUpdate(){
        TbUser tbUser=tbUserService.getById(36L);
        tbUser.setUsername("hhh");
        tbUserService.update(tbUser);
    }


    @Test
    public void testMD5(){
        System.out.println(DigestUtils.md5DigestAsHex("123456".getBytes()));
    }


}
