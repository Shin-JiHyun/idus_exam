package com.example.idus_exam.user;

import com.example.idus_exam.order.Orders;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import  com.example.idus_exam.user.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable = false,length = 20)
    private String username;

    @Column(nullable = false,length = 30)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false,length = 20)
    private int phone;

    @Column(nullable = false,length = 100)
    private String email;

    @Column(nullable = true)
    private String gender;

    //private boolean enabled;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Orders> orders = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority(role.toString());

        authorities.add(authority);
        return authorities;
    }


    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
