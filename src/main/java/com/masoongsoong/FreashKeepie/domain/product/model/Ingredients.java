package com.masoongsoong.FreashKeepie.domain.product.model;


import com.masoongsoong.FreashKeepie.domain.product.model.dto.IngredientsDto;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.persistence.*;


@Table(name = "ingredients")
@Entity
@Schema(name = "재료 정보", description = "재료 정보를 저장하는 Entity")
public class Ingredients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(title = "고유 ID - PK")
    @Column(nullable = false)
    int id;
    @Schema(title = "유통 기한")
    @Column(nullable = true)
    String expiration_date;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @Schema(title = "냉장고 정보 - FK")
    @JoinColumn(nullable = false , name = "fridgeId")
    private Fridge fridge;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @Schema(title = "재료 상세 정보 - FK")
    @JoinColumn(nullable = false , name = "ingredientsDetailId")
    private IngredientsDetail ingredientsdetail;

    protected Ingredients() {
    }

    public Ingredients(String expiration_date,  Fridge fridge, IngredientsDetail ingredientsdetail) {
        this.expiration_date = expiration_date;
        this.fridge = fridge;
        this.ingredientsdetail = ingredientsdetail;
    }

    public Ingredients(IngredientsDto ingredientsDto, Fridge fridge, IngredientsDetail ingredientsDetail) {
        this.id = ingredientsDto.getId();
        this.expiration_date = ingredientsDto.getExpiration_date();
        this.fridge = fridge;
        this.ingredientsdetail = ingredientsDetail;
    }

    public int getId() {
        return id;
    }

    public String getExpiration_date() {
        return expiration_date;
    }

    public Fridge getFridge() {
        return fridge;
    }

    public IngredientsDetail getIngredientsdetail() {
        return ingredientsdetail;
    }

    @Override
    public String toString() {
        return "Ingredients [id=" + id + ", expiration_date=" + expiration_date + ", fridge="
                + fridge + ", ingredients detail=" + ingredientsdetail + "]";
    }

}