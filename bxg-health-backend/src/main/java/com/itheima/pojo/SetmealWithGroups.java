package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 套餐里面包含的检查组
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SetmealWithGroups implements Serializable {
    private Integer setmealId;//套餐id
    private Integer checkgroupId;//组id
}