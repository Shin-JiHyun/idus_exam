package com.example.idus_exam.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void create(UserDto.SignupRequest signupRequest) {
        userRepository.save(signupRequest.toEntity(passwordEncoder.encode(signupRequest.getPassword())));
        return ;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> result = userRepository.findByEmail(username);

        if (result.isPresent()) {
            // 7번 로직
            User user = result.get();
            return user;
        }

        return null;
    }

    public UserDto.UserResponse getUserDetail(User user) {
       User user1 =  userRepository.findByEmail(user.getEmail()).orElseThrow();
        return UserDto.UserResponse.from(user1);
    }
    @Transactional(readOnly = true)
    public Long getUserIdx(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        return user.getIdx();
    }

    @Transactional(readOnly = true)
    public List<User> getUsersByName(String name) {
       List<User> user = userRepository.findByUsernameContaining(name);
        return user;
    }
    @Transactional(readOnly = true)
    public Long getUserIdxByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        return user.getIdx();
    }


}
