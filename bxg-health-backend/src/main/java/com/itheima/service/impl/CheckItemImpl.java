package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.common.entity.PageResult;
import com.itheima.mapper.CheckGroupWithItemMapper;
import com.itheima.mapper.CheckItemMapper;
import com.itheima.pojo.CheckItem;
import com.itheima.pojo.FindPage;
import com.itheima.pojo.GroupWithItem;
import com.itheima.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckItemImpl implements CheckItemService {
    @Autowired
    CheckItemMapper checkItemMapper;

    @Autowired
    CheckGroupWithItemMapper checkGroupWithItemMapper;

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
        //查看是否被检查组关联，如果被检查组关联不能删除
        List<GroupWithItem> groupWithItemList = checkGroupWithItemMapper.selectByItemId(id);
        //如果数组为空或者数组中的元素为0那么就可以删除
        if (groupWithItemList == null || groupWithItemList.size() == 0) {
            checkItemMapper.deleteCheckItem(id);
        } else {
            System.out.println("检查项被检查组关联，不能删除！");
        }
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

    @Override
    public List<CheckItem> findAll() {
        List<CheckItem> checkItemList = checkItemMapper.findAll();
        return checkItemList;
    }

    /**
     * 根据检查组id查询相关联的检查项id
     *
     * @param checkgroupId
     * @return
     */
    @Override
    public List<Long> findCheckItemIdsByCheckGroupId(Long checkgroupId) {
        return checkItemMapper.findCheckItemIdsByCheckGroupId(checkgroupId);
    }

    /**
     * 根据id查询检查项
     *
     * @param id
     * @return
     */
    @Override
    public CheckItem findById(Long id) {
        return checkItemMapper.findById(id);
    }
}