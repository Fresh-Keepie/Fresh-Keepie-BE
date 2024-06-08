package com.masoongsoong.FreashKeepie.domain.product.repository;

import com.masoongsoong.FreashKeepie.domain.product.model.Fridge;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FridgeRepository extends JpaRepository<Fridge, Integer> {
    Fridge[] findByUserId(int userId);

    Optional<Fridge> findByIdAndUserId(int id, int userId);
}