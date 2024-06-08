package com.masoongsoong.FreashKeepie.domain.product.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.masoongsoong.FreashKeepie.domain.member.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IngredientsDetailDto {

    @Schema(title = "재료 이름")
    String productName;

    @Schema(title = "재료양")
    Integer amount;

    String userId;

    public IngredientsDetailDto(String productName, Integer amount, String userId) {
        this.productName = productName;
        this.amount = amount;
        this.userId = userId;
    }

}


