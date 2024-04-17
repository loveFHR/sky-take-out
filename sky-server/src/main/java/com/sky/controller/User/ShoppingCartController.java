package com.sky.controller.User;

import com.sky.dto.ShoppingCartDTO;
import com.sky.result.Result;
import com.sky.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author FHR
 * @Create 2024/4/17 21:38
 * @Version 1.0
 */
@RestController
@RequestMapping("/user/shoppingCart")
@Slf4j
@Api(tags = "C端-购物车相关接口")
public class ShoppingCartController {

    @Resource
    private ShoppingCartService shoppingCartService;
    /**
     * 添加购物车
     * @param shoppingCartDTO
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("添加购物车")
    public Result add(@RequestBody ShoppingCartDTO shoppingCartDTO){
        return shoppingCartService.add(shoppingCartDTO);
    }

    /**
     * 查看购物车
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查看购物车")
    public Result list(){
        return shoppingCartService.showShoppingCart();
    }

    /**
     * 清空购物车
     * @return
     */
    @DeleteMapping("/clean")
    @ApiOperation("清空购物车")
    public Result clean(){
        return shoppingCartService.cleanShoppingCart();
    }

    /**
     * 减少购物车商品数量
     * @param shoppingCartDTO
     * @return
     */
    @PostMapping("/sub")
    @ApiOperation("减少购物车商品数量")
    public Result sub(@RequestBody ShoppingCartDTO shoppingCartDTO){
        return shoppingCartService.subNumber(shoppingCartDTO);
        //return Result.error("功能尚未完善");
    }
}
