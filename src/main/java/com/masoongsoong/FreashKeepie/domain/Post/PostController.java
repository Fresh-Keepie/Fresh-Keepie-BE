package com.masoongsoong.FreashKeepie.domain.Post;

import com.masoongsoong.FreashKeepie.domain.Comment.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class PostController { //웹 띄우기

    private final PostService postService;
    private final CommentService commentService;


    // 게시글 전체조회
    @GetMapping("/{boardId}/list")
    public ResponseEntity<Page<PostDTO>> postListPage(@PathVariable("boardId") String boardId, @RequestParam String userId,
                               @RequestParam(required = false, defaultValue = "1") int page) {
        Page<PostDTO> postDTOPage = postService.getPostList(boardId, userId, page);
        return ResponseEntity.ok(postDTOPage);
    }

    // 게시글 단건조회
    @GetMapping("/{boardId}/post/{postId}")
    public ResponseEntity<PostDTO> postDetailPage(@PathVariable Long postId, @RequestParam String userId){

        PostDTO postDTO = postService.getPost(postId, userId);
        return ResponseEntity.ok(postDTO);
    }

    // 게시글 검색
    @GetMapping("/{boardId}/search/{searchWord}")
    public ResponseEntity<Page<PostDTO>> postListTitle(@PathVariable("boardId") String boardId, @RequestParam String userId,
                                @PathVariable String searchWord,
                                @RequestParam(required = false, defaultValue = "1") int page) {

        Page<PostDTO> postDTOPage = postService.postListTitle(boardId, userId, searchWord, page);
        return ResponseEntity.ok(postDTOPage);
    }

    //글쓰는 화면 띄우기
    @GetMapping("/{boardId}/write")
    public String postWritePage(@PathVariable String boardId, Model model) {
        Board boardCategory = Board.of(boardId);
        if (boardCategory == null) {
            return "redirect:/board/" + boardId + "/list";
        }

        model.addAttribute("boardId", boardId);
        model.addAttribute("postCreateRequest", new PostCreateRequest());
        return "board/write"; //글 작성 페이지
    }


    // 게시글 작성
    @PostMapping("/{boardId}/post")
    ResponseEntity<String> postWrite(@RequestBody PostCreateRequest request, @PathVariable String boardId){
        Board boardCategory = Board.of(boardId);
        if(boardCategory == null){
            throw new IllegalArgumentException("카테고리가 잘못입력되었습니다.");
        }

        String result = postService.writePost(request, boardCategory);
        return ResponseEntity.ok(result);
    }

    // 게시글 수정
    @PutMapping("/{boardId}/post/{postId}")
    public ResponseEntity<String> postEdit(@PathVariable Long postId, @RequestBody PostCreateRequest req){

        String result = postService.editPost(postId, req);
        return ResponseEntity.ok(result);
    }

    // 게시글 삭제
    @DeleteMapping("/{boardId}/post/{postId}")
    public ResponseEntity<String> postDelete(@PathVariable Long postId, @RequestParam String userId){

        String result = postService.deletePost(postId, userId);
        return ResponseEntity.ok(result);
    }
}
