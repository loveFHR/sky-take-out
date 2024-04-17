package com.sky.service.impl;

import com.sky.context.BaseContext;
import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.entity.ShoppingCart;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.mapper.ShoppingCartMapper;
import com.sky.result.Result;
import com.sky.service.ShoppingCartService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * @Author FHR
 * @Create 2024/4/17 21:42
 * @Version 1.0
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Resource
    private ShoppingCartMapper shoppingCartMapper;
    @Resource
    private DishMapper dishMapper;
    @Resource
    private SetmealMapper setmealMapper;
    /**
     * 添加购物车
     * @param shoppingCartDTO
     * @return
     */
    @Override
    public Result add(ShoppingCartDTO shoppingCartDTO) {
        //判断当前加入购物车的商品是否已经存在
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO,shoppingCart);
        Long userId = BaseContext.getCurrentId();
        shoppingCart.setUserId(userId);
        List<ShoppingCart> list = shoppingCartMapper.list(shoppingCart);
        //如果已经存在，只需要将数量加1
        if (list!=null && list.size()>0) {
            ShoppingCart cart = list.get(0);
            cart.setNumber(cart.getNumber() + 1);
            shoppingCartMapper.updateNumberById(cart);
        }else {
            //如果不存在，插入一条购物车数据
            if (shoppingCartDTO.getDishId() !=null) {
                //添加的是菜品
                Dish dish = dishMapper.getById(shoppingCartDTO.getDishId());
                shoppingCart.setName(dish.getName());
                shoppingCart.setImage(dish.getImage());
                shoppingCart.setAmount(dish.getPrice());
            }else {
                //添加的是套餐
                Setmeal setmeal = setmealMapper.getById(shoppingCartDTO.getSetmealId());
                shoppingCart.setName(setmeal.getName());
                shoppingCart.setImage(setmeal.getImage());
                shoppingCart.setAmount(setmeal.getPrice());
            }
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCartMapper.insert(shoppingCart);
        }
        return Result.success();
    }

    @Override
    public Result showShoppingCart() {
        Long userId = BaseContext.getCurrentId();
        List<ShoppingCart> list = shoppingCartMapper.list(ShoppingCart.builder().userId(userId).build());
        return Result.success(list);
    }

    /**
     * 清空购物车
     * @return
     */
    @Override
    public Result cleanShoppingCart() {
        Long userId = BaseContext.getCurrentId();
        shoppingCartMapper.deleteByUserId(userId);
        return Result.success();
    }
    /**
     * 减少购物车商品数量
     * @param shoppingCartDTO
     * @return
     */
    @Override
    public Result subNumber(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO,shoppingCart);
        Long userId = BaseContext.getCurrentId();
        shoppingCart.setUserId(userId);
        List<ShoppingCart> list = shoppingCartMapper.list(shoppingCart);
        ShoppingCart cart = list.get(0);
        if (cart.getNumber() > 1) {
            cart.setNumber(cart.getNumber() - 1);
            shoppingCartMapper.updateNumberById(cart);
        }else if(cart.getNumber() == 1){
            shoppingCartMapper.deleteOne(cart.getId());
        }
        return Result.success();
    }
}
