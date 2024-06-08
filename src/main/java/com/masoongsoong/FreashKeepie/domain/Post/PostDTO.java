package com.masoongsoong.FreashKeepie.domain.Post;

import com.masoongsoong.FreashKeepie.domain.Comment.CommentDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Getter
@NoArgsConstructor
public class PostDTO {
    private Long id;
    private String userId;
    private String nickname;
    private String title;
    private String content;
    private Integer likeCnt;
    private Integer view;
    //좋아요 여부
    private boolean likeCheck;

    //스크랩 여부
    private boolean scrapCheck;
    private Integer commentCnt;
    // 댓글 리스트
    private List<CommentDto> commentDtoList;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public PostDTO(Post post, boolean likeCheck, boolean scrapCheck){
        this.id = post.getId();
        this.userId = post.getUser().getUserId();
        this.nickname = post.getUser().getNickname();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.likeCnt = post.getLikeCnt();
        this.view = post.getView();
        this.commentCnt = post.getCommentCnt();
        this.commentDtoList = post.getComments().stream()
                .map(CommentDto::new)
                .sorted(Comparator.comparing(CommentDto::getCreatedAt).reversed())
                .toList();
        this.likeCheck = likeCheck;
        this.scrapCheck = scrapCheck;
    }
}
