package com.masoongsoong.FreashKeepie.domain.Like;

import com.masoongsoong.FreashKeepie.domain.member.model.User;
import com.masoongsoong.FreashKeepie.domain.member.repository.UserRepository;
import com.masoongsoong.FreashKeepie.domain.Post.Post;
import com.masoongsoong.FreashKeepie.domain.Post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    // 게시글 좋아요
    @Transactional
    public String addLike(Long postId, String userId){
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글 입니다.")
        );

        User user = userRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저 입니다.")
        );

        if(!checkLike(user, postId)){
            post.likeChange(post.getLikeCnt()+1);

            likeRepository.save(Like.builder()
                    .user(user)
                    .post(post)
                    .build());

            return post.getId() + "번 " + "게시글에 좋아요를 눌렀습니다.";
        } else{
            return "이미 좋아요를 누른 게시글 입니다.";
        }
    }

    // 게시글 좋아요 취소
    @Transactional
    public String deleteLike(Long postId, String userId){
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글 입니다.")
        );

        User user = userRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저 입니다.")
        );

        if(checkLike(user, postId)){
            post.likeChange(post.getLikeCnt() - 1);
            likeRepository.deleteByUserAndPostId(user, postId);

            return post.getId() + "번 " + "게시글에 좋아요를 취소했습니다.";
        } else{
            return "좋아요를 누르지 않은 게시글 입니다.";
        }
    }

    // 좋아요 여부 true/false 반환
    public boolean checkLike(User user, Long postId){
        Boolean result = likeRepository.existsByUserAndPostId(user, postId);
        return result;
    }
}
