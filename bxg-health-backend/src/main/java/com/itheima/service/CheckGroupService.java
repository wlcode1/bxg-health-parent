package com.itheima.service;

import com.itheima.common.entity.PageResult;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.FindPage;

import java.util.List;

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
     * @param checkItemIds
     */
    void addCheckGroup(CheckGroup group, List<Long> checkItemIds);

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
     * @param checkitemIds
     */
    void editCheckGroup(CheckGroup group, List<Long> checkitemIds);

    /**
     * 根据套餐id查询关联的检查组id
     *
     * @param setmealId
     * @return
     */
    List<Long> findCheckGroupIdsBySetmealId(Long setmealId);

    /**
     * 根据id查询检查组
     *
     * @param id
     * @return
     */
    CheckGroup findById(Long id);

    /**
     * 查询所有检查组
     *
     * @return
     */
    List<CheckGroup> findAll();
}