package com.ecommerce.payment.dtos;

import jakarta.validation.constraints.NotNull;

public record OrderItemsDto(@NotNull String productId, @NotNull Integer quantity){

}
