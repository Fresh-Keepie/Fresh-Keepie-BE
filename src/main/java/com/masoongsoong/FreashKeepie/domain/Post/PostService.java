package com.masoongsoong.FreashKeepie.domain.Post;

import com.masoongsoong.FreashKeepie.domain.Comment.CommentCreateRequest;
import com.masoongsoong.FreashKeepie.domain.Like.LikeService;
import com.masoongsoong.FreashKeepie.domain.Scrap.ScrapService;
import com.masoongsoong.FreashKeepie.domain.member.User;
import com.masoongsoong.FreashKeepie.domain.Scrap.Scrap;
import com.masoongsoong.FreashKeepie.domain.Scrap.ScrapRepository;
import com.masoongsoong.FreashKeepie.domain.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ScrapRepository scrapRepository;

    private final LikeService likeService;
    private final ScrapService scrapService;

    private static final Logger logger = LoggerFactory.getLogger(PostService.class);


    public Page<Post> getPostListByTitle(Board category, PageRequest pageRequest, String keyword) { //제목으로 게시물 검색

        return postRepository.findAllByCategoryAndTitle(category, keyword, pageRequest);
    }

    // 게시글 전체조회
    public Page<PostDTO> getPostList(String boardId, String userId, int page) { // pageRequset는 id내림차순으로 정렬된 것
        Board boardCategory = Board.of(boardId);
        if (boardCategory == null) {
            throw new IllegalArgumentException("카테고리 값이 잘못되었습니다.");
        }

        User user = userRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저 입니다.")
        );

        // 한 페이지당 표시할 게시글 수
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page-1, pageSize, Sort.by("id").descending());

        // 게시글을 저장할 변수
        Page<Post> postPage = postRepository.findAllByCategory(boardCategory, pageable);

        // 카테고리의 게시글을 조회한뒤 형변환
        Page<PostDTO> postDTOPage = postPage.map(post -> {
            Boolean likeCheck = likeService.checkLike(user, post.getId());
            Boolean scrapCheck = scrapService.checkScrap(user, post.getId());
            return new PostDTO(post, likeCheck, scrapCheck);
        });

        return postDTOPage;
    }

    // 게시글 단건조회
    public PostDTO getPost(Long postId, String userId){ //게시물을 가져옴
        User user = userRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저 입니다.")
        );

        Boolean likeCheck = likeService.checkLike(user, postId);
        Boolean scrapCheck = scrapService.checkScrap(user, postId);

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글 입니다.")
        );

        PostDTO postDTO= new PostDTO(post, likeCheck, scrapCheck);

        return postDTO;
    }

    // 게시글 검색
    public Page<PostDTO> postListTitle(String boardId, String userId, String searchWord, int page) {
        Board boardCategory = Board.of(boardId);
        if (boardCategory == null) {
            throw new IllegalArgumentException("카테고리 값이 잘못되었습니다.");
        }

        User user = userRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저 입니다.")
        );

        // 한 페이지당 표시할 게시글 수
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page-1, pageSize, Sort.by("id").descending());

        // 게시글을 저장할 변수
        Page<Post> postPage = postRepository.findByCategoryAndTitleContaining(boardCategory, searchWord, pageable);

        // 카테고리의 게시글을 조회한뒤 형변환
        Page<PostDTO> postDTOPage = postPage.map(post -> {
            Boolean likeCheck = likeService.checkLike(user, post.getId());
            Boolean scrapCheck = scrapService.checkScrap(user, post.getId());
            return new PostDTO(post, likeCheck, scrapCheck);
        });

        return postDTOPage;
    }

    // 게시글 작성
    @Transactional //데베에 여러 작업을 하나의 단위로 처리하겠다
    public String writePost(PostCreateRequest req, Board category){
        Optional<User> optionalUser = userRepository.findByUserId(req.getUserId());

        if(!optionalUser.isPresent()){
            throw new NoSuchElementException("유저가 존재하지 않습니다.");
        }

        User user = optionalUser.get();

        Post post = Post.builder()
                .title(req.getTitle())
                .content(req.getContent())
                .category(category)
                .user(user)
                .likeCnt(0)
                .commentCnt(0)
                .view(0)
                .build();

        postRepository.save(post);

        return "게시글 작성이 완료되었습니다.";
    }

    // 게시글 수정
    @Transactional
    public String editPost(Long postId, PostCreateRequest req){
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글 입니다.")
        );

        User user = userRepository.findByUserId(req.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저 입니다.")
        );

        if(!post.getUser().getId().equals(user.getId())){
            throw new IllegalArgumentException("게시글 작성자만 수정이 가능합니다.");
        }

        post.update(req);

        return "게시글 수정이 완료되었습니다.";
    }

    // 게시글 삭제
    @Transactional
    public String deletePost(Long postId, String userId){
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글 입니다.")
        );

        User user = userRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저 입니다.")
        );

        if(!post.getUser().getId().equals(user.getId())){
            throw new IllegalArgumentException("게시글 작성자만 삭제가 가능합니다.");
        }

        postRepository.deleteById(postId);
        return "게시글이 삭제되었습니다.";
    }

    public String getCategory(Long postId){ //board id 얻기 위함
        Post post=postRepository.findById(postId).get();
        return post.getCategory().toString().toLowerCase();
    }
}
