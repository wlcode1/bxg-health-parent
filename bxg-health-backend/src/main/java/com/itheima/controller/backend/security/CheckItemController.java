package com.itheima.controller.backend.security;

import com.itheima.common.constant.MessageConstant;
import com.itheima.common.entity.PageResult;
import com.itheima.common.entity.Result;
import com.itheima.pojo.CheckItem;
import com.itheima.pojo.FindPage;
import com.itheima.service.CheckItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    /**
     * 删除检查项
     *
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public Result deleteCheckItem(@RequestParam Long id) {
        log.info("删除检查项：{}", id);
        checkItemService.deleteCheckItem(id);
        return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }

    /**
     * 编辑检查项
     *
     * @param item
     * @return
     */
    @PostMapping("/edit")
    public Result editCheckItem(@RequestBody CheckItem item) {
        log.info("编辑检查项： {}", item);
        checkItemService.editCheckItem(item);
        return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }

    /**
     * 分页查询检查项
     *
     * @param findPage
     * @return
     */
    @PostMapping("/findPage")
    public PageResult page(@RequestBody FindPage findPage) {
        log.info("分页查询检查项：{}", findPage);
        return checkItemService.pageQuery(findPage);
    }

    /**
     * 查询所有检查项
     *
     * @return
     */
    @GetMapping("/findAll")
    public Result findAll() {
        log.info("查询所有检查项");
        List<CheckItem> checkItemList = checkItemService.findAll();
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkItemList);
    }

    /**
     * 根据检查组id查询相关联的检查项id
     *
     * @param checkgroupId
     * @return
     */
    @GetMapping("/findCheckItemIdsByCheckGroupId")
    public Result findCheckItemIdsByCheckGroupId(@RequestParam Long checkgroupId) {
        log.info("根据检查组id查询相关联的检查项id：{}", checkgroupId);
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkItemService.findCheckItemIdsByCheckGroupId(checkgroupId));
    }

    /**
     * 根据id查询检查项
     *
     * @param id
     * @return
     */
    @GetMapping("/findById")
    public Result findById(@RequestParam Long id) {
        log.info("根据id查询检查项: {}", id);
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkItemService.findById(id));
    }
}