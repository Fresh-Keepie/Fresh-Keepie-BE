package com.masoongsoong.FreashKeepie.global.controller;

import com.masoongsoong.FreashKeepie.domain.product.model.IngredientsDetail;
import com.masoongsoong.FreashKeepie.global.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "DataController v0.1")
@RestController
@RequestMapping("/data")
public class DataController {
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private static final String MESSAGE = "message";

    @Autowired
    private DataService dataService;

//    회원이 작성한 레시피 게시물인듯
//    @Operation(summary = "레시피 추가", description = "레시피 추가")
//    @PostMapping("/createRecipe")
//    public ResponseEntity<Map<String, Object>> createRecipe(
//            @RequestBody Recipe recipe){
//        Map<String, Object> resultMap = new HashMap<>();
//        HttpStatus status = null;
//        try {
//            dataService.creteRecipe(recipe);
//            resultMap.put(MESSAGE, SUCCESS);
//            status = HttpStatus.ACCEPTED;
//        } catch(Exception e) {
//            resultMap.put(MESSAGE, FAIL);
//            status = HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        return new ResponseEntity<>(resultMap, status);
//    }

    @Operation(summary = "Ingredientsdetail 추가", description = "Ingredientsdetail 추가")
    @PostMapping("/createIngredientsdetail")
    public ResponseEntity<Map<String, Object>> createIngredientsdetail(
            @RequestBody IngredientsDetail ingredientsdetail) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            dataService.createIngredientsdetail(ingredientsdetail);
            resultMap.put(MESSAGE, SUCCESS);
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            resultMap.put(MESSAGE, FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);
    }
}


//    @Operation (summary = "Sub 추가", description = "Sub 부재료 추가")
//    @PostMapping("/createSub")
//    public ResponseEntity<Map<String, Object>> createSub(
//            @RequestBody Sub sub){
//        Map<String, Object> resultMap = new HashMap<>();
//        HttpStatus status = null;
//        try {
//            dataService.createSub(sub);
//            resultMap.put(MESSAGE, SUCCESS);
//            status = HttpStatus.ACCEPTED;
//        }catch(Exception e) {
//            resultMap.put(MESSAGE, FAIL);
//            status = HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        return new ResponseEntity<> (resultMap, status);
//    }
//
//}
