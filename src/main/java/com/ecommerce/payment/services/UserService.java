package com.ecommerce.payment.services;

import com.ecommerce.payment.models.User;
import org.springframework.stereotype.Service;


public interface UserService {

    void save(User user);
}
