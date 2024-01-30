package com.itheima.service;

import com.itheima.common.entity.PageResult;
import com.itheima.pojo.FindPage;
import com.itheima.pojo.Setmeal;

public interface CheckSetmealService {
    /**
     * 添加检查套餐
     *
     * @param setmeal
     */
    void addCheckSetmeal(Setmeal setmeal);

    /**
     * 删除检查套餐
     *
     * @param id
     */
    void deleteCheckSetmeal(Long id);

    /**
     * 修改检查套餐
     *
     * @param setmeal
     */
    void editCheckSetmeal(Setmeal setmeal);

    /**
     * 分页查询套餐
     *
     * @param findPage
     * @return
     */
    PageResult pageQuery(FindPage findPage);
}