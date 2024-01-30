package com.itheima.mapper;

import com.itheima.pojo.GroupWithItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CheckGroupWithItemMapper {

    /**
     * 添加检查组和项先关联
     *
     * @param groupWithItemList
     */
    void insertCheckItems(List<GroupWithItem> groupWithItemList);

    /**
     * 根据groupid查询库里面是否有相对应的数据
     *
     * @param groupId
     * @return
     */
    List<GroupWithItem> selectByGroupId(Long groupId);

    /**
     * 根据传入的id删除关联数据
     *
     * @param groupId
     */
    void deleteByGroupItemId(Long groupId);

    /**
     * 根据传入的itemid来查询是否有关联数据
     *
     * @param itemId
     * @return
     */
    List<GroupWithItem> selectByItemId(Long itemId);
}