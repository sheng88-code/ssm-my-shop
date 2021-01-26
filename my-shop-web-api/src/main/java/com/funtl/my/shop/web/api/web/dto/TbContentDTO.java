package com.funtl.my.shop.web.api.web.dto;

import lombok.Data;

/**
 * 内容数据传输对象
 */
@Data
public class TbContentDTO {
    private Long id;
    private String title;
    private String subTitle;
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;
}
