package com.masoongsoong.FreashKeepie.domain.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostId(Long postId);
    List<Comment> findAllByLoginID(String loginID); //탈퇴할 때 썼던 댓글 다 삭제하기 위함
}
