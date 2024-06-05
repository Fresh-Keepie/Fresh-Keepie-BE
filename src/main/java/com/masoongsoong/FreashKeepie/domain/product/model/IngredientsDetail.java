package com.masoongsoong.FreashKeepie.domain.product.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Table(name = "ingredientsDetail")
@Entity
@Schema(name = "재료 상세 정보", description = "재료의 상세 정보 저장 Entity")
public class IngredientsDetail {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(title = "고유 ID - PK")
    int id;
    @Getter
    @Schema(title = "재료 이름")
    @Column(nullable = false, length = 20)
    String product_name;

    @Schema(title = "재료양")
    @Column(nullable = false, length = 20)
    Integer amount;

    protected IngredientsDetail() {
    }

    public IngredientsDetail(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }


    @Override
    public String toString() {
        return "IngredientsDetail [id=" + id + ", name=" + product_name + ", amount=" + amount+  "]";
    }

}