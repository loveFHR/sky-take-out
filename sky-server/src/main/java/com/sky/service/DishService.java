package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.vo.DishVO;

import java.util.List;

/**
 * @Author FHR
 * @Create 2024/4/6 20:52
 * @Version 1.0
 */
public interface DishService {
    /**
     * 新增菜品和对应的口味
     * @param dishDTO
     * @return
     */
    Result saveWithFlavor(DishDTO dishDTO);

    Result<PageResult> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    Result deleteBatch(List<Long> ids);

    Result<DishVO> getByIdWithFlavor(Long id);

    Result updateWithFlavor(DishDTO dishDTO);
}
