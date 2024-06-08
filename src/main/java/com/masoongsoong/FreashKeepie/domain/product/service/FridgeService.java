package com.masoongsoong.FreashKeepie.domain.product.service;


import java.util.List;
import java.util.Optional;

import com.masoongsoong.FreashKeepie.domain.member.model.User;
import com.masoongsoong.FreashKeepie.domain.product.model.Fridge;
import com.masoongsoong.FreashKeepie.domain.product.model.Ingredients;
import com.masoongsoong.FreashKeepie.domain.product.model.IngredientsDetail;
import com.masoongsoong.FreashKeepie.domain.product.model.dto.*;
import com.masoongsoong.FreashKeepie.global.error.WrongFormException;


public interface FridgeService {

    void create(FridgeDto fridgeDto) throws WrongFormException;

    void addIngredients(IngredientsDto ingredientsDto) throws WrongFormException;

    Fridge[] fridgeList(int id);

    List<IngredientsDetail> ingredientsDetailList(int id);

    Optional<Fridge> fridgeDetail(int fridgeId) throws WrongFormException;

    Ingredients[] ingredientsList(int fridgeId);

    void fridgeDel(String userId, int fridgeId) throws WrongFormException;

    void delIngredients(String userId, int ingredientsId) throws WrongFormException;

//    void moveIngredients(IngredientsDto ingredientsDto) throws WrongFormException;

}
