package com.masoongsoong.FreashKeepie.domain.mypage;

import com.masoongsoong.FreashKeepie.domain.Comment.CommentRepository;
import com.masoongsoong.FreashKeepie.domain.Like.LikeRepository;
import com.masoongsoong.FreashKeepie.domain.Like.LikeService;
import com.masoongsoong.FreashKeepie.domain.Post.Post;
import com.masoongsoong.FreashKeepie.domain.Post.PostDTO;
import com.masoongsoong.FreashKeepie.domain.Post.PostRepository;
import com.masoongsoong.FreashKeepie.domain.Scrap.ScrapRepository;
import com.masoongsoong.FreashKeepie.domain.Scrap.ScrapService;
import com.masoongsoong.FreashKeepie.domain.member.MemberDTO;
import com.masoongsoong.FreashKeepie.domain.member.model.User;
import com.masoongsoong.FreashKeepie.domain.member.repository.UserRepository;
import com.masoongsoong.FreashKeepie.domain.member.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;
    private final ScrapRepository scrapRepository;
    private final BCryptPasswordEncoder encoder;
    private final PostRepository postRepository;
    private final LikeService likeService;
    private final ScrapService scrapService;

    private final PasswordEncoder passwordEncoder;

    // 유저 정보 조회
    public UserDto myinfo(String userId) {
        User user = userRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저 입니다.")
        );

        UserDto userDto = new UserDto(user);
        return userDto;
    }

    public BindingResult editValid(MemberDTO dto, BindingResult bindingResult, String userId) {

        User user = userRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저입니다.")
        );

        //bindingResult : 사용자 입력값과 데베의 데이터 비교하여 유효성 검사

        // 유효성 검사 : 현재 비밀번호
        if(dto.getNowPassword() == null || dto.getNowPassword().isEmpty()){
            bindingResult.addError(new FieldError("dto", "nowPassword", "현재 비밀번호가 비어있습니다."));
        } else if(!encoder.matches(dto.getNowPassword(), user.getPassword())){
            bindingResult.addError(new FieldError("dto", "nowPassword", "현재 비밀번호가 틀렸습니다."));
        }

        // 유효성 검사 : 새로운 비밀번호
        if(dto.getNewPassword() != null && !dto.getNewPassword().isEmpty()){
            if(!dto.getNewPassword().equals(dto.getNewPasswordCheck())){
                bindingResult.addError(new FieldError("dto", "newPasswordCheck", "비밀번호가 일치하지 않습니다."));
            }
        }

        // 유효성 검사 : 닉네임
        if(dto.getNickname() != null && !dto.getNickname().isEmpty()){
            if(dto.getNickname().length() > 10){
                bindingResult.addError(new FieldError("dto", "nickname", "닉네임이 10자가 넘습니다."));
            } else if(!dto.getNickname().equals(user.getNickname()) && userRepository.existsByNickname(dto.getNickname())){
                bindingResult.addError(new FieldError("dto", "nickname", "닉네임이 중복됩니다."));
            }
        }

        return bindingResult; //유효성 검사가 실패했음을 나타내는데 사용 -> 폼을 다시 표시하거나 에러 메시지 보여줄 때
    }

    @Transactional
    public void edit(MemberDTO dto, String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException(userId));

        // 닉네임을 입력했을시
        if(dto.getNickname() != null && !dto.getNickname().isEmpty()){
            user.setNickname(dto.getNickname());
        }

        // 비밀번호를 입력했을시
        if(dto.getNewPassword() != null && !dto.getNewPassword().isEmpty()){
            user.setPassword(encoder.encode(dto.getNewPassword()));
        }

        userRepository.save(user);
    }

    @Transactional
    public Boolean delete(String userId, String nowPassword) {
        User user = userRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저 입니다.")
        );

        if (encoder.matches(nowPassword, user.getPassword())) { //비번이 일치하면 좋아요 수 -1, 썼던 댓글 지우기
            likeRepository.deleteAll(user.getLikes());
            commentRepository.deleteAll(user.getComments());
            scrapRepository.deleteAll(user.getScraps());
            postRepository.deleteAll(user.getPost());
            userRepository.delete(user);
            return true;
        } else {
            return false;
        }
    }

    // 스크랩한 게시글 조회
    public Page<PostDTO> myPageScrap(String userId, int page) {
        User user = userRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저 입니다.")
        );

        // 한 페이지당 표시할 게시글 수
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page-1, pageSize, Sort.by("id").descending());

        // 게시글을 저장할 변수
        Page<Post> postPage = postRepository.findScrappedPostsByUserId(user.getId(), pageable);

        // 카테고리의 게시글을 조회한뒤 형변환
        Page<PostDTO> postDTOPage = postPage.map(post -> {
            Boolean likeCheck = likeService.checkLike(user, post.getId());
            Boolean scrapCheck = scrapService.checkScrap(user, post.getId());
            return new PostDTO(post, likeCheck, scrapCheck);
        });

        return postDTOPage;
    }


    // 작성한 게시글 조회
    public Page<PostDTO> myPagePost(String userId, int page) {
        User user = userRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저 입니다.")
        );

        // 한 페이지당 표시할 게시글 수
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page-1, pageSize, Sort.by("id").descending());

        // 게시글을 저장할 변수
        Page<Post> postPage = postRepository.findPostsByUserId(user.getId(), pageable);

        // 카테고리의 게시글을 조회한뒤 형변환
        Page<PostDTO> postDTOPage = postPage.map(post -> {
            Boolean likeCheck = likeService.checkLike(user, post.getId());
            Boolean scrapCheck = scrapService.checkScrap(user, post.getId());
            return new PostDTO(post, likeCheck, scrapCheck);
        });

        return postDTOPage;
    }
}
