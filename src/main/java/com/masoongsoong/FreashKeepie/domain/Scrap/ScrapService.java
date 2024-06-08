package com.masoongsoong.FreashKeepie.domain.Scrap;

import com.masoongsoong.FreashKeepie.domain.member.User;
import com.masoongsoong.FreashKeepie.domain.Post.Post;
import com.masoongsoong.FreashKeepie.domain.Post.PostRepository;
import com.masoongsoong.FreashKeepie.domain.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScrapService {

    private final ScrapRepository scrapRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // 스크랩 추가
    @Transactional
    public String addScrap(String userId, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글 입니다.")
        );

        User user = userRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저 입니다.")
        );

        if (!checkScrap(user, postId)) {
            scrapRepository.save(Scrap.builder()
                    .user(user)
                    .post(post)
                    .build());
            return post.getId() + "번 " + "게시글을 스크랩했습니다.";
        } else{
            return "이미 스크랩을 완료한 게시글 입니다.";
        }
    }

    // 스크랩 제거
    @Transactional
    public String deleteScrap(String userId, Long postId){
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글 입니다.")
        );

        User user = userRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저 입니다.")
        );

        if (checkScrap(user, postId)){
            scrapRepository.deleteByUserAndPostId(user, postId);
            return post.getId() + "번 " + "게시글의 스크랩을 취소했습니다.";
        } else{
            return "스크랩이 되어있지 않은 게시글 입니다.";
        }
    }

    // 스크랩 여부 true/false 반환
    public Boolean checkScrap(User user, Long postId){
        return scrapRepository.existsByUserAndPostId(user, postId);
    }
}
