package com.ecommerce.payment.dtos;

import com.ecommerce.payment.enums.ActionType;
import com.ecommerce.payment.models.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@Data
public class UserEventDto {

    private UUID userId;
    private String username;
    private String email;
    private String cpf;
    private ActionType actionType;

    public User convertToUser() {
        var user = new User();
        BeanUtils.copyProperties(this, user);
        return user;
    }
}
