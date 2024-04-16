package com.sky.controller.User;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author FHR
 * @Create 2024/4/16 15:42
 * @Version 1.0
 */
@RestController("userShopController")
@RequestMapping("/user/shop")
@Api(tags = "店铺相关接口")
public class ShopController {
    @Resource
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 获取店铺营业状态
     * @return
     */
    @GetMapping("/status")
    @ApiOperation("获取店铺营业状态")
    public Result<Integer> getStatus(){
        Integer status = Integer.valueOf(stringRedisTemplate.opsForValue().get("SHOP_STATUS"));
        return Result.success(status);
    }
}
