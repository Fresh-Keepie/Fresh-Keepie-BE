package com.masoongsoong.FreashKeepie.domain.Scrap;

import com.masoongsoong.FreashKeepie.domain.Member.Member;
import com.masoongsoong.FreashKeepie.domain.Member.MemberRepository;
import com.masoongsoong.FreashKeepie.domain.Post.Post;
import com.masoongsoong.FreashKeepie.domain.Post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScrapService {

    private final ScrapRepository scrapRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void addScrap(String loginID, Long postId){
        Post post=postRepository.findById(postId).get();
        Member member=memberRepository.findByloginID(loginID).get();

        scrapRepository.save(Scrap.builder()
                .member(member)
                .post(post)
                .build());
    }

    @Transactional
    public void deleteScrap(String loginID, Long postId){
        Post post=postRepository.findById(postId).get();
        Member member=memberRepository.findByloginID(loginID).get();

        scrapRepository.deleteByloginIDAndPostId(loginID, postId);
    }

    public Boolean checkScrap(String loginID, Long postId){
        return scrapRepository.existsByloginIDAndPostId(loginID, postId);
    }
}
