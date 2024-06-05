package com.masoongsoong.FreashKeepie.domain.product.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "재료 정보 담는 Dto", description = "id, 유통기한, 냉장고 ID, 재료 상세 ID를 포함하는 객체")
public class IngredientsDto {
    @Schema(title = "고유 ID - PK")
    private int id;
    @Schema(title = "유통기한")
    private String expiration_date;
    @Schema(title = "냉장고 고유 ID - FK")
    private int fridgeId;
    @Schema(title = "재료 정보 ID - FK")
    private int ingredientsDetailId;

    public IngredientsDto( String expiration_date, int fridgeId, int ingredientsDetailId) {
        super();
        this.expiration_date = expiration_date;
        this.fridgeId = fridgeId;
        this.ingredientsDetailId = ingredientsDetailId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(String expiration_date) {
        this.expiration_date= expiration_date;
    }

    public int getFridgeId() {
        return fridgeId;
    }

    public void setFridgeId(int fridgeId) {
        this.fridgeId = fridgeId;
    }

    public int getIngredientsDetailId() {
        return ingredientsDetailId;
    }

    public void setIngredientsDetailId(int ingredientsDetailId) {
        this.ingredientsDetailId = ingredientsDetailId;
    }

    @Override
    public String toString() {
        return "IngredientsDto [id=" + id + ", expiration_date=" + expiration_date +  ", fridgeId="
                + fridgeId + ", ingredientsDetailId=" + ingredientsDetailId + "]";
    }

}