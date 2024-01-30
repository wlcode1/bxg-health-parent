package com.itheima.controller.backend.security;

import com.itheima.common.entity.PageResult;
import com.itheima.common.entity.Result;
import com.itheima.pojo.CheckItem;
import com.itheima.pojo.FindPage;
import com.itheima.service.CheckItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/checkitem")
public class CheckItemController {
    @Autowired
    CheckItemService checkItemService;

    /**
     * 添加检查项
     */
    @PostMapping("/add")
    public Result addCheckItem(@RequestBody CheckItem item) {
        log.info("添加检查项：{}", item);
        checkItemService.addCheckItem(item);
        return new Result(true, null);
    }

    /**
     * 删除检查项
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public Result deleteCheckItem(Long id) {
        log.info("删除检查项：{}", id);
        checkItemService.deleteCheckItem(id);
        return new Result(true, null);
    }

    /**
     * 编辑检查项
     *
     * @param item
     * @return
     */
    @PutMapping("/edit")
    public Result editCheckItem(@RequestBody CheckItem item) {
        log.info("编辑检查项： {}", item);
        checkItemService.editCheckItem(item);
        return new Result(true, null);
    }

    /**
     * 分页查询检查项
     *
     * @param findPage
     * @return
     */
    @GetMapping("/findPage")
    public Result page(FindPage findPage) {
        log.info("分页查询检查项：{}", findPage);
        PageResult pageResult = checkItemService.pageQuery(findPage);
        return new Result(true, null, pageResult);
    }
}