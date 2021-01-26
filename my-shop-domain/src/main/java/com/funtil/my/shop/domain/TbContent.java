package com.funtil.my.shop.domain;

import com.funtl.my.shop.commons.persistence.BaseEntity;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 内容管理
 */
@Data
public class TbContent extends BaseEntity {


    @Length(min = 1,max = 20,message = "标题的长度介于6-20位之间")
    private String title;
    @Length(min = 1,max = 20,message = "子标题的长度介于6-20位之间")
    private String subTitle;
    @Length(min = 1,max = 20,message = "标题描述的长度介于6-20位之间")
    private String titleDesc;
    @Length(min = 1,max = 50,message = "链接的长度介于6-20位之间")
    private String url;

    private String pic;

    private String pic2;
    private String content;


    private TbContentCategory tbContentCategory;

}
