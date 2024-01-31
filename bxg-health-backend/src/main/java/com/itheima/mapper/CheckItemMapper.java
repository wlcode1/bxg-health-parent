package com.itheima.mapper;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckItem;
import com.itheima.pojo.FindPage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CheckItemMapper {

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
    void upDateCheckItem(CheckItem item);

    /**
     * 分页查询检查项
     *
     * @param findPage
     * @return
     */
    Page<CheckItem> pageQuery(FindPage findPage);

    /**
     * 查询所有检查项
     *
     * @return
     */
    List<CheckItem> findAll();

    /**
     * 根据检查组id查询相关联的检查项id
     *
     * @param checkgroupId
     * @return
     */
    List<Long> findCheckItemIdsByCheckGroupId(Long checkgroupId);

    /**
     * 根据id查询检查项
     *
     * @param id
     * @return
     */
    CheckItem findById(Long id);
}
