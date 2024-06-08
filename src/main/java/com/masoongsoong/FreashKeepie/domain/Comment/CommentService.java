package com.masoongsoong.FreashKeepie.domain.Comment;

import com.masoongsoong.FreashKeepie.domain.member.model.User;
import com.masoongsoong.FreashKeepie.domain.member.repository.UserRepository;
import com.masoongsoong.FreashKeepie.domain.Post.Post;
import com.masoongsoong.FreashKeepie.domain.Post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // 댓글 작성
    @Transactional
    public String writeComment(Long postId, CommentCreateRequest req, String userId){
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글 입니다.")
        );

        User user = userRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저 입니다.")
        );

        post.commentChange(post.getCommentCnt()+1);
        commentRepository.save(req.toEntity(post, user));

        return "댓글이 작성되었습니다.";
    }

     public List<Comment> findAll(Long postId){
         return commentRepository.findAllByPostId(postId);
     }

    // 댓글 수정
    @Transactional
    public String editComment(Long commentId, CommentCreateRequest req, String userId){
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 댓글 입니다.")
        );

        User user = userRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저 입니다.")
        );

        if(!comment.getUser().getId().equals(user.getId())){
            throw new IllegalArgumentException("댓글 작성자만 수정이 가능합니다.");
        }

        comment.updateComment(req.getContent());
        return "댓글이 수정되었습니다.";
    }

    // 댓글 삭제
    @Transactional
    public String deleteComment(Long commentId, String userId){
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 댓글 입니다.")
        );

        User user = userRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저 입니다.")
        );

        if(!comment.getUser().getId().equals(user.getId())){
            throw new IllegalArgumentException("댓글 작성자만 삭제가 가능합니다.");
        }

        commentRepository.delete(comment);

        // 삭제 성공시 댓글 cnt --
        Post post=comment.getPost();
        post.commentChange(post.getCommentCnt()-1); //댓글 개수 -1

        return "댓글이 삭제되었습니다.";
    }
}
