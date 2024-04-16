package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author FHR
 * @Create 2024/4/6 20:21
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping("/admin/common")
@Api(tags = "通用接口")
public class CommonController {
    @Resource
    private AliOssUtil aliOssUtil;

    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> upload(MultipartFile file){
        try {
            //获取原始文件名
            String originalFilename = file.getOriginalFilename();
            //获取文件名后缀
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

            String objectName = UUID.randomUUID().toString() + extension;
            //String filePath = aliOssUtil.upload(file.getBytes(), objectName);
            String filePath = "https://img.zcool.cn/community/01d1085c0a2c25a80121ab5d8da8f5.jpg@1280w_1l_2o_100sh.jpg";
            return Result.success(filePath);
        } catch (Exception e) {
            log.error("文件上传失败");
        }
        return  Result.error(MessageConstant.UPLOAD_FAILED);
    }
}
