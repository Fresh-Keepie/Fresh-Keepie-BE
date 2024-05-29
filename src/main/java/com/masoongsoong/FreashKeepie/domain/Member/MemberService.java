package com.masoongsoong.FreashKeepie.domain.Member;

import com.masoongsoong.FreashKeepie.domain.Comment.Comment;
import com.masoongsoong.FreashKeepie.domain.Comment.CommentRepository;
import com.masoongsoong.FreashKeepie.domain.Comment.CommentService;
import com.masoongsoong.FreashKeepie.domain.Like.Like;
import com.masoongsoong.FreashKeepie.domain.Like.LikeRepository;
import com.masoongsoong.FreashKeepie.domain.Post.PostRepository;
import com.masoongsoong.FreashKeepie.domain.Scrap.ScrapRepository;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

/*@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;
    private final BCryptPasswordEncoder encoder;

    public Member myinfo(String loginID){
        return memberRepository.findByloginID(loginID).get();
    }

    public BindingResult editValid(MemberDTO dto, BindingResult bindingResult, String loginID){

        Member member=memberRepository.findByloginID(loginID).get();

        //bindingResult : 사용자 입력값과 데베의 데이터 비교하여 유효성 검사
        if(dto.getNowPassword().isEmpty()) {
            bindingResult.addError(new FieldError("dto", "nowPassword", "현재 비밀번호가 비어있습니다."));
        }else if(!encoder.matches(dto.getNowPassword(), member.getPassword())){
            bindingResult.addError(new FieldError("dto", "nowPassword", "현재 비밀번호가 틀렸습니다."));
        }

        if(!dto.getNewPassword().equals((dto.getNewPasswordCheck()))){
            bindingResult.addError(new FieldError("dto", "newPasswordCheck", "비밀번호가 일치하지 않습니다."));
        }

        if(dto.getNickname().isEmpty()){
            bindingResult.addError(new FieldError("dto", "nickname", "닉네임이 비어있습니다."));
        }else if(dto.getNickname().length()>10){
            bindingResult.addError(new FieldError("dto", "nickname", "닉네임이 10자가 넘습니다."));
        }else if(!dto.getNickname().equals(member.getNickname()) && memberRepository.existsByNickname(dto.getNickname())){
            bindingResult.addError(new FieldError("dto", "nickname", "닉네임이 중복됩니다."));
        }

        return bindingResult; //유효성 검사가 실패했음을 나타내는데 사용 -> 폼을 다시 표시하거나 에러 메시지 보여줄 때
    }

    @Transactional
    public void edit(MemberDTO dto, String loginID){
        Member member = memberRepository.findByloginID(loginID)
                .orElseThrow(() -> new UsernameNotFoundException(loginID));

        if(dto.getNewPassword().equals("")){ //처음에 회원가입했을 땐 새 비밀번호 없음
            member.edit(member.getPassword(), member.getNickname());
        } else{
            member.edit(encoder.encode(dto.getNewPassword()), dto.getNickname()); //새로운 비밀번호를 암호화한 후 데베에 넣음
        }
    }

    @Transactional
    public Boolean delete(String loginID, String nowPassword){
        Member member=memberRepository.findByloginID(loginID).get();

        if(encoder.matches(nowPassword, member.getPassword())){ //비번이 일치하면 좋아요 수 -1, 썼던 댓글 지우기
            List<Like> likes=likeRepository.findAllByLoginID(loginID);
            for(Like like:likes){
                like.getPost().likeChange(like.getPost().getLikeCnt()-1);
            }

            List<Comment> comments=commentRepository.findAllByLoginID(loginID);
            for(Comment comment : comments){
                comment.getPost().commentChange(comment.getPost().getCommentCnt()-1);
            }
            memberRepository.delete(member);
            return true;
        }else{
            return false;
        }
    }

}*/
