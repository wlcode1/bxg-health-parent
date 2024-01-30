package com.itheima.service;

import com.itheima.common.entity.PageResult;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.FindPage;

public interface CheckGroupService {
    /**
     * 分页查询检查组
     *
     * @param findPage
     * @return
     */
    PageResult pageQuery(FindPage findPage);

    /**
     * 添加检查组
     *
     * @param group
     */
    void addCheckGroup(CheckGroup group);

    /**
     * 删除检查组
     *
     * @param id
     */
    void deleteCheckGroup(Long id);

    /**
     * 修改检查组
     *
     * @param group
     */
    void editCheckGroup(CheckGroup group);
}