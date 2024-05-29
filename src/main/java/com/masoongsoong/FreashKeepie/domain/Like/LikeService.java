package com.masoongsoong.FreashKeepie.domain.Like;

import com.masoongsoong.FreashKeepie.domain.Member.Member;
import com.masoongsoong.FreashKeepie.domain.Member.MemberRepository;
import com.masoongsoong.FreashKeepie.domain.Post.Post;
import com.masoongsoong.FreashKeepie.domain.Post.PostRepository;
import com.masoongsoong.FreashKeepie.domain.Post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    @Transactional
    public void addLike(String loginID, Long postId){
        Member member=memberRepository.findByloginID(loginID).get();
        Post post=postRepository.findById(postId).get();

        post.likeChange(post.getLikeCnt()+1);

        likeRepository.save(Like.builder()
                .member(member)
                .post(post)
                .build());
    }

    @Transactional
    public void deleteLike(String loginID, Long postId){
        Member member =memberRepository.findByloginID(loginID).get();
        Post post=postRepository.findById(postId).get();

        post.likeChange(post.getLikeCnt() - 1);

        likeRepository.deleteByLoginIDAndPostId(loginID, postId);
    }

    public boolean checkLike(String loginID, Long postId){
        return likeRepository.existsByLoginIDAndPostId(loginID, postId);
    }
}
