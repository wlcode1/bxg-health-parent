package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
public class GroupWithItem implements Serializable {
    private Integer checkgroupId;
    private Integer checkitemId;
}