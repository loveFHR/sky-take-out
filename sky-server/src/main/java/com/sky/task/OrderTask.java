package com.sky.task;

/**
 * @Author FHR
 * @Create 2024/4/18 17:58
 * @Version 1.0
 */

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 定时处理订单状态
 */
@Component
@Slf4j
public class OrderTask {

    @Resource
    private OrderMapper orderMapper;
    /**
     * 处理超时订单
     * https://cron.qqe2.com/
     */
    @Scheduled(cron = "0 * * * * ? ") //每分钟触发一次
    public void processTimeoutOrder(){
        log.info("定时处理超时订单:{}", LocalDateTime.now());
        LocalDateTime time = LocalDateTime.now().plusMinutes(-15);
        //select * from order where status = ? and order_time < ? (当前时间减去15分钟)
        List<Orders> ordersList = orderMapper.getByStatusAndOrderTime(Orders.PAID, time);
        if (ordersList!=null&&ordersList.size()>0) {
            for (Orders orders : ordersList) {
                orders.setStatus(Orders.CANCELLED);//已取消
                orders.setCancelReason("订单超时，自动取消");
                orders.setCancelTime(LocalDateTime.now());
                orderMapper.update(orders);
            }
        }
    }

    /**
     * 处理一直处于派送中的订单
     */
    @Scheduled(cron = "0 0 1 * * ?") //每天凌晨一点触发一次
    public void processDeliverOrder(){
        log.info("定时处理处于派送中的订单:{}",LocalDateTime.now());
        LocalDateTime time = LocalDateTime.now().plusMinutes(-60); //上一天
        List<Orders> ordersList = orderMapper.getByStatusAndOrderTime(Orders.DELIVERY_IN_PROGRESS, time);
        if (ordersList!=null&&ordersList.size()>0) {
            for (Orders orders : ordersList) {
                orders.setStatus(Orders.COMPLETED);//已取消
                orderMapper.update(orders);
            }
        }
    }
}
