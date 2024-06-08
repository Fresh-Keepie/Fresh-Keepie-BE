package com.masoongsoong.FreashKeepie.domain.member.model;

import com.masoongsoong.FreashKeepie.domain.product.model.Fridge;
import jakarta.persistence.*;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String userId;

    @Column
    private String password;

    @Column(unique = true)
    private String nickname;

    @Column
    private String birth;

    @Column
    private String email;


    private List<UserRole> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Fridge> fridges;



    public User(int userId) {
    }




    @Builder
    private User(String userId, String password, String nickname, String birth, String email) {
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
        this.birth = birth;
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserRole role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getValue()));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}

