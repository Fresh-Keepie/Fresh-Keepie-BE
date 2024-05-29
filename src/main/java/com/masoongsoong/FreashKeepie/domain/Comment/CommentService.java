package com.masoongsoong.FreashKeepie.domain.Comment;

import com.masoongsoong.FreashKeepie.domain.Member.Member;
import com.masoongsoong.FreashKeepie.domain.Member.MemberRepository;
import com.masoongsoong.FreashKeepie.domain.Post.Post;
import com.masoongsoong.FreashKeepie.domain.Post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public void writeComment(Long postId, CommentCreateRequest req, String loginID){
        Post post=postRepository.findById(postId).get();
        Member member=memberRepository.findByloginID(loginID).get();
        post.commentChange(post.getCommentCnt()+1);
        commentRepository.save(req.toEntity(post, member));
    }

    public List<Comment> findAll(Long postId){
        return commentRepository.findAllByPostId(postId);
    }

    @Transactional
    public Long editComment(Long commentId, String newContent, String loginID){
        Optional<Comment> optComment=commentRepository.findById(commentId);
        Optional<Member> optMember=memberRepository.findByloginID(loginID);
        if(optComment.isEmpty() || optMember.isEmpty() || !optComment.get().getMember().equals(optMember.get())){
            return null;
        }

        Comment comment=optComment.get();
        comment.updateComment(newContent);
        return comment.getPost().getId(); //게시글 id가 return됨!
    }

    public Long deleteComment(Long commentId, String loginID){
        Optional<Comment> optComment=commentRepository.findById(commentId);
        Optional<Member> optMember=memberRepository.findByloginID(loginID);
        if(optComment.isEmpty() || optMember.isEmpty()){
            return null;
        }

        Post post=optComment.get().getPost();
        post.commentChange(post.getCommentCnt()-1); //댓글 개수 -1
        commentRepository.delete(optComment.get());
        return post.getId();
    }
}
