package com.itheima.mapper;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.FindPage;
import org.apache.ibatis.annotations.Mapper;

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
}