package com.masoongsoong.FreashKeepie.domain.member.model;

<<<<<<< HEAD
import com.masoongsoong.FreashKeepie.domain.Comment.Comment;
import com.masoongsoong.FreashKeepie.domain.Like.Like;
import com.masoongsoong.FreashKeepie.domain.Post.Post;
import com.masoongsoong.FreashKeepie.domain.Scrap.Scrap;
import com.masoongsoong.FreashKeepie.domain.member.model.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
=======
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
>>>>>>> 950559fe53b06144550e26fe75beecfc29520123


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
    private Long id;
=======
    private int id;
>>>>>>> 950559fe53b06144550e26fe75beecfc29520123

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


<<<<<<< HEAD
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<UserRole> roles;

    // @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    // private Set<Fridge> fridges;
=======
    private List<UserRole> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Fridge> fridges;
>>>>>>> 950559fe53b06144550e26fe75beecfc29520123



    public User(int userId) {
<<<<<<< HEAD

=======
>>>>>>> 950559fe53b06144550e26fe75beecfc29520123
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
<<<<<<< HEAD
        return userId;
=======
        return null;
>>>>>>> 950559fe53b06144550e26fe75beecfc29520123
    }

    @Override
    public boolean isAccountNonExpired() {
<<<<<<< HEAD
        return true;
=======
        return false;
>>>>>>> 950559fe53b06144550e26fe75beecfc29520123
    }

    @Override
    public boolean isAccountNonLocked() {
<<<<<<< HEAD
        return true;
=======
        return false;
>>>>>>> 950559fe53b06144550e26fe75beecfc29520123
    }

    @Override
    public boolean isCredentialsNonExpired() {
<<<<<<< HEAD
        return true;
=======
        return false;
>>>>>>> 950559fe53b06144550e26fe75beecfc29520123
    }

    @Override
    public boolean isEnabled() {
<<<<<<< HEAD
        return true;
    }

    // 유저가 쓴 게시글
    @OneToMany(mappedBy ="user", orphanRemoval = true, cascade=CascadeType.MERGE) //orphanRemoval=ture는 부모가 삭제되면 자식도 삭제시킴
    private List<Post> post =new ArrayList<>();

    // 유저가 누른 스크랩
    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade=CascadeType.MERGE)
    private List<Scrap> scraps=new ArrayList<>();

    // 유저가 쓴 댓글
    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade=CascadeType.MERGE)
    private List<Comment> comments =new ArrayList<>();

    // 유저가 누른 좋아요
    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade=CascadeType.MERGE)
    private List<Like> likes =new ArrayList<>();

    public void edit(String newPassword, String newNickname){
        this.password=newPassword;
        this.nickname=newNickname;
=======
        return false;
>>>>>>> 950559fe53b06144550e26fe75beecfc29520123
    }

}

