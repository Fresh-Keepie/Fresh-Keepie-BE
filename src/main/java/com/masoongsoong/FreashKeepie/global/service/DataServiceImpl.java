package com.masoongsoong.FreashKeepie.global.service;

import com.masoongsoong.FreashKeepie.domain.product.model.IngredientsDetail;
import com.masoongsoong.FreashKeepie.domain.product.repository.IngredientsDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataServiceImpl implements DataService {
//    @Autowired
//    private RecipesRepository recipeRepository;
    @Autowired
    private IngredientsDetailRepository ingredientsdetailRepository;

//    @Autowired
//    private SubRepository subRepository;

// 회원이 작성한 레시피
//    @Override
//    public void creteRecipe(Recipe recipe) {
//        recipeRepository.save(recipe);
//    }

    @Override
    public void createIngredientsdetail(IngredientsDetail ingredientsdetail) {
        ingredientsdetailRepository.save(ingredientsdetail);
    }
}

// 회원이 좋아요, 구독한거
//    @Override
//    public void createSub(Sub sub) {
//        subRepository.save(sub);
//    }

