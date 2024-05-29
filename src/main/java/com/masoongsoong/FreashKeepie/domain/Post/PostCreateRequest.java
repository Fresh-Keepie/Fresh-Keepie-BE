package com.masoongsoong.FreashKeepie.domain.Post;

import com.masoongsoong.FreashKeepie.domain.Member.Member;
import lombok.Getter;

@Getter
public class PostCreateRequest {
    private String title;
    private String content;

    public Post toEntity(Board category, Member member){
        return Post.builder()
                .member(member)
                .category(category)
                .title(title)
                .content(content)
                .likeCnt(0)
                .view(0)
                .commentCnt(0)
                .build();
    }
}
