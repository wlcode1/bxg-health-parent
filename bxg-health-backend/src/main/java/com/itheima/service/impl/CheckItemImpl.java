package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.common.entity.PageResult;
import com.itheima.mapper.CheckItemMapper;
import com.itheima.pojo.CheckItem;
import com.itheima.pojo.FindPage;
import com.itheima.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckItemImpl implements CheckItemService {
    @Autowired
    CheckItemMapper checkItemMapper;

    /**
     * 添加检查项
     *
     * @param item
     */
    @Override
    public void addCheckItem(CheckItem item) {
        checkItemMapper.addCheckItem(item);
    }

    /**
     * 删除检查项
     *
     * @param id
     */
    @Override
    public void deleteCheckItem(Long id) {
        checkItemMapper.deleteCheckItem(id);
    }

    /**
     * 编辑检查项
     *
     * @param item
     * @return
     */
    @Override
    public void editCheckItem(CheckItem item) {
        checkItemMapper.upDateCheckItem(item);
    }

    /**
     * 分页查询检查项
     *
     * @param findPage
     * @return
     */
    @Override
    public PageResult pageQuery(FindPage findPage) {
        PageHelper.startPage(findPage.getCurrentPage(), findPage.getPageSize());
        Page<CheckItem> page = checkItemMapper.pageQuery(findPage);
        return new PageResult(page.getTotal(), page.getResult());
    }
}