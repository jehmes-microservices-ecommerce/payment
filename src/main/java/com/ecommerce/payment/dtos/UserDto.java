package com.ecommerce.payment.dtos;

import com.ecommerce.payment.enums.UserType;
import com.ecommerce.payment.models.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@Data
public class UserDto {

    private UUID userId;
    private String username;
    private String email;
    private String cpf;
    private String actionType;
    private String userType;


    public User convertToUser() {
        var user = new User();
        BeanUtils.copyProperties(this, user);
        user.setUserType(UserType.valueOf(this.getUserType()));
        return user;
    }
}
