package com.masoongsoong.FreashKeepie.domain.product.model;

import com.masoongsoong.FreashKeepie.domain.member.model.User;
import com.masoongsoong.FreashKeepie.domain.product.model.dto.IngredientsDetailDto;
import com.masoongsoong.FreashKeepie.domain.product.model.dto.IngredientsDto;
import jakarta.persistence.*;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Table(name = "ingredientsDetail")
@Getter
@Entity
@Schema(name = "재료 상세 정보", description = "재료의 상세 정보 저장 Entity")
public class IngredientsDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(title = "고유 ID - PK")
    int id;

    @Schema(title = "재료 이름")
    @Column(nullable = false, length = 20)
    String productName;

    @Schema(title = "재료양")
    @Column(nullable = false, length = 20)
    Integer amount;

    @ManyToOne
    @Schema(title = "fridge fk")
    @JoinColumn(nullable = false , name = "userId")
    User user;

    protected IngredientsDetail() {
    }


    public IngredientsDetail(User user, String productName, Integer amount) {
        this.user = user;
        this.productName = productName;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "IngredientsDetail [id=" + id + ", name=" + productName + ", amount=" + amount+  "]";
    }


}