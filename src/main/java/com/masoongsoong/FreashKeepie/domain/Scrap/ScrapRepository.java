package com.masoongsoong.FreashKeepie.domain.Scrap;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScrapRepository extends JpaRepository<Scrap, Long> {
    void deleteByloginIDAndPostId(String loginID, Long postId); //아이디와 게시물 id로 스크랩 취소
    Boolean existsByloginIDAndPostId(String loginID, Long postId); // 스크랩 했는지 여부
    List<Scrap> findAllByloginID(String loginID); //아이디로 스크랩한 리스트 반환
}
