package com.masoongsoong.FreashKeepie.domain.product.service;

import com.masoongsoong.FreashKeepie.domain.member.model.User;
import com.masoongsoong.FreashKeepie.domain.member.repository.UserRepository;
import com.masoongsoong.FreashKeepie.domain.product.model.Fridge;
import com.masoongsoong.FreashKeepie.domain.product.model.Ingredients;
import com.masoongsoong.FreashKeepie.domain.product.model.IngredientsDetail;
import com.masoongsoong.FreashKeepie.domain.product.model.dto.FridgeDto;
import com.masoongsoong.FreashKeepie.domain.product.model.dto.IngredientsDto;
import com.masoongsoong.FreashKeepie.domain.product.repository.FridgeRepository;
import com.masoongsoong.FreashKeepie.domain.product.repository.IngredientsRepository;
import com.masoongsoong.FreashKeepie.domain.product.repository.IngredientsDetailRepository;
import com.masoongsoong.FreashKeepie.global.error.WrongFormException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FridgeServiceImpl implements FridgeService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FridgeRepository fridgeRepository;
    @Autowired
    private IngredientsRepository ingredientsRepository;
    @Autowired
    private IngredientsDetailRepository ingredientsDetailRepository;

    @Override
    public void create(FridgeDto fridgeDto) throws WrongFormException {
        User user = userRepository.findByUserId(fridgeDto.getUser()).orElseThrow(() -> new RuntimeException("올바르지 않은 user id"));

        // User 엔티티를 먼저 저장합니다.
        if (user != null && user.getId() == 0) {
            userRepository.save(user);
        }

        // Fridge 엔티티를 저장합니다.
        Fridge fridge = new Fridge();
        fridgeRepository.save(fridge);
    }

    @Override
    public void addIngredients(IngredientsDto ingredientsDto) throws WrongFormException {
        int cnt = ingredientsRepository.findCntByIngredientsDetailId(ingredientsDto.getIngredientsDetailId(),
                ingredientsDto.getFridgeId());

        if (cnt == 0) {
            Ingredients ingredients = new Ingredients(ingredientsDto);
            ingredientsRepository.save(ingredients);
        } else {
            int id = ingredientsRepository.findByIngredientsDetailIdandFridgeId(ingredientsDto.getIngredientsDetailId(),
                    ingredientsDto.getFridgeId()).orElseThrow(() -> new WrongFormException("재료가 존재하지 않습니다"));

            Ingredients ingredientsSave = new Ingredients(new IngredientsDto(ingredientsDto.getExpiration_date(), ingredientsDto.getFridgeId(),
                    ingredientsDto.getIngredientsDetailId()));

            ingredientsRepository.save(ingredientsSave);
        }
    }

    @Override
    public Fridge[] fridgeList(int id) {
        return fridgeRepository.findByUserId(id);
    }

    @Override
    public List<IngredientsDetail> ingredientsDetailList() {
        return ingredientsDetailRepository.findAll();
    }

    @Override
    public Optional<Fridge> fridgeDetail(int fridgeId) throws WrongFormException {
        return Optional.of(
                fridgeRepository.findById(fridgeId)).orElseThrow(() -> new WrongFormException("존재 하지 않는 냉장고 입니다"));
    }

    @Override
    public Ingredients[] ingrediantsList(int fridgeId) {
        return ingredientsRepository.findByFridgeId(fridgeId);
    }


//    @Override
//    public void fridgeDel(int user_id, int fridgeId) throws WrongFormException {
//        Fridge fridge = fridgeRepository.findByIdAndUser_Id(user_id, fridgeId)
//                .orElseThrow(() -> new WrongFormException("존재하지 않는 냉장고 입니다"));
//
//        fridgeRepository.delete(fridge);
//    }

    @Override
    @Transactional
    public void fridgeDel(FridgeDto fridgeDto, int fridgeId) throws WrongFormException {
        // User가 존재하는지 확인합니다.
        User user = userRepository.findById(fridgeDto.getUser()).orElseThrow(() -> new RuntimeException("올바르지 않은 user id"));


        // Fridge를 찾고 삭제합니다.
        Optional<Fridge> fridgeOptional = fridgeRepository.findById(Integer.valueOf(fridgeDto.getName()));
        if (!fridgeOptional.isPresent()) {
            throw new WrongFormException("Fridge not found");
        }

        Fridge fridge = fridgeOptional.get();
        if (!fridge.getUser().equals(user)) {
            throw new WrongFormException("User does not own this fridge");
        }

        fridgeRepository.delete(fridge);
    }

    @Override
    public void delIngredients(int ingredientsId) throws WrongFormException {
        Ingredients ingredients = ingredientsRepository.findById(ingredientsId)
                .orElseThrow(() -> new WrongFormException("재료 번호 확인 필요"));

        ingredientsRepository.delete(ingredients);
    }

    @Override
    public void moveIngredients(IngredientsDto ingredientsDto) throws WrongFormException {
        Ingredients ingredients = Optional.ofNullable(new Ingredients(ingredientsDto))
                .orElseThrow(() -> new WrongFormException("재료의 정보 입력 필수"));

        ingredientsRepository.save(ingredients);
    }

}