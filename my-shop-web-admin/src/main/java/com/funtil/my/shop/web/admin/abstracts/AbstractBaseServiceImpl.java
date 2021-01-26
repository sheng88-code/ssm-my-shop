package com.funtil.my.shop.web.admin.abstracts;

import com.funtl.my.shop.commons.dto.PageInfo;
import com.funtl.my.shop.commons.persistence.BaseDao;
import com.funtl.my.shop.commons.persistence.BaseEntity;
import com.funtl.my.shop.commons.persistence.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractBaseServiceImpl<T extends BaseEntity,D extends BaseDao<T>> implements BaseService<T> {

    @Autowired
    protected D dao;

    /**
     * 查询全部数据
     * @return
     */
    @Override
    public List<T> selectAll() {
        return dao.selectAll();
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public T getById(Long id) {
        return dao.getById(id);
    }

    @Override
    public void update(T eneity) {
        dao.update(eneity);
    }

    @Override
    public void deleteMutil(String[] ids) {
        dao.deleteMutil(ids);
    }

    @Override
    public int count(T eneity) {
        return dao.count(eneity);
    }

    /**
     * 分页查询
     * @param start
     * @param length
     * @param draw
     * @param eneity
     * @return
     */
    @Override
    public PageInfo<T> page(int start, int length, int draw, T eneity) {
        int count=count(eneity);

        Map<String,Object> params=new HashMap<>();
        params.put("start",start);
        params.put("length",length);
        params.put("pageParams",eneity);

        PageInfo<T> pageInfo=new PageInfo<>();
        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(dao.page(params));

        return pageInfo;
    }
}
