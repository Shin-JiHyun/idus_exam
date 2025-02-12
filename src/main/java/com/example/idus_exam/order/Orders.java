package com.example.idus_exam.order;

import com.example.idus_exam.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable = false, unique = true, length = 12)
    private String orderNo;

    @Column(nullable = false, length = 100)
    private String productName;

    @Column(nullable = false)
    private ZonedDateTime orderDate;

    @ManyToOne
    @JoinColumn(name = "user_idx")
    private User user;
}
