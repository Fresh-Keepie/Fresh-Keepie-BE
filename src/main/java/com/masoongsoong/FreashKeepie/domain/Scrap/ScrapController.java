package com.masoongsoong.FreashKeepie.domain.Scrap;

import com.masoongsoong.FreashKeepie.domain.Post.PostService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class ScrapController {

    private final ScrapService scrapService;
    private final PostService postService;

    // 스크랩 추가
    @PostMapping("/{boardId}/save/{postId}") //url로 들어온
    public ResponseEntity<String> addScrap(@PathVariable Long postId, @RequestParam String userId){
        String result = scrapService.addScrap(userId, postId);
        return ResponseEntity.ok(result);
    }

    // 스크랩 제거
    @DeleteMapping("/{boardId}/save/{postId}")
    public ResponseEntity<String> deleteScrap(@PathVariable Long postId, @RequestParam String userId){
        String result = scrapService.deleteScrap(userId, postId);
        return ResponseEntity.ok(result);
    }
}
