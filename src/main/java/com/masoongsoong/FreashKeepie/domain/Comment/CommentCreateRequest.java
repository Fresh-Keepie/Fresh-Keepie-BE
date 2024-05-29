package com.masoongsoong.FreashKeepie.domain.Comment;

import com.masoongsoong.FreashKeepie.domain.Member.Member;
import com.masoongsoong.FreashKeepie.domain.Post.Post;
import lombok.Getter;

@Getter
public class CommentCreateRequest {
    private String content;

    public Comment toEntity(Post post, Member member){
        return Comment.builder()
                .member(member)
                .post(post)
                .content(content)
                .build();
    }
}
