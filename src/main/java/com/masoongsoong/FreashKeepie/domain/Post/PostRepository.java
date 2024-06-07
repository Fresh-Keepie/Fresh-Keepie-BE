package com.masoongsoong.FreashKeepie.domain.Post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByCategory(Board category, Pageable pageable); //해당 카테고리에 있는 게시글 페이지에 맞게 조회

    // 제목 기반 검색
    Page<Post> findByCategoryAndTitleContaining(Board category, String title, Pageable pageable);
    Page<Post> findAllByCategoryAndTitle(Board category, String title, PageRequest pageRequest); //제목으로 게시글 검색
    // List<Post> findAllByuserId(String userId); //멤버의 마이 페이지에서 내가 작성한 글 조회 시 사용

    @Query("SELECT p FROM Post p WHERE p.user.id = :userId")
    List<Post> findAllByUserId(@Param("userId") String userId);

    @Query("SELECT p FROM Post p JOIN Scrap s ON p.id = s.post.id WHERE s.user.id = :userId")
    Page<Post> findScrappedPostsByUserId(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT p FROM Post p WHERE p.user.id = :userId")
    Page<Post> findPostsByUserId(@Param("userId") Long userId, Pageable pageable);
}
