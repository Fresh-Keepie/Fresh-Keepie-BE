package com.masoongsoong.FreashKeepie.domain.product.repository;

import com.masoongsoong.FreashKeepie.domain.product.model.IngredientsDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IngredientsDetailRepository extends JpaRepository<IngredientsDetail, Integer> {
//    @Query("SELECT DISTINCT category FROM IngredientsDetail")
//    String [] findDistinctCategory();

//    IngredientsDetail[] findByCategory(String category);

//    @Query("SELECT name From IngredientsDetail where id = :ingredientsDetailId")
//    String findIngredientsDetailNameByIngredientsDetailId(Integer ingredientsDetailId);
}
