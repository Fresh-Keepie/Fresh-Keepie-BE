package com.masoongsoong.FreashKeepie.domain.product.model;

import com.masoongsoong.FreashKeepie.domain.member.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


import com.masoongsoong.FreashKeepie.domain.product.model.dto.FridgeDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Table(name = "fridge")
@Getter
@Entity
@Schema(name = "냉장고 정보", description = "냉장고 정보를 담기 위한 객체")
public class Fridge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(title = "fridge pk")
    @Column(nullable = false)
    int id;
    @Schema(title = "fridge name")
    @Column(nullable = false, length = 10)
    String name;


    @ManyToOne
    @Schema(title = "fridge fk")
    @JoinColumn(nullable = false , name = "userId")
    User user;

    public Fridge() {
    }

    public Fridge(int fridgeId) {
        this.id = fridgeId;
    }

    public Fridge(String name, User user){
        this.name = name;
        this.user = user;
    }


    @Override
    public String toString() {
        return "Fridge [id=" + id + ", name=" + name + ", user=" + user + "]";
    }

}