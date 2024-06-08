package com.masoongsoong.FreashKeepie.domain.member.service;

import com.masoongsoong.FreashKeepie.domain.member.model.User;
import com.masoongsoong.FreashKeepie.domain.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User create(String userId, String nickname, String birth, String email, String password) {
        // 중복 사용자 검사
        if (userRepository.existsByUserId(userId)) {
            throw new DataIntegrityViolationException("Username already exists");
        }

        if (userRepository.existsByEmail(email)) {
            throw new DataIntegrityViolationException("Email already exists");
        }
        if (userRepository.existsByNickname(nickname)) {
            throw new DataIntegrityViolationException("Nickname already exists");
        }

        // 생년월일 필드가 null인지 확인
        if (birth == null || birth.isEmpty()) {
            throw new DataIntegrityViolationException("Birth date cannot be null or empty");
        }


        User user = new User();
        user.setUserId(userId);
        user.setNickname(nickname);
        user.setBirth(birth);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));


        try {
            userRepository.save(user);
            return user;
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            throw new DataIntegrityViolationException("Data integrity violation: " + e.getMessage());
        }
    }


    public User logincreate(String userId, String password) throws Exception {
        Optional<User> optionalUser = userRepository.findByUserId(userId);
        if (!optionalUser.isPresent() || !passwordEncoder.matches(password, optionalUser.get().getPassword())) {
            throw new Exception("Invalid userId or password");
        }
        return optionalUser.get();
    }
}