package com.masoongsoong.FreashKeepie.domain.Like;

import com.masoongsoong.FreashKeepie.domain.Post.PostService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class LikeController {
    private final LikeService likeService;
    private final PostService postService;

    @GetMapping("/{boardId}/like/{postId}")
    public String addLike(@PathVariable Long postId, Authentication auth){
        likeService.addLike(auth.name(), postId);
        return "redirect:/board/"+postService.getCategory(postId)+"/post/"+postId;
    }

    @DeleteMapping("/{boardId}/like/{postId}")
    public String deleteLike(@PathVariable Long postId, Authentication auth) {
        likeService.deleteLike(auth.name(), postId);
        return "redirect:/board/" + postService.getCategory(postId) + "/post/" + postId;
    }
}
