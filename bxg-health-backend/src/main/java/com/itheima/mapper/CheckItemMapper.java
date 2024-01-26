package com.itheima.mapper;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckItem;
import com.itheima.pojo.FindPage;
import org.apache.ibatis.annotations.Mapper;

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
}
