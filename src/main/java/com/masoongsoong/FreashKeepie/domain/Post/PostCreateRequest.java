package com.masoongsoong.FreashKeepie.domain.Post;
import com.masoongsoong.FreashKeepie.domain.member.model.User;
import lombok.Getter;

@Getter
public class PostCreateRequest {
    private String title;
    private String content;
    private String userId;
    public Post toEntity(Board category, User user){
        return Post.builder()
                .user(user)
                .category(category)
                .title(title)
                .content(content)
                .likeCnt(0)
                .view(0)
                .commentCnt(0)
                .build();
    }

}
