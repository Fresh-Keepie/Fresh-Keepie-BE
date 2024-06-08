package com.masoongsoong.FreashKeepie.domain.Scrap;

import com.masoongsoong.FreashKeepie.domain.member.User;
import com.masoongsoong.FreashKeepie.domain.Post.Post;
import com.masoongsoong.FreashKeepie.domain.member.User;
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

    @ManyToOne(fetch= LAZY)
    @JoinColumn(name="user_id", updatable=false)
    private User user;

    @ManyToOne(fetch=LAZY)
    @JoinColumn(name="post_id")
    private Post post; //좋아요가 추가된 게시글
}
