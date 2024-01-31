package com.itheima.controller.backend.security;

import com.itheima.common.constant.MessageConstant;
import com.itheima.common.entity.PageResult;
import com.itheima.common.entity.Result;
import com.itheima.pojo.FindPage;
import com.itheima.pojo.Setmeal;
import com.itheima.service.CheckSetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/setmeal")
public class CheckSetmealController {
    @Autowired
    CheckSetmealService checkSetmealService;

    /**
     * 添加检查套餐
     *
     * @param checkgroupIds
     * @param setmeal
     * @return
     */
    @PostMapping("/add")
    public Result addCheckSetmeal(@RequestParam List<Long> checkgroupIds, @RequestBody Setmeal setmeal) {
        log.info("添加套餐：{},{}", setmeal, checkgroupIds);
        checkSetmealService.addCheckSetmeal(setmeal, checkgroupIds);
        return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
    }

    /**
     * 根据id查询套餐
     *
     * @param id
     * @return
     */
    @GetMapping("/findById")
    public Result findById(@RequestParam Long id) {
        log.info("根据id查询套餐：{}", id);
        Setmeal setmeal = checkSetmealService.findById(id);
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setmeal);
    }

    /**
     * 修改套餐
     *
     * @param checkGroupIds
     * @param setmeal
     * @return
     */
    @PostMapping("/edit")
    public Result editCheckSetmeal(@RequestParam List<Long> checkGroupIds, @RequestBody Setmeal setmeal) {
        log.info("修改套餐：{}, {}", setmeal, checkGroupIds);
        checkSetmealService.editCheckSetmeal(setmeal, checkGroupIds);
        return new Result(true, MessageConstant.EDIT_SETMEAL_SUCCESS);
    }

    /**
     * 分页查询套餐
     *
     * @param page
     * @return
     */
    @PostMapping("/findPage")
    public PageResult page(@RequestBody FindPage page) {
        log.info("分页查询套餐：{}", page);
        return checkSetmealService.pageQuery(page);
    }
}