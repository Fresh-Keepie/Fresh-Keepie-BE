package com.masoongsoong.FreashKeepie.domain.Post;

import com.masoongsoong.FreashKeepie.domain.Comment.CommentRepository;
import com.masoongsoong.FreashKeepie.domain.Member.Member;
import com.masoongsoong.FreashKeepie.domain.Member.MemberRepository;
import com.masoongsoong.FreashKeepie.domain.Scrap.Scrap;
import com.masoongsoong.FreashKeepie.domain.Scrap.ScrapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final ScrapRepository scrapRepository;

    public Page<Post> getPostList(Board category, PageRequest pageRequest) { // pageRequset는 id내림차순으로 정렬된 것

        return postRepository.findAllByCategory(category, pageRequest);
    }

    public Page<Post> getPostListByTitle(Board category, PageRequest pageRequest, String keyword) { //제목으로 게시물 검색

        return postRepository.findAllByCategoryAndTitle(category, keyword, pageRequest);
    }

    public PostDTO getPost(Long postId, String category){ //게시물을 가져옴
        Optional<Post> optPost=postRepository.findById(postId);

        //id에 해당하는 게시글이 없거나 카테고리가 일치하지 않을 때
        if(optPost.isEmpty() || !optPost.get().getCategory().toString().equals(category))
            return null;

        return PostDTO.of(optPost.get());
    }

    @Transactional //데베에 여러 작업을 하나의 단위로 처리하겠다
    // Authentication auth 파라미터는 빼고 썼음
    public Long writePost(PostCreateRequest req, Board category, String loginID) throws IOException{
        Member member=memberRepository.findByloginID(loginID).get(); //해당 loginID가 없을경우 Optional 객체에 대해 NoSuchElementException을 방지하여 .get()을 씀

        Post savePost=postRepository.save(req.toEntity(category, member));
        return savePost.getId();
    }

    @Transactional
    public Long editPost(Long postId, String category, PostDTO dto){
        Optional <Post> optPost = postRepository.findById(postId);

        if(optPost.isEmpty() || !optPost.get().getCategory().toString().equals(category)){
            return null;
        }
        Post post=optPost.get();
        post.update(dto);

        return post.getId();
    }

    public Long deletePost(Long postId, String category){
        Optional<Post> optPost=postRepository.findById(postId);

        //id 해당 게시글 없거나 카테고리 일치하지 않을 때
        if(optPost.isEmpty() || !optPost.get().getCategory().toString().equals(category)){
            return null;
        }

        //이미지 지우기 코드 생략

        postRepository.deleteById(postId);
        return postId;
    }

    public String getCategory(Long postId){ //board id 얻기 위함
        Post post=postRepository.findById(postId).get();
        return post.getCategory().toString().toLowerCase();
    }

    public List<Post> findMyPost(String category, String loginID){
        if(category.equals("post"))
            return postRepository.findAllByLoginID(loginID);
        else if(category.equals("scrap")) {
            List<Scrap> scraps = scrapRepository.findAllByloginID(loginID);
            List<Post> posts=new ArrayList<>();

            for(Scrap scrap : scraps){
                posts.add(scrap.getPost());
            }
            return posts;
        }

        return null;
    }

}
