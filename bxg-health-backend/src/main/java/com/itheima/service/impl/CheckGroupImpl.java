package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.common.entity.PageResult;
import com.itheima.mapper.CheckGroupMapper;
import com.itheima.mapper.CheckGroupWithItemMapper;
import com.itheima.mapper.CheckSetmealWithGroupsMapper;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.FindPage;
import com.itheima.pojo.GroupWithItem;
import com.itheima.pojo.SetmealWithGroups;
import com.itheima.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CheckGroupImpl implements CheckGroupService {
    @Autowired
    CheckGroupMapper checkGroupMapper;

    @Autowired
    CheckGroupWithItemMapper checkGroupWithItemMapper;

    @Autowired
    CheckSetmealWithGroupsMapper checkSetmealWithGroupsMapper;

    /**
     * 添加检查组
     *
     * @param group
     */
    @Transactional
    @Override
    public void addCheckGroup(CheckGroup group) {
        //先插入检查组
        checkGroupMapper.addCheckGroup(group);
        //再从检查组里面获取item的数组
        List<Long> itemList = group.getCheckItems();
        //如果有值，说明可以继续插入
        if (itemList != null && itemList.size() > 0) {
            //取出相关联的检查组的id
            Integer groupId = group.getId();
            //传入添加
            addGroupItem(groupId, itemList);
        }
    }

    /**
     * 添加检查组和项先关联
     *
     * @param groupId
     * @param itemList
     */
    public void addGroupItem(Integer groupId, List<Long> itemList) {
        //新建一个数组留着保存检查组和项相关联的model用
        List<GroupWithItem> groupWithItemList = new ArrayList<>();
        //遍历传入的数组
        for (Long itemId : itemList) {
            //封装好检查组和项相关联的model再加入到数组中
            GroupWithItem item = GroupWithItem.builder().checkgroupId(groupId).checkitemId(itemId.intValue()).build();
            groupWithItemList.add(item);
        }
        //插入
        checkGroupWithItemMapper.insertCheckItems(groupWithItemList);
    }

    /**
     * 删除检查组
     *
     * @param id
     */
    @Transactional
    @Override
    public void deleteCheckGroup(Long id) {
        //如果要删除检查组，首先要查询下是否被套餐关联了，如果是被关联了，那么不允许删除
        List<SetmealWithGroups> setmealWithGroups = checkSetmealWithGroupsMapper.selectByGroupId(id);
        //如果数组里的元素个数等于0或者数组为null，表示可以删除
        if (setmealWithGroups.size() == 0 || setmealWithGroups == null) {
            deleteGroupWithItems(id);
            checkGroupMapper.deleteCheckGroup(id);
        } else {
            System.out.println("数据被关联，删除失败！");
        }
    }

    /**
     * 根据传入的id先查看是否有值，如果有值，再删除
     *
     * @param id
     */
    public void deleteGroupWithItems(Long id) {
        List<GroupWithItem> groupWithItemList = checkGroupWithItemMapper.selectByGroupId(id);
        if (groupWithItemList.size() > 0) {
            checkGroupWithItemMapper.deleteByGroupItemId(id);
        }
    }

    /**
     * 修改检查组
     *
     * @param group
     */
    @Transactional
    @Override
    public void editCheckGroup(CheckGroup group) {
        deleteGroupWithItems(group.getId().longValue());
        checkGroupMapper.upDateCheckGroup(group);
        addGroupItem(group.getId(), group.getCheckItems());
    }

    /**
     * 分页查询检查组
     *
     * @param findPage
     * @return
     */
    @Override
    public PageResult pageQuery(FindPage findPage) {
        PageHelper.startPage(findPage.getCurrentPage(), findPage.getPageSize());
        Page<CheckGroup> page = checkGroupMapper.pageQuery(findPage);
        return new PageResult(page.getTotal(), page.getResult());
    }
}