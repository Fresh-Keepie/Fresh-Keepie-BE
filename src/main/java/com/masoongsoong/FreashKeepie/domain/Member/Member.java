package com.masoongsoong.FreashKeepie.domain.Member;

import com.masoongsoong.FreashKeepie.domain.Post.Post;
import com.masoongsoong.FreashKeepie.domain.Scrap.Scrap;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Getter
@Entity
@NoArgsConstructor //기본 생성자 추가
@Table(name="member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id", unique=true, nullable=false)
    private Long id;

    @Column(length=100, nullable=false)
    private String loginID;

    @Column(length=100, nullable=false)
    private String password;

    @Column(length=20, nullable=false)
    private String nickname;

    @Column(length=50, nullable=false)
    private String email;

    @Column(nullable = false)
    private LocalDateTime birth;

    //유저가 쓴 게시글
    @OneToMany(mappedBy ="member", cascade=CascadeType.MERGE, orphanRemoval = true) //orphanRemoval=ture는 부모가 삭제되면 자식도 삭제시킴
    private List<Post> post =new ArrayList<>();

    //유저가 누른 스크랩
    @OneToMany(mappedBy = "member", cascade=CascadeType.MERGE,orphanRemoval = true)
    private List<Scrap> scraps=new ArrayList<>();

    public void edit(String newPassword, String newNickname){
        this.password=newPassword;
        this.password=newNickname;
    }

    @Builder
    public Member(String loginID, String password, String nickname, String email, LocalDateTime birth) {
        this.loginID=loginID;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.birth=birth;
    }
}
