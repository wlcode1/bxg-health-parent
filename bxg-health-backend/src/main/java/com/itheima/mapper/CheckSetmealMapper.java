package com.itheima.mapper;

import com.github.pagehelper.Page;
import com.itheima.pojo.FindPage;
import com.itheima.pojo.Setmeal;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CheckSetmealMapper {

    /**
     * 添加套餐
     *
     * @param setmeal
     */
    void addCheckSetmeal(Setmeal setmeal);

    /**
     * 编辑套餐
     *
     * @param setmeal
     */
    void upDateCheckSetmeal(Setmeal setmeal);

    /**
     * 分页查询套餐
     *
     * @param findPage
     * @return
     */
    Page<Setmeal> pageQuery(FindPage findPage);

    /**
     * 根据套餐id查询套餐
     *
     * @param id
     * @return
     */
    Setmeal findById(Long id);
}