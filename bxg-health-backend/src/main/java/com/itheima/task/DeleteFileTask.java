package com.itheima.task;

import com.itheima.common.constant.RedisConstant;
import com.itheima.common.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
public class DeleteFileTask {
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    AliOssUtil aliOssUtil;

    /**
     * 每天上午1点执行
     */
    @Scheduled(cron = "0 0 1 ? * *")
    public void deleteFile() {
        //获取得到redis中的数据
        Set db_resources = redisTemplate.opsForSet().members(RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        Set resources = redisTemplate.opsForSet().members(RedisConstant.SETMEAL_PIC_RESOURCES);
        Set temp_resources = new HashSet(resources);
        temp_resources.removeAll(db_resources);

        //遍历删除阿里云和redis中的数据
        for (Object tempResource : temp_resources) {
            aliOssUtil.delete((String) tempResource);
            redisTemplate.opsForSet().remove(RedisConstant.SETMEAL_PIC_RESOURCES, (String) tempResource);
        }
    }
}