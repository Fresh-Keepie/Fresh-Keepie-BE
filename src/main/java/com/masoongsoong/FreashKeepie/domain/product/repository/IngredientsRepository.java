package com.masoongsoong.FreashKeepie.domain.product.repository;

import com.masoongsoong.FreashKeepie.domain.product.model.Ingredients;
import com.masoongsoong.FreashKeepie.domain.product.model.IngredientsDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;



public interface IngredientsRepository extends JpaRepository<Ingredients, Integer> {
    Ingredients[] findByFridgeId(int fridgeId);

    @Query("SELECT COUNT(i.id) FROM Ingredients i WHERE i.ingredientsdetail.id = :ingredientsDetailId AND i.fridge.id = :fridgeId")
    int findCntByIngredientsDetailId(@Param("ingredientsDetailId") int ingredientsDetailId, @Param("fridgeId") int fridgeId);

    @Query("SELECT i.id FROM Ingredients i WHERE i.ingredientsdetail.id = :ingredientsDetailId AND i.fridge.id = :fridgeId")
    Optional<Integer> findByIngredientsDetailIdandFridgeId(@Param("ingredientsDetailId") int ingredientsDetailId, @Param("fridgeId") int fridgeId);
}


