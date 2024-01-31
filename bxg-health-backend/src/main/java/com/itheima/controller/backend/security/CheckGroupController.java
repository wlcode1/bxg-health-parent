package com.itheima.controller.backend.security;

import com.itheima.common.constant.MessageConstant;
import com.itheima.common.entity.PageResult;
import com.itheima.common.entity.Result;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.FindPage;
import com.itheima.service.CheckGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/checkgroup")
public class CheckGroupController {
    @Autowired
    CheckGroupService checkGroupService;

    /**
     * 添加检查组
     *
     * @param group
     * @param checkitemIds
     * @return
     */
    @PostMapping("/add")
    public Result addCheckGroup(@RequestBody CheckGroup group, @RequestParam List<Long> checkitemIds) {
        log.info("添加检查组：{}, {}", group, checkitemIds);
        checkGroupService.addCheckGroup(group, checkitemIds);
        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }

    /**
     * 删除检查组
     *
     * @param id
     * @return
     */
    @GetMapping("/deleteById")
    public Result deleteCheckGroup(@RequestParam Long id) {
        log.info("删除检查组：{}", id);
        checkGroupService.deleteCheckGroup(id);
        return new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
    }

    /**
     * 修改检查组
     *
     * @param group
     * @return
     */
    @PostMapping("/edit")
    public Result editCheckSetmeal(@RequestBody CheckGroup group, @RequestParam List<Long> checkitemIds) {
        log.info("修改检查组：{},{}", group, checkitemIds);
        checkGroupService.editCheckGroup(group, checkitemIds);
        return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    }

    /**
     * 分页查询检查组
     *
     * @param page
     * @return
     */
    @PostMapping("/findPage")
    public PageResult page(@RequestBody FindPage page) {
        log.info("分页查询检查组：{}", page);
        return checkGroupService.pageQuery(page);
    }

    /**
     * 根据套餐id查询关联的检查组id
     *
     * @param setmealId
     * @return
     */
    @GetMapping("/findCheckGroupIdsBySetmealId")
    public Result findCheckGroupIdsBySetmealId(@RequestParam Long setmealId) {
        log.info("根据套餐id查询关联的检查组id：{}", setmealId);
        List<Long> groupIds = checkGroupService.findCheckGroupIdsBySetmealId(setmealId);
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, groupIds);
    }

    /**
     * 根据id查询检查组
     *
     * @param id
     * @return
     */
    @GetMapping("/findById")
    public Result findById(@RequestParam Long id) {
        log.info("根据id查询检查组：{}", id);
        CheckGroup group = checkGroupService.findById(id);
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, group);
    }

    /**
     * 查询所有检查组
     *
     * @return
     */
    @GetMapping("/findAll")
    public Result findAll() {
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, checkGroupService.findAll());
    }
}