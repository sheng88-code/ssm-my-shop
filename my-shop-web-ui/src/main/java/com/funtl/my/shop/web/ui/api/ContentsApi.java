package com.funtl.my.shop.web.ui.api;

import com.funtl.my.shop.commons.utils.HttpClientUtils;
import com.funtl.my.shop.commons.utils.MapperUtils;
import com.funtl.my.shop.web.ui.dto.TbContent;

import java.util.List;

/**
 * 内容管理接口
 */
public class ContentsApi {

    /**
     * 轮转图ppt
     * @return
     */
    public static List<TbContent> ppt(){
        String result= HttpClientUtils.doGet(API.API_CONTENTS_PPT+"89");
        List<TbContent> tbContents= null;
        try {
            tbContents = MapperUtils.json2listByTree(result,"data", TbContent.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  tbContents;
    }


}
