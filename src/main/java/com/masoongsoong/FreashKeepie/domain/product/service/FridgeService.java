package com.masoongsoong.FreashKeepie.domain.product.service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.masoongsoong.FreashKeepie.domain.member.model.User;
import com.masoongsoong.FreashKeepie.domain.product.model.Fridge;
import com.masoongsoong.FreashKeepie.domain.product.model.Ingredients;
import com.masoongsoong.FreashKeepie.domain.product.model.IngredientsDetail;
import com.masoongsoong.FreashKeepie.domain.product.model.dto.*;
import com.masoongsoong.FreashKeepie.global.error.WrongFormException;

//public interface FridgeService {
//
//    void create(FridgeDto fridgeDto) throws WrongFormException;
//
//
//    void addIngredients(IngredientsDto ingredientsDto) throws WrongFormException;
//
//    Fridge[] fridgeList(int id);
//
//    List<IngredientsDetail> ingredientsDetailList();
//
//    Optional<Fridge> fridgeDetail(int fridgeId) throws WrongFormException;
//
//    Ingredients[] ingrediantsList(int fridgeId);
//
//    void fridgeDel(int user_id, int fridgeId) throws WrongFormException;
//
//    void delIngredients(int ingredientsId) throws WrongFormException;
//
//    void moveIngredients(IngredientsDto ingredientsDto) throws WrongFormException;
//
//}

public interface FridgeService {

    void create(FridgeDto fridgeDto) throws WrongFormException;

    void addIngredients(IngredientsDto ingredientsDto) throws WrongFormException;

    Fridge[] fridgeList(int id);

    List<IngredientsDetail> ingredientsDetailList();

    Optional<Fridge> fridgeDetail(int fridgeId) throws WrongFormException;

    Ingredients[] ingrediantsList(int fridgeId);

    void fridgeDel(FridgeDto fridgeDto,int fridgeId) throws WrongFormException;

    void delIngredients(int ingredientsId) throws WrongFormException;

    void moveIngredients(IngredientsDto ingredientsDto) throws WrongFormException;

}
