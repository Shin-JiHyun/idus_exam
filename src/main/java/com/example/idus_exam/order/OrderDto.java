package com.example.idus_exam.order;

import jakarta.persistence.criteria.Order;
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
        public String orderNo;
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
