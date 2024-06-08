package com.masoongsoong.FreashKeepie.domain.product.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
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

    public String userId;

    public IngredientsDto( String expiration_date, int fridgeId, int ingredientsDetailId, String userId) {
        super();
        this.expiration_date = expiration_date;
        this.fridgeId = fridgeId;
        this.ingredientsDetailId = ingredientsDetailId;
        this.userId = userId;
    }

    public String getUser(){return userId;}

    @Override
    public String toString() {
        return "IngredientsDto [ expiration_date=" + expiration_date +  ", fridgeId="
                + fridgeId + ", ingredientsDetailId=" + ingredientsDetailId + "]";
    }

}