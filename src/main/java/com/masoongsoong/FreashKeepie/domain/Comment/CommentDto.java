package com.masoongsoong.FreashKeepie.domain.Comment;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentDto {
    // 댓글 번호
    private Long id;

    // 댓글 내용
    private String content;

    // 댓글이 작성된 게시글 번호
    private Long post_id;

    // 댓글을 작성한 유저Id
    private Long userId;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentDto(Comment comment){
        this.id = comment.getId();
        this.content = comment.getContent();
        this.post_id = comment.getPost().getId();
        this.userId = comment.getUser().getId();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
