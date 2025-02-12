package com.example.idus_exam.order;

import com.example.idus_exam.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/order")
@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;


    @Operation(summary = "사용자의 전체 주문 목록 조회", description = "현재 로그인한 사용자의 전체 주문 목록을 가져옵니다.")
    @GetMapping("/list")
    public ResponseEntity<?> findOrderList(@AuthenticationPrincipal User user
    ,int page, int list) {
        List<OrderDto.OrderResponse> orders = orderService.getOrderList(user, page, list);
        return ResponseEntity.ok().body(orders);
    }

    @Operation(summary = "유저명으로 주문 조회", description = "주어진 유저명(username)을 포함하는 사용자의 주문을 가져옵니다.")
    @GetMapping("/username")
    public ResponseEntity<?> findByUsername(@RequestParam @Parameter(example = "신지현") String username) {
        List<OrderDto.OrderResponse> order = orderService.getOrderByUser(username);
        return ResponseEntity.ok().body(order);
    }

    @Operation(summary = "이메일로 주문 조회", description = "주어진 이메일을 가진 사용자의 주문을 가져옵니다.")
    @GetMapping("/email")
    public ResponseEntity<?> findByEmail(@RequestParam @Parameter(example = "example2@example.com")String email) {
        OrderDto.OrderResponse order = orderService.getOrderByEmail(email);
        return ResponseEntity.ok().body(order);
    }
}
