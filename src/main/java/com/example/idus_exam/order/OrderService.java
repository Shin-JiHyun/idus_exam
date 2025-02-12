package com.example.idus_exam.order;

import com.example.idus_exam.user.User;
import com.example.idus_exam.user.UserRepository;
import com.example.idus_exam.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;


    @Transactional(readOnly = true)
    public List<OrderDto.OrderResponse> getOrderList(User user, int page, int size) {
        //페이징
        List<Orders> orders = orderRepository.findAllByUserIdx(user.getIdx(), PageRequest.of(page,size));
        return orders.stream()
                .map(OrderDto.OrderResponse::from)
                .collect(Collectors.toList());

    }


    @Transactional(readOnly = true)
    public List<OrderDto.OrderResponse> getOrderByUser(String username) {
            List<User> users = userService.getUsersByName(username);
                List<Orders> orders = new ArrayList<>();
            for (User user : users) {
                orders.add(orderRepository.findTop1ByUserIdxOrderByOrderDateDesc(user.getIdx()).orElseThrow());
            }

            return orders.stream().map(OrderDto.OrderResponse::from).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public OrderDto.OrderResponse getOrderByEmail(String email) {
        Long userIdx = userService.getUserIdxByEmail(email);
        Orders order = orderRepository.findTop1ByUserIdxOrderByOrderDateDesc(userIdx).orElseThrow();
        return OrderDto.OrderResponse.from(order);
    }
}
