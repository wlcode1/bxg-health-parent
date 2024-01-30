package com.itheima.controller.backend.security;

import com.itheima.common.entity.PageResult;
import com.itheima.common.entity.Result;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.FindPage;
import com.itheima.service.CheckGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     */
    @PostMapping("/add")
    public Result addCheckGroup(@RequestBody CheckGroup group) {
        log.info("添加检查组：{}", group);
        checkGroupService.addCheckGroup(group);
        return new Result(true, null);
    }

    /**
     * 删除检查组
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteCheckGroup(@PathVariable Long id) {
        log.info("删除检查组：{}", id);
        checkGroupService.deleteCheckGroup(id);
        return new Result(true, null);
    }

    /**
     * 修改检查组
     *
     * @param group
     * @return
     */
    @PutMapping("/edit")
    public Result editCheckSetmeal(@RequestBody CheckGroup group) {
        log.info("修改检查组：{}", group);
        checkGroupService.editCheckGroup(group);
        return new Result(true, null);
    }

    /**
     * 分页查询检查组
     *
     * @param page
     * @return
     */
    @GetMapping("/findPage")
    public Result page(FindPage page) {
        log.info("分页查询检查组：{}", page);
        PageResult pageResult = checkGroupService.pageQuery(page);
        return new Result(true, null, pageResult);
    }
}