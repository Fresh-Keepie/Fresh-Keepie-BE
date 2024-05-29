package com.masoongsoong.FreashKeepie.domain.Post;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostDTO {
    private Long id;
    private String loginID;
    private String nickname;
    private String title;
    private String content;
    private Integer likeCnt;
    private Integer view;
    //좋아요 여부
    //스크랩 여부

    private Integer commentCnt;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public static PostDTO of(Post post) {
        return PostDTO.builder()
                .id(post.getId())
                .loginID(post.getMember().getLoginID())
                .nickname(post.getMember().getNickname())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .modifiedAt(post.getModifiedAt())
                .likeCnt(post.getLikeCnt())
                .view(post.getView())
                .commentCnt(post.getCommentCnt())
                .build();
    }
}
