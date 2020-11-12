package com.saket.springboot.service;

import com.saket.springboot.domain.User;

public interface IUserService {
    void saveInitialBatch();
    User getUserByUserId(Long userId);
}
