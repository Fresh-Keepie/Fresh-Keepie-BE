package com.masoongsoong.FreashKeepie.domain.product.controller;

import com.masoongsoong.FreashKeepie.domain.product.model.IngredientsDetail;
import com.masoongsoong.FreashKeepie.domain.product.model.dto.IngredientsDetailDto;
import com.masoongsoong.FreashKeepie.domain.product.model.dto.IngredientsDto;
import com.masoongsoong.FreashKeepie.domain.product.service.DataService;
import com.masoongsoong.FreashKeepie.global.error.DataNotFoundException;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.HashMap;
import java.util.Map;

import static jakarta.security.auth.message.AuthStatus.SUCCESS;
import static sun.security.provider.certpath.BuildStep.FAIL;
import static sun.tools.jconsole.Messages.MESSAGE;

@Tag(name = "DataController v0.1")
@RestController
@RequestMapping("/ingredient")
public class DataController {
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private static final String MESSAGE = "message";

    @Autowired
    private DataService dataService;


    @Operation(summary = "Ingredientsdetail 추가", description = "Ingredientsdetail 추가")
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createIngredientsDetail(
            @RequestBody @Parameter(name = "상품 등록", required = true) IngredientsDetailDto ingredientsDetailDto) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            dataService.createIngredientsdetail(ingredientsDetailDto);
            resultMap.put(MESSAGE, SUCCESS);
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            resultMap.put(MESSAGE, FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);
    }
//
//    @PatchMapping("/update/{userId}/{ingredientsDetailId}")
//    public ResponseEntity<Map<String, Object>> updateIngredientsDetail(
//            @PathVariable int ingredientsDetailId, @PathVariable int userId,
//            @RequestBody IngredientsDto ingredientsDto) {
//        Map<String, Object> resultMap = new HashMap<>();
//        HttpStatus status;
//        try {
//            resultMap.put(MESSAGE, SUCCESS);
//            status = HttpStatus.OK;
//        } catch (Exception e) {
//            resultMap.put(MESSAGE, FAIL);
//            status = HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        return new ResponseEntity<>(resultMap, status);
//    }
//
//    @GetMapping({"/{memberId}/{ingredientsDetailId}"})
//    public ResponseEntity<Map<String, Object>> getingredientInfo(@PathVariable("userId") String memberId, @PathVariable("ingredientsDetailId") int ingredientsDetailId) {
//        Map<String, Object> resultMap = new HashMap<>();
//        HttpStatus status;
//
//        try {
//            dataService.getIngredientsdetail(ingredientsDetailId);
//            resultMap.put(MESSAGE, SUCCESS);
//            status = HttpStatus.ACCEPTED;
//        } catch (DataNotFoundException var3) {
//            return ResponseEntity.notFound().build();
//        }
//        return new ResponseEntity<>(resultMap, status);
//    }

}