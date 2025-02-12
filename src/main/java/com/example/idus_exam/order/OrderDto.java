package com.example.idus_exam.order;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

public class OrderDto {

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    public static class OrderResponse {
        @Pattern(regexp = "^[A-Z0-9]{10}$", message = "Order number must be 10 characters long, containing only uppercase letters and digits.")
        public String orderNo;
        @Pattern(regexp = "^[\\p{L}\\p{N}\\p{P}\\p{Z}\\uD83C-\\uDBFF\\uDC00-\\uDFFF]+$", message = "Input can include letters, numbers, punctuation, spaces, and emojis.")
        public String productName;
        public ZonedDateTime orderDate;

        public static OrderResponse from(Orders order) {
            return OrderResponse.builder()
                    .orderNo(order.getOrderNo())
                    .productName(order.getProductName())
                    .orderDate(order.getOrderDate())
                    .build();
        }
    }



}
