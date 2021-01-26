package com.funtil.my.shop.domain;

import com.funtl.my.shop.commons.persistence.BaseTreeEntity;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;


/**
 * 分类管理
 */
@Data
public class TbContentCategory extends BaseTreeEntity {

    private Long parentId;
    @Length(min = 1,max = 20,message = "姓名的长度介于6-20位之间")
    private String name;

    private Integer status;
    @NotNull (message ="排序为空")
    private Integer sortOrder;

    private TbContentCategory parent;
    private Boolean isParent;



}
