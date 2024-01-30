package com.itheima.controller.backend.security;

import com.itheima.common.entity.PageResult;
import com.itheima.common.entity.Result;
import com.itheima.pojo.FindPage;
import com.itheima.pojo.Setmeal;
import com.itheima.service.CheckSetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/setmeal")
public class CheckSetmealController {
    @Autowired
    CheckSetmealService checkSetmealService;

    /**
     * 添加检查套餐
     *
     * @param setmeal
     */
    @PostMapping("/add")
    public Result addCheckSetmeal(@RequestBody Setmeal setmeal) {
        log.info("添加套餐：{}", setmeal);
        checkSetmealService.addCheckSetmeal(setmeal);
        return new Result(true, "添加套餐成功");
    }

    /**
     * 删除套餐
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteCheckSetmeal(@PathVariable Long id) {
        log.info("删除套餐：{}", id);
        checkSetmealService.deleteCheckSetmeal(id);
        return new Result(true, null);
    }

    /**
     * 修改套餐
     *
     * @param setmeal
     * @return
     */
    @PutMapping("/edit")
    public Result editCheckSetmeal(@RequestBody Setmeal setmeal) {
        log.info("修改套餐：{}", setmeal);
        checkSetmealService.editCheckSetmeal(setmeal);
        return new Result(true, null);
    }

    /**
     * 分页查询套餐
     *
     * @param page
     * @return
     */
    @GetMapping("/findPage")
    public Result page(FindPage page) {
        log.info("分页查询套餐：{}", page);
        PageResult pageResult = checkSetmealService.pageQuery(page);
        return new Result(true, null, pageResult);
    }
}