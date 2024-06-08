package com.masoongsoong.FreashKeepie.domain.member.repository;

import com.masoongsoong.FreashKeepie.domain.member.model.User;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Id> {
    boolean existsByUserId(String userId);
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
    Optional<User> findByUserId(String userId);



}

