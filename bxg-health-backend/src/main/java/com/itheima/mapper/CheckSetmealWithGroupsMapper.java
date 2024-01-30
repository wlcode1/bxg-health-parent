package com.itheima.mapper;

import com.itheima.pojo.SetmealWithGroups;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CheckSetmealWithGroupsMapper {
    /**
     * 批量插入检查组与套餐的关系
     *
     * @param groups
     */
    void insertCheckGroups(List<SetmealWithGroups> groups);

    /**
     * 删除与传入的套餐id相同的套餐组的信息
     *
     * @param setmealId
     */
    void deleteBySetmealGroupId(Long setmealId);

    /**
     * 根据 setmealId来查是否有数据
     *
     * @param id
     * @return
     */
    List<SetmealWithGroups> selectBysetmealId(Long id);

    /**
     * 根据groupId来查是否有数据
     *
     * @param groupId
     * @return
     */
    List<SetmealWithGroups> selectByGroupId(Long groupId);
}