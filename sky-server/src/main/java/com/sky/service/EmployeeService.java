package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;
import com.sky.result.Result;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    Result save(EmployeeDTO employeeLoginDTO);

    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    Result startOrStop(Integer status, Long id);

    Result getById(Long id);

    Result update(EmployeeDTO employeeDTO);
}
