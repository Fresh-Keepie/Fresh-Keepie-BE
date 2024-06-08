package com.masoongsoong.FreashKeepie.domain.Comment;

import com.masoongsoong.FreashKeepie.domain.member.model.User;
import com.masoongsoong.FreashKeepie.domain.Post.Post;
import lombok.Getter;

@Getter
public class CommentCreateRequest {
    private String content;

    public Comment toEntity(Post post, User user){
        return Comment.builder()
                .user(user)
                .post(post)
                .content(content)
                .build();
    }
}
