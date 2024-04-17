package com.sky.service;

import com.sky.dto.ShoppingCartDTO;
import com.sky.result.Result;

/**
 * @Author FHR
 * @Create 2024/4/17 21:42
 * @Version 1.0
 */
public interface ShoppingCartService {
    Result add(ShoppingCartDTO shoppingCartDTO);

    Result showShoppingCart();

    Result cleanShoppingCart();

    Result subNumber(ShoppingCartDTO shoppingCartDTO);
}
