package com.masoongsoong.FreashKeepie.domain.Comment;

import com.masoongsoong.FreashKeepie.domain.BaseTimeEntity;
import com.masoongsoong.FreashKeepie.domain.member.User;
import com.masoongsoong.FreashKeepie.domain.Post.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.*;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="comment")
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_id")
    private Long id;

    @ManyToOne(fetch= LAZY)
    @JoinColumn(name="user_id", updatable=false)
    private User user;

    @ManyToOne(fetch= LAZY)
    @JoinColumn(name="post_id")
    private Post post;

    @Column(nullable = false)
    private String content;

    public void updateComment(String content){
        this.content=content;
    }

    public Comment(CommentCreateRequest commentCreateRequest){
        this.content = commentCreateRequest.getContent();
    }

}
