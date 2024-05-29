package com.masoongsoong.FreashKeepie.domain.Member;

import com.masoongsoong.FreashKeepie.domain.Post.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/*@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MemberController {

    private final MemberService memberService;
    private final PostService postService;

    @GetMapping("/{loginID}")
    public String myPage(Authentication auth, Model model){
        Member member =memberService.myinfo(auth.name());
        model.addAttribute("memberDTO", MemberDTO.of(member));
        return "mypage";
    }

    @PatchMapping("/{loginID}")
    public String myPageEdit(@PathVariable String loginID, @Valid @ModelAttribute MemberDTO dto, BindingResult bindingResult,
                             Authentication auth){
        //Validation
        if(memberService.editValid(dto, bindingResult, auth.name()).hasErrors()){
            return "mypage"; //유효성 검사에 대한 결과를 빨간 글씨로.
        }

        memberService.edit(dto, auth.name());
        return "redirect:/myPage/"+loginID;
    }

    @DeleteMapping("/{loginID}")
    public String memberDelete(@ModelAttribute MemberDTO dto, Authentication auth){
        Boolean deleteSuccess = memberService.delete(auth.name(), dto.getNowPassword());
        if(deleteSuccess)
            return "redirect:/login";
        else
            return "mypage"; // 비밀번호가 올바르지 않음을 알려야됨
    }

    @GetMapping("/save/{loginID}")
    public String myPageSave(Authentication auth, Model model){
        model.addAttribute("scrap", postService.findMyPost("post", auth.name()));
        return "mypage/save";
    }

    @GetMapping("/post/{loginID}")
    public String myPagePost(Authentication auth, Model model){
        model.addAttribute("post", postService.findMyPost("post", auth.name()));
        return "mypage/post";
    }
}*/
