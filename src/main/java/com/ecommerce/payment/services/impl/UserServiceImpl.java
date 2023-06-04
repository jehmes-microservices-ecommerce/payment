package com.ecommerce.payment.services.impl;

import com.ecommerce.payment.models.User;
import com.ecommerce.payment.repositories.UserRepository;
import com.ecommerce.payment.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
