package com.masoongsoong.FreashKeepie.domain.product.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.masoongsoong.FreashKeepie.domain.member.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IngredientsDetailResponseDto {
    private int id;

    private String productName;

    private Integer amount;

    @JsonProperty("userId")
    private String userId;
    public IngredientsDetailResponseDto(int id, String productName, Integer amount, User user) {
        this.id = id;
        this.productName = productName;
        this.amount = amount;
        this.userId = user.getUserId();
    }


}
