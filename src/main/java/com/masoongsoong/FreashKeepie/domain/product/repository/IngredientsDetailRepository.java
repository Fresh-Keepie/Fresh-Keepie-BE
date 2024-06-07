package com.masoongsoong.FreashKeepie.domain.product.repository;

import com.masoongsoong.FreashKeepie.domain.product.model.IngredientsDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IngredientsDetailRepository extends JpaRepository<IngredientsDetail, Integer> {
//    @Query("SELECT DISTINCT category FROM IngredientsDetail")
//    String [] findDistinctCategory();

//    IngredientsDetail[] findByCategory(String category);

    @Query("SELECT i.productName FROM IngredientsDetail i WHERE i.id = :ingredientsDetailId")
    String findIngredientsDetailNameByIngredientsDetailId(int ingredientsDetailId);

    List<IngredientsDetail> findByUserId(int id);
}
