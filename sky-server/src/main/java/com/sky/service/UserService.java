package com.sky.service;

import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;

/**
 * @Author FHR
 * @Create 2024/4/16 20:35
 * @Version 1.0
 */
public interface UserService {

    User wxLogin(UserLoginDTO userLoginDTO);
}
