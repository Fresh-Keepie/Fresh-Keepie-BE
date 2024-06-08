package com.masoongsoong.FreashKeepie.domain.product.service;

import com.masoongsoong.FreashKeepie.domain.product.model.IngredientsDetail;
import com.masoongsoong.FreashKeepie.domain.product.model.dto.IngredientsDetailDto;

public interface DataService {

    //void creteRecipe(Recipe recipe); 여기에 레시피를 붙여야 하나

    void createIngredientsdetail(IngredientsDetail ingredientsdetail);

    void createIngredientsdetail(IngredientsDetailDto ingredientsDetailDto);

     IngredientsDetailDto getIngredientsdetail(int ingredientsDetailId);


    //void createSub(Sub sub);  여기에 찜, 좋아요 넣어야할거같은데

}
