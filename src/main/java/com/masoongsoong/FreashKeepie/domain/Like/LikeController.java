package com.masoongsoong.FreashKeepie.domain.Like;

import com.masoongsoong.FreashKeepie.domain.Post.PostService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class LikeController {
    private final LikeService likeService;
    private final PostService postService;

    // 게시글 좋아요
    @PostMapping("/{boardId}/like/{postId}")
    public ResponseEntity<String> addLike(@PathVariable Long postId, @RequestParam String userId){
        String result = likeService.addLike(postId, userId);
        return ResponseEntity.ok(result);
    }

    // 게시글 좋아요 취소
    @DeleteMapping("/{boardId}/like/{postId}")
    public ResponseEntity<String> deleteLike(@PathVariable Long postId, @RequestParam String userId) {
        String result = likeService.deleteLike(postId, userId);
        return ResponseEntity.ok(result);
    }
}
