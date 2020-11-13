package com.saket.springboot.service.impl;

import com.saket.springboot.domain.User;
import com.saket.springboot.exception.UserNotFoundException;
import com.saket.springboot.repository.IUserRepository;
import com.saket.springboot.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(Transactional.TxType.REQUIRED)
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final List<User> users;

    @Autowired
    public UserService(IUserRepository IUserRepository) {
        this.userRepository = IUserRepository;
        this.users = new ArrayList<>();
        initUsers();
    }

    private void initUsers() {
        users.add(new User("user1@gmail.com", "user1"));
        users.add(new User("user2@gmail.com", "user2"));
    }

    @Override
    public void saveInitialBatch() {
        userRepository.save(users);
    }

    @Override
    public User getUserByUserId(Long userId) {
        log.debug("Get user request: " + userId);
        User user = userRepository.findOne(userId);
        if (user == null) {
            log.error("User not found" + userId);
            throw new UserNotFoundException("User not found", userId);
        }
        return user;
    }

}
