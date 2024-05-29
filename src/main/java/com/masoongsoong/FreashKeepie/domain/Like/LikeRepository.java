package com.masoongsoong.FreashKeepie.domain.Like;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    void deleteByLoginIDAndPostId(String loginID, Long postId); //아이디와 게시물 id로 좋아요 취소  (Jpa 매서드 규칙을 따르고 있기 때문에 자동으로 loginID, postId에 해당하는 레코드를 delete함)
    Boolean existsByLoginIDAndPostId(String loginID, Long postId); // 좋아요 했는지 여부
    List<Like> findAllByLoginID(String loginID);
}
