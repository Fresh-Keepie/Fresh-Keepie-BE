package com.masoongsoong.FreashKeepie.domain.mypage;

import com.masoongsoong.FreashKeepie.domain.Post.PostDTO;
import com.masoongsoong.FreashKeepie.domain.Post.PostService;
import com.masoongsoong.FreashKeepie.domain.member.MemberDTO;
import com.masoongsoong.FreashKeepie.domain.member.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageController {
    private final MyPageService myPageService;
    private final PostService postService;


    // 유저 정보 조회
    @GetMapping("/info")
    public ResponseEntity<UserDto> myPage(@RequestParam String userId){

        UserDto userDto = myPageService.myinfo(userId);
        return ResponseEntity.ok(userDto);
    }

    // 회원 정보 수정
    @PutMapping("/edit")
    public ResponseEntity<String> myPageEdit(@RequestParam String userId, @Valid @RequestBody MemberDTO dto, BindingResult bindingResult){
        //Validation
        if(myPageService.editValid(dto, bindingResult, userId).hasErrors()){
            return ResponseEntity.badRequest().body("유효성 검사 실패");
        }

        myPageService.edit(dto, userId);
        return ResponseEntity.ok("회원 정보가 수정되었습니다.");
    }

    // 회원 탈퇴
    @DeleteMapping("/deleteuser")
    public ResponseEntity<String> userDelete(@RequestParam String userId, @Valid @RequestBody MemberDTO dto){

        Boolean deleteSuccess = myPageService.delete(userId, dto.getNowPassword());
        if(deleteSuccess)
            return ResponseEntity.ok("회원 탈퇴가 완료되었습니다.");
        else
            return ResponseEntity.badRequest().body("비밀번호가 올바르지 않습니다.");
    }

    // 스크랩한 게시글 조회
    @GetMapping("/save")
    public ResponseEntity<Page<PostDTO>> myPageScrap(@RequestParam String userId,
                                                     @RequestParam(required = false, defaultValue = "1") int page) {

        Page<PostDTO> postDTOPage = myPageService.myPageScrap(userId, page);
        return ResponseEntity.ok(postDTOPage);
    }

    // 작성한 게시글 조회
    @GetMapping("/post")
    public ResponseEntity<Page<PostDTO>> myPagePost(@RequestParam String userId,
                                                    @RequestParam(required = false, defaultValue = "1") int page) {

        Page<PostDTO> postDTOPage = myPageService.myPagePost(userId, page);
        return ResponseEntity.ok(postDTOPage);
    }

}
