package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.common.entity.PageResult;
import com.itheima.mapper.CheckSetmealMapper;
import com.itheima.mapper.CheckSetmealWithGroupsMapper;
import com.itheima.pojo.FindPage;
import com.itheima.pojo.Setmeal;
import com.itheima.pojo.SetmealWithGroups;
import com.itheima.service.CheckSetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CheckSetmealImpl implements CheckSetmealService {
    @Autowired
    CheckSetmealMapper checkSetmealMapper;

    @Autowired
    CheckSetmealWithGroupsMapper checkSetmealWithGroupsMapper;

    /**
     * 添加检查套餐
     *
     * @param setmeal
     */
    @Transactional
    @Override
    public void addCheckSetmeal(Setmeal setmeal) {
        //首先插入套餐
        checkSetmealMapper.addCheckSetmeal(setmeal);
        //取出传入的套餐中包含的检查组的id
        List<Long> setmealGroupIds = setmeal.getCheckGroups();
        if (setmealGroupIds != null && setmealGroupIds.size() > 0) {
            //然后拿到套餐的id
            Integer setmealId = setmeal.getId();
            //传入到添加套餐组的方法中
            addSetmealGroups(setmealId, setmealGroupIds);
        }
    }

    /**
     * 添加套餐组
     *
     * @param setmealId
     * @param setmealGroupIds
     */
    public void addSetmealGroups(Integer setmealId, List<Long> setmealGroupIds) {
        //新建一个list，其中将要存入的是套餐与检查组的id的model
        List<SetmealWithGroups> setmealGroupsList = new ArrayList<>();
        //遍历检查组的id的数组
        for (Long sgId : setmealGroupIds) {
            //新建套餐和检查组id的model
            SetmealWithGroups setmealGroups = SetmealWithGroups.builder().setmealId(setmealId).checkgroupId(sgId.intValue()).build();
            //添加到上面的新的数组中
            System.out.println(setmealGroups);
            setmealGroupsList.add(setmealGroups);
        }
        //插入
        checkSetmealWithGroupsMapper.insertCheckGroups(setmealGroupsList);
    }

    /**
     * 删除检查套餐
     *
     * @param id
     */
    @Override
    @Transactional
    public void deleteCheckSetmeal(Long id) {
        //先删除套餐组表中的相关数据
        List<SetmealWithGroups> setmealGroupsList = checkSetmealWithGroupsMapper.selectBysetmealId(id);
        if (setmealGroupsList.size() > 0) {
            for (int i = 0; i < setmealGroupsList.size(); i++) {
                checkSetmealWithGroupsMapper.deleteBySetmealGroupId(id);
            }
        }
        //然后再删除套餐的数据
        checkSetmealMapper.deleteCheckSetmeal(id);
    }

    /**
     * 删除套餐组中的相关数据
     *
     * @param id
     */
    public void deleteSetmealGroups(Long id) {
        List<SetmealWithGroups> setmealGroupsList = checkSetmealWithGroupsMapper.selectBysetmealId(id);
        if (setmealGroupsList.size() > 0) {
            for (int i = 0; i < setmealGroupsList.size(); i++) {
                checkSetmealWithGroupsMapper.deleteBySetmealGroupId(id);
            }
        }
    }

    /**
     * 修改检查套餐
     *
     * @param setmeal
     */
    @Transactional
    @Override
    public void editCheckSetmeal(Setmeal setmeal) {
        //先删除套餐组表中的相关数据
        deleteSetmealGroups(setmeal.getId().longValue());
        //再更新套餐的数据
        checkSetmealMapper.upDateCheckSetmeal(setmeal);
        //最后再把套餐组数据添加进去
        if (setmeal.getCheckGroups() != null && setmeal.getCheckGroups().size() > 0) {
            addSetmealGroups(setmeal.getId(), setmeal.getCheckGroups());
        }
    }

    /**
     * 分页查询套餐
     *
     * @param findPage
     * @return
     */
    @Override
    public PageResult pageQuery(FindPage findPage) {
        PageHelper.startPage(findPage.getCurrentPage(), findPage.getPageSize());
        Page<Setmeal> page = checkSetmealMapper.pageQuery(findPage);
        return new PageResult(page.getTotal(), page.getResult());
    }
}