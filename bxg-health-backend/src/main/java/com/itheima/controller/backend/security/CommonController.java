package com.itheima.controller.backend.security;

import com.itheima.common.entity.Result;
import com.itheima.common.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/setmeal")
@Slf4j
public class CommonController {
    @Autowired
    private AliOssUtil aliOssUtil;

    @PostMapping("/upload")
    public Result upload(MultipartFile imgFile) {
        log.info("文件上传：{}", imgFile);
        try {
            //原始文件名
            String originalFileName = imgFile.getOriginalFilename();
            //截取原始文件名的后缀 .png
            String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
            //构造新文件名称
            String objectName = UUID.randomUUID().toString() + extension;

            //文件的请求路径
            String filePath = aliOssUtil.upload(imgFile.getBytes(), objectName);
            return new Result(true, "图片上传成功", filePath);
        } catch (Exception e) {
            log.info("文件上传失败：{}", e);
        }
        return new Result(false, "图片上传失败", null);
    }
}