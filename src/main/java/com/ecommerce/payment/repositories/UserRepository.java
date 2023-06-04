package com.ecommerce.payment.repositories;

import com.ecommerce.payment.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
