package com.masoongsoong.FreashKeepie.domain.Post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByCategory(Board category, PageRequest pageRequest); //해당 카테고리에 있는 게시글 페이지에 맞게 조회
    Page<Post> findAllByCategoryAndTitle(Board category, String title, PageRequest pageRequest); //제목으로 게시글 검색
    List<Post> findAllByLoginID(String loginID); //멤버의 마이 페이지에서 내가 작성한 글 조회 시 사용
}
