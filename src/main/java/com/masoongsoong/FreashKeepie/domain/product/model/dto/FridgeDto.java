package com.masoongsoong.FreashKeepie.domain.product.model.dto;

import com.masoongsoong.FreashKeepie.domain.product.model.Schema;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "냉장고 정보 Dto")
public class FridgeDto {

    @Getter
    @Schema(name = "", title = "냉장고 이름", description = "")
    private String name; // 냉장고 이름

    public String userNo;

    @Override
    public String toString() {
        return "FridgeDto [name=" + name + ", user=" + userNo + "]";
    }

    public String getUser() {
        return userNo;
    }

    public void setUser(String userNo) {
        this.userNo = userNo;
    }
}

