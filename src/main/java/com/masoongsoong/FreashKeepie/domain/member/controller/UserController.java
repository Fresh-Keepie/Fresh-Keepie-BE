package com.masoongsoong.FreashKeepie.domain.member.controller;

import com.masoongsoong.FreashKeepie.domain.member.model.LoginForm;
import com.masoongsoong.FreashKeepie.domain.member.model.UserCreateForm;
import com.masoongsoong.FreashKeepie.domain.member.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody UserCreateForm userCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid form data");
        }

        if (!userCreateForm.getPassword().equals(userCreateForm.getPassword2())) {
            return ResponseEntity.badRequest().body("Passwords do not match");
        }

        try {
            userService.create(userCreateForm.getUserId(), userCreateForm.getNickname(), userCreateForm.getBirth(),
                    userCreateForm.getEmail(), userCreateForm.getPassword());
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return ResponseEntity.status(409).body("User already exists: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Signup failed: " + e.getMessage());
        }

        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginForm loginForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("유효하지 않은 폼 데이터입니다");
        }

        try {
            userService.logincreate(loginForm.getUserId(), loginForm.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("로그인 실패: " + e.getMessage());
        }

        return ResponseEntity.ok("사용자 로그인 성공");
    }

}