package com.itheima.service;

import com.itheima.common.entity.PageResult;
import com.itheima.pojo.FindPage;
import com.itheima.pojo.Setmeal;

import java.util.List;

public interface CheckSetmealService {
    /**
     * 添加检查套餐
     *
     * @param setmeal
     */
    void addCheckSetmeal(Setmeal setmeal, List<Long> groupIds);

    /**
     * 修改检查套餐
     *
     * @param setmeal
     */
    void editCheckSetmeal(Setmeal setmeal, List<Long> groupIds);

    /**
     * 分页查询套餐
     *
     * @param findPage
     * @return
     */
    PageResult pageQuery(FindPage findPage);

    /**
     * 根据套餐id查询套餐
     *
     * @param id
     * @return
     */
    Setmeal findById(Long id);
}