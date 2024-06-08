package com.masoongsoong.FreashKeepie.domain.product.service;

import com.masoongsoong.FreashKeepie.domain.member.model.User;
import com.masoongsoong.FreashKeepie.domain.member.repository.UserRepository;
import com.masoongsoong.FreashKeepie.domain.product.model.IngredientsDetail;
import com.masoongsoong.FreashKeepie.domain.product.model.dto.IngredientsDetailDto;
import com.masoongsoong.FreashKeepie.domain.product.repository.IngredientsDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataServiceImpl implements DataService {
    //    @Autowired
//    private RecipesRepository recipeRepository;
    @Autowired
    private IngredientsDetailRepository ingredientsdetailRepository;

    @Autowired
    private UserRepository userRepository;


//    @Autowired
//    private SubRepository subRepository;

// 회원이 작성한 레시피
//    @Override
//    public void creteRecipe(Recipe recipe) {
//        recipeRepository.save(recipe);
//    }


    @Override
    public void createIngredientsdetail(IngredientsDetail ingredientsdetail) {

    }

    @Override
    public void createIngredientsdetail(IngredientsDetailDto ingredientsDetailDto) {
        User user = userRepository.findByUserId(ingredientsDetailDto.getUserId()).orElseThrow(() -> new RuntimeException("올바르지 않은 user id"));

        if (user != null && user.getId() == 0) {
            userRepository.save(user);
        }

        IngredientsDetail ingredientsDetail = new IngredientsDetail(user, ingredientsDetailDto.getProductName(), ingredientsDetailDto.getAmount());
        ingredientsdetailRepository.save(ingredientsDetail);
    }

    @Override
    public IngredientsDetailDto getIngredientsdetail(int detailId) {
        IngredientsDetail ingredientsDetail = ingredientsdetailRepository.findById(detailId)
                .orElseThrow(() -> new RuntimeException("상품 세부 정보를 찾을 수 없습니다. ID: " + detailId));

        // 여기에서 IngredientsDetail 엔티티를 DTO로 변환하는 로직을 구현합니다.
        // 예시로, IngredientsDetailDto 생성자를 사용하여 DTO 인스턴스를 생성합니다.
        IngredientsDetailDto ingredientsDetailDto = new IngredientsDetailDto();
        ingredientsDetailDto.setUserId(String.valueOf(ingredientsDetail.getUser()));
        ingredientsDetailDto.setProductName(ingredientsDetail.getProductName());
        ingredientsDetailDto.setAmount(ingredientsDetail.getAmount());

        return ingredientsDetailDto;
    }
}

// 회원이 좋아요, 구독한거
//    @Override
//    public void createSub(Sub sub) {
//        subRepository.save(sub);
//    }

