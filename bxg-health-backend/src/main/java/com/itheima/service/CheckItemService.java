package com.itheima.service;

import com.itheima.common.entity.PageResult;
import com.itheima.pojo.CheckItem;
import com.itheima.pojo.FindPage;

public interface CheckItemService {
    /**
     * 添加检查项
     *
     * @param item
     */
    void addCheckItem(CheckItem item);

    /**
     * 删除检查项
     *
     * @param id
     */
    void deleteCheckItem(Long id);

    /**
     * 编辑检查项
     *
     * @param item
     * @return
     */
    void editCheckItem(CheckItem item);

    /**
     * 分页查询检查项
     *
     * @param findPage
     * @return
     */
    PageResult pageQuery(FindPage findPage);
}