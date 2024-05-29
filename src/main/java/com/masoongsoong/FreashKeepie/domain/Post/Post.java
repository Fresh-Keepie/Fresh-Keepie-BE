package com.masoongsoong.FreashKeepie.domain.Post;

import com.masoongsoong.FreashKeepie.domain.BaseTimeEntity;
import com.masoongsoong.FreashKeepie.domain.Comment.Comment;
import com.masoongsoong.FreashKeepie.domain.Member.Member;
import com.masoongsoong.FreashKeepie.domain.Scrap.Scrap;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.FetchType.*;

@Builder
@Entity //테이블이다.
@Getter //유튜브에선 @Data로 했으나 여기엔 @Setter가 포함돼있음
@AllArgsConstructor
@NoArgsConstructor
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    private Long id;

    @ManyToOne(fetch= LAZY, cascade=CascadeType.MERGE, targetEntity = Member.class)
    @JoinColumn(name="member_id", updatable=false)
    private Member member;

    private String loginID; //안쓰는데 오류가 만들래서 만듦

    @Column(nullable=false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    private Board category; //게시판 카테고리

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view;     // 조회수

    @OneToMany(mappedBy = "post", orphanRemoval=true)
    private List<Scrap> scraps; //마이페이지에서 조회할 스크랩 목록

    @Column
    private Integer likeCnt; //좋아요 수

    @OneToMany(mappedBy="post", orphanRemoval = true)
    private List<Comment> comments;

    @Column
    private Integer commentCnt; //댓글 수

    public void commentChange(Integer commentCnt){
        this.commentCnt=commentCnt;
    }

    public void update(PostDTO dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }

    public void likeChange(Integer likeCnt){
        this.likeCnt=likeCnt;
    }

}
