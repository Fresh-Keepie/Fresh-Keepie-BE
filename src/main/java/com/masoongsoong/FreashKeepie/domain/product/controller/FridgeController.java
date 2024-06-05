package com.masoongsoong.FreashKeepie.domain.product.controller;


import com.masoongsoong.FreashKeepie.domain.member.model.User;
import com.masoongsoong.FreashKeepie.domain.product.model.Fridge;
import com.masoongsoong.FreashKeepie.domain.product.model.Ingredients;
import com.masoongsoong.FreashKeepie.domain.product.model.IngredientsDetail;
import com.masoongsoong.FreashKeepie.domain.product.model.dto.FridgeDto;
import com.masoongsoong.FreashKeepie.domain.product.model.dto.IngredientsDto;
import com.masoongsoong.FreashKeepie.domain.product.service.FridgeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/product")
public class FridgeController {
    private static final Logger logger = LoggerFactory.getLogger(FridgeController.class);

    private static final String MESSAGE = "message";
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private FridgeService fridgeService;

//    @Operation(summary = "냉장고 등록", description = "냉장고 정보를 입력하고 등록한다.", security = {
//            @SecurityRequirement(name = "X-AUTH-TOKEN") })
//    @PostMapping("/fridge")
//    public ResponseEntity<Map<String, Object>> create(@RequestBody @Parameter(name = "냉장고 등록에 필요한 정보", required = true) FridgeDto fridgeDto) {
//        Map<String, Object> resultMap = new HashMap<>();
//        HttpStatus status = null;
//
//        try {
//            fridgeService.create(fridgeDto);
//            resultMap.put(MESSAGE, SUCCESS);
//            status = HttpStatus.OK;
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//            resultMap.put(MESSAGE, FAIL);
//            status = HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        return new ResponseEntity<>(resultMap, status);
//    }

    @Operation(summary = "냉장고 등록", description = "냉장고 정보를 입력하고 등록한다.")
    @PostMapping("/fridge")
    public ResponseEntity<Map<String, Object>> create(@RequestBody @Parameter(name = "냉장고 등록에 필요한 정보", required = true) FridgeDto fridgeDto) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try {
            fridgeService.create(fridgeDto);
            resultMap.put(MESSAGE, SUCCESS);
            status = HttpStatus.OK;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            resultMap.put(MESSAGE, FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);
    }

    @Operation(summary = "재료 추가", description = "냉장고에 재료 추가", security = {
            @SecurityRequirement(name = "X-AUTH-TOKEN") })
    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> addIngredients(
            @Parameter(name = "추가할 재료 정보 및 냉장고 아이디") @RequestBody IngredientsDto ingredientsDto) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try {
            fridgeService.addIngredients(ingredientsDto);

            resultMap.put(MESSAGE, SUCCESS);
            status = HttpStatus.OK;
        } catch (Exception e) {
            logger.error(e.getMessage());
            resultMap.put(MESSAGE, FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);
    }

    @Operation(summary = "내 냉장고 목록 보기", description = "내가 등록한 냉장고의 정보를 가져온다.", security = {
            @SecurityRequirement(name = "X-AUTH-TOKEN") })
    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> fridgeList(@Parameter(name = "로그인 유저") FridgeDto fridgeDto) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try {
            Fridge[] fridgeList = fridgeService.fridgeList(Integer.parseInt(fridgeDto.getName()));

            resultMap.put(MESSAGE, SUCCESS);
            resultMap.put("fridgeList", fridgeList);
            status = HttpStatus.OK;
        } catch (Exception e) {
            logger.error(e.getMessage());
            resultMap.put(MESSAGE, FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);
    }

    @Operation(summary = "전체 재료 조회", description = "카테고리와 무관하게 전체 조회", security = {
            @SecurityRequirement(name = "X-AUTH-TOKEN") })
    @GetMapping("/product/list")
    public ResponseEntity<Map<String, Object>> ingredientsDetailList() {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try {
            List<IngredientsDetail> ingredients = fridgeService.ingredientsDetailList();

            resultMap.put(MESSAGE, SUCCESS);
            resultMap.put("ingredients", ingredients);
            status = HttpStatus.OK;
        } catch (Exception e) {
            logger.error(e.getMessage());
            resultMap.put(MESSAGE, FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);
    }

    @Operation(summary = "냉장고 포함 재료", description = "냉장고 번호를 통해 냉장고에 포함된 재료", security = {
            @SecurityRequirement(name = "X-AUTH-TOKEN") })
    @GetMapping("/ingredients/{fridge_id}")
    public ResponseEntity<Map<String, Object>> ingrediantsList(@PathVariable("fridge_id") int fridgeId) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try {
            Ingredients[] ingredients = fridgeService.ingrediantsList(fridgeId);

            resultMap.put(MESSAGE, SUCCESS);
            resultMap.put("ingredients", ingredients);
            status = HttpStatus.OK;
        } catch (Exception e) {
            logger.error(e.getMessage());
            resultMap.put(MESSAGE, FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);
    }

//    @Operation(summary = "냉장고 삭제", description = "등록한 냉장고 삭제.", security = {
//            @SecurityRequirement(name = "X-AUTH-TOKEN") })
//    @DeleteMapping("/list/{fridgeId}")
//    public ResponseEntity<Map<String, Object>> fridgeDel(@Parameter(name = "로그인 유저") FridgeDto fridgeDto,
//                                                         @PathVariable("fridgeId") int fridgeId) {
//        Map<String, Object> resultMap = new HashMap<>();
//        HttpStatus status = null;
//        try {
//            fridgeService.fridgeDel(fridgeDto.user_id, fridgeId);
//
//            resultMap.put(MESSAGE, SUCCESS);
//            status = HttpStatus.OK;
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//            resultMap.put(MESSAGE, FAIL);
//            status = HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        return new ResponseEntity<>(resultMap, status);
//    }

//    @Operation(summary = "냉장고 삭제", description = "등록한 냉장고 삭제.", security = {
//            @SecurityRequirement(name = "X-AUTH-TOKEN") })
//    @DeleteMapping("/list/{fridgeId}")
//    public ResponseEntity<Map<String, Object>> fridgeDel(
//                                                         @PathVariable("fridgeId") int fridgeId) {
//        Map<String, Object> resultMap = new HashMap<>();
//        HttpStatus status = null;
//        try {
//            fridgeService.fridgeDel(fridgeDto.userNo, fridgeId);
//            resultMap.put(MESSAGE, SUCCESS);
//            status = HttpStatus.OK;
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            resultMap.put(MESSAGE, FAIL);
//            status = HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        return new ResponseEntity<>(resultMap, status);
//    }

    @Operation(summary = "재료 제거", description = "냉장고 재료 제거", security = { @SecurityRequirement(name = "X-AUTH-TOKEN") })
    @DeleteMapping("/product/{ingredientsId}")
    public ResponseEntity<Map<String, Object>> delIngredients(@PathVariable("ingredientsId") int ingredientsId) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            fridgeService.delIngredients(ingredientsId);
            resultMap.put(MESSAGE, SUCCESS);
            status = HttpStatus.OK;
        } catch (Exception e) {
            logger.error(e.getMessage());
            resultMap.put(MESSAGE, FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);
    }

}