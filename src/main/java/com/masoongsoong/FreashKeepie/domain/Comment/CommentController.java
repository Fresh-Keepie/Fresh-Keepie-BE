package com.masoongsoong.FreashKeepie.domain.Comment;

import com.masoongsoong.FreashKeepie.domain.Post.PostService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;

    @PostMapping("/{boardId}/comment/{postId}")
    public String addComment(@PathVariable Long postId,
                             @ModelAttribute CommentCreateRequest req, Authentication auth){

        commentService.writeComment(postId, req, auth.name());
        return "/board/"+postService.getCategory(postId)+"/post/"+postId;
    }

    @PatchMapping("/{boardId}/comment/{commentId}")
    public String editComment(@PathVariable Long commentId,
                             @ModelAttribute CommentCreateRequest req, Authentication auth){

        Long postId = commentService.editComment(commentId, req.getContent(), auth.name()); //작성자가 쓴 댓글이어야 postId가 반환됨
        return "/board/"+postService.getCategory(postId)+"/post/"+postId;
    }

    @DeleteMapping("/{boardId}/comment/{commentId}")
    public String deleteComment(@PathVariable Long commentId,
                                Authentication auth){

        Long postId = commentService.deleteComment(commentId, auth.name()); //작성자가 쓴 댓글이어야 postId가 반환됨
        return "/board/"+postService.getCategory(postId)+"/post/"+postId;
    }
}
