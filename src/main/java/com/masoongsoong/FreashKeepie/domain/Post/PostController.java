package com.masoongsoong.FreashKeepie.domain.Post;

import com.masoongsoong.FreashKeepie.domain.Comment.CommentCreateRequest;
import com.masoongsoong.FreashKeepie.domain.Comment.CommentService;
import com.masoongsoong.FreashKeepie.domain.Like.LikeService;
import com.masoongsoong.FreashKeepie.domain.Scrap.ScrapService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class PostController { //웹 띄우기

    private final PostService postService;
    private final CommentService commentService;
    private final ScrapService scrapService;
    private final LikeService likeService;

    @GetMapping("/{boardId}/list")
    public String postListPage(@PathVariable("boardId") String boardId, Model model,
                               @RequestParam(required =false, defaultValue = "1") int page){
        Board boardCategory=Board.of(boardId);
        if(boardCategory==null){
            //return "redirect:/error/404"; // 적절한 오류 페이지로 리다이렉트
            return "redirect:/board/"+boardId+"/list";
        }

        //id로만 정렬
        PageRequest pageRequest=PageRequest.of(page-1, 10, Sort.by("id").descending());

        model.addAttribute("boardId", boardId);
        model.addAttribute("post", postService.getPostList(boardCategory, pageRequest));
        return "list"; //board/list에서 바꿈
    }
    @GetMapping("/{boardId}/search/{searchWord}")
    public String postListTitle(@PathVariable String boardId, Model model,
                                @PathVariable String searchWord,
                                @RequestParam(required = false, defaultValue = "1") int page){
        Board boardCategory = Board.of(boardId);
        if(boardCategory==null){
            return "redirect:/board/"+boardId+"/list";
        }

        //id로만 정렬
        PageRequest pageRequest=PageRequest.of(page-1, 10, Sort.by("id").descending());

        model.addAttribute("boardId", boardId);
        model.addAttribute("post", postService.getPostListByTitle(boardCategory, pageRequest, searchWord));
        model.addAttribute("postSearchRequest", new PostResearchRequest(searchWord));
        return "board/list"; //그냥 list 정렬이랑 똑같이 해도 되나?
    }

    //글쓰는 화면 띄우기
    @GetMapping("/{boardId}/write")
    public String postWritePage(@PathVariable String boardId, Model model){
        Board boardCategory=Board.of(boardId);
        if(boardCategory==null){
            return "redirect:/board/"+boardId+"/list";
        }

        model.addAttribute("boardId", boardId);
        model.addAttribute("postCreateRequest", new PostCreateRequest());
        return "board/write"; //글 작성 페이지
    }

    @PostMapping("/{boardId}/post") //api 겹치면 안돼서 API 명세서랑 다르게 함
    public String postWrite(@PathVariable String boardId, @ModelAttribute PostCreateRequest req,
                            Authentication auth) throws IOException{
        Board boardCategory = Board.of(boardId);
        if(boardCategory==null){
            return "redirect:/board/"+boardId+"/list";
        }

        Long savePostId= postService.writePost(req, boardCategory, auth.name());
        return "redirect:/board/"+boardId+"/post/"+savePostId;
    }

    @GetMapping("/{boardId}/post/{postId}")
    public String postDetailPage(@PathVariable String boardId, @PathVariable Long postId, Model model,
                                 Authentication auth){
        if(auth!=null){ //사용자가 인증되었을 때 각 사용자와 관련된 데이터들
            model.addAttribute("loginMemloginID", auth.name());
            model.addAttribute("likeCheck", likeService.checkLike(auth.name(), postId)); //게시물에 좋아요 체크했는지 여부
            model.addAttribute("scrapCheck", scrapService.checkScrap(auth.name(), postId)); //게시물에 스크랩 체크했는지 여부
        }
        PostDTO postDTO= postService.getPost(postId, boardId);

        //id에 해당하는 게시글이 없거나 카테고리가 일치하지 않는 경우
        if (postDTO==null){
            return "redirect:/board/"+boardId+"/list";
        }

        model.addAttribute("postDTO",postDTO);
        model.addAttribute("category", boardId); //게시물 상세보기에서 필요한가??

        model.addAttribute("commentCreateRequest", new CommentCreateRequest()); // 댓글 쓰는 입력칸
        model.addAttribute("commentList", commentService.findAll(postId)); //이 게시물에 있는 댓글들 가져오기
        return "post/detail";
    }

    @PatchMapping("/{boardId}/post/{postId}")
    public String postEdit(@PathVariable String boardId, @PathVariable Long postId,
                           @ModelAttribute PostDTO dto) throws IOException{
        Long editPostId= postService.editPost(postId, boardId, dto);

        if(editPostId==null){
            return "redirect:/board/"+boardId+"/list";
        }else {
            return "redirect:/board/"+postService.getCategory(postId)+"/post/"+editPostId;
        }
    }

    @DeleteMapping("/{boardId}/post/{postId}")
    public void postDelete(@PathVariable String boardId, @PathVariable Long postId) throws IOException{
        Long deletedPostId= postService.deletePost(postId, boardId);
    }
}
