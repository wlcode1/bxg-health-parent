package com.itheima.mapper;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.FindPage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CheckGroupMapper {
    /**
     * 分页查询检查组
     *
     * @param findPage
     * @return
     */
    Page<CheckGroup> pageQuery(FindPage findPage);

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
     * 编辑检查组
     *
     * @param group
     */
    void upDateCheckGroup(CheckGroup group);

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