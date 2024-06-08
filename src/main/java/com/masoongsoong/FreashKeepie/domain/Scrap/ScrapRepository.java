package com.masoongsoong.FreashKeepie.domain.Scrap;

import com.masoongsoong.FreashKeepie.domain.member.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScrapRepository extends JpaRepository<Scrap, Long> {
    void deleteByUserAndPostId(User user, Long postId); //아이디와 게시물 id로 스크랩 취소
    Boolean existsByUserAndPostId(User user, Long postId); // 스크랩 했는지 여부
    List<Scrap> findAllByUser(User user); //아이디로 스크랩한 리스트 반환
}
