package com.masoongsoong.FreashKeepie.domain.Scrap;

import com.masoongsoong.FreashKeepie.domain.Post.PostService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class ScrapController {

    private final ScrapService scrapService;
    private final PostService postService;

    @GetMapping("/{boardId}/save/{postId}") //url로 들어온
    public String addScrap(@PathVariable Long postId, Authentication auth){
        scrapService.addScrap(auth.name(), postId);
        return "redirect:/board/"+postService.getCategory(postId)+"/post/"+postId;
    }

    @DeleteMapping("/{boardId}/save/{postId}")
    public String deleteScrap(@PathVariable Long postId, Authentication auth){
        scrapService.deleteScrap(auth.name(), postId);
        return "redirect:/board/"+postService.getCategory(postId)+"/post/"+postId;
    }
}
