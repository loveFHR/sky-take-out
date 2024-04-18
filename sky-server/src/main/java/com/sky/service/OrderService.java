package com.sky.service;

import com.sky.dto.OrdersSubmitDTO;
import com.sky.result.Result;
import com.sky.vo.OrderSubmitVO;

/**
 * @Author FHR
 * @Create 2024/4/18 13:22
 * @Version 1.0
 */
public interface OrderService {
    Result<OrderSubmitVO> submitOrder(OrdersSubmitDTO ordersSubmitDTO);
}
