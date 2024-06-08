package com.masoongsoong.FreashKeepie.domain.member.model;

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


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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


    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<UserRole> roles;

    // @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    // private Set<Fridge> fridges;



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
        return userId;
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

    @Override
    public boolean isEnabled() {
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
    }

}

