package com.funtil.my.shop.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.funtl.my.shop.commons.persistence.BaseEntity;

import com.funtl.my.shop.commons.utils.RegexpUtils;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

@Data//封装bean（代表set和get，toString）

public class TbUser extends BaseEntity {
    @Length(min = 6,max = 20,message = "姓名的长度介于6-20位之间")
    private String username;
    @JsonIgnore
    @Length(min = 6,max = 20,message = "密码的长度介于6-20位之间")
    private String password;
    @Pattern(regexp= RegexpUtils.PHONE,message = "手机号的格式不正确")
    private String phone;
    @Pattern(regexp= RegexpUtils.EMAIL,message = "邮箱的格式不正确")
    private String email;
}
