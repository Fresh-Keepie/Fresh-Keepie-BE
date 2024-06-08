package com.masoongsoong.FreashKeepie.domain.Comment;

import com.masoongsoong.FreashKeepie.domain.Post.PostService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.boot.Banner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;

    // 댓글 작성
    @PostMapping("/{boardId}/comment/{postId}")
    public ResponseEntity<String> addComment(@PathVariable Long postId, @RequestBody CommentCreateRequest req,
                                     @RequestParam String userId){

        String result = commentService.writeComment(postId, req, userId);
        return ResponseEntity.ok(result);
    }

    // 댓글 수정
    @PutMapping("/{boardId}/comment/{commentId}")
    public ResponseEntity<String> editComment(@PathVariable Long commentId, @RequestBody CommentCreateRequest req,
                              @RequestParam String userId){

        String result = commentService.editComment(commentId, req, userId); //작성자가 쓴 댓글이어야 postId가 반환됨
        return ResponseEntity.ok(result);
    }

    // 댓글 삭제
    @DeleteMapping("/{boardId}/comment/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId, @RequestParam String userId){

        String result = commentService.deleteComment(commentId, userId); //작성자가 쓴 댓글이어야 postId가 반환됨
        return ResponseEntity.ok(result);
    }
}
