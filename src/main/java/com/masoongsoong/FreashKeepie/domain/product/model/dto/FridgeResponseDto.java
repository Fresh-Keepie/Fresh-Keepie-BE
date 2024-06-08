package com.masoongsoong.FreashKeepie.domain.product.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.masoongsoong.FreashKeepie.domain.member.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.kafka.common.protocol.types.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FridgeResponseDto {
    private int id;
    private String name;

    @JsonProperty("userId")
    private String userId;

    public FridgeResponseDto(int id, String name, User user) {
        this.id = id;
        this.name = name;
        this.userId = user.getUserId()
        ;
    }
}