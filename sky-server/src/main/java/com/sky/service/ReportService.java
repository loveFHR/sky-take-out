package com.sky.service;

import com.sky.result.Result;
import com.sky.vo.OrderReportVO;
import com.sky.vo.SalesTop10ReportVO;
import com.sky.vo.TurnoverReportVO;
import com.sky.vo.UserReportVO;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

/**
 * @Author FHR
 * @Create 2024/4/19 13:10
 * @Version 1.0
 */
public interface ReportService {
    Result<TurnoverReportVO> getTurnoverStatistics(LocalDate begin, LocalDate end);

    Result<UserReportVO> getUserStatistics(LocalDate begin, LocalDate end);
    /**
     * 根据时间区间统计订单数量
     * @param begin
     * @param end
     * @return
     */
    OrderReportVO getOrderStatistics(LocalDate begin, LocalDate end);

    /**
     * 查询指定时间区间内的销量排名top10
     * @param begin
     * @param end
     * @return
     */
    SalesTop10ReportVO getSalesTop10(LocalDate begin, LocalDate end);

    /**
     * 导出近30天的运营数据报表
     * @param response
     **/
    void exportBusinessData(HttpServletResponse response);
}
