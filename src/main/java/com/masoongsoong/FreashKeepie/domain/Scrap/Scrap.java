package com.masoongsoong.FreashKeepie.domain.Scrap;

import com.masoongsoong.FreashKeepie.domain.Member.Member;
import com.masoongsoong.FreashKeepie.domain.Post.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.util.Lazy;

import static jakarta.persistence.FetchType.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name="scrap")
public class Scrap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loginID; //오류가 만들래서 만듦

    @ManyToOne(fetch= LAZY)
    private Member member; //내가 스크랩한 글에서 필요

    @ManyToOne(fetch=LAZY)
    private Post post; //좋아요가 추가된 게시글
}
