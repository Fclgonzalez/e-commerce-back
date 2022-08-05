package com.ecommerce.imobiliaria.Repositories;

import com.ecommerce.imobiliaria.Models.Caracteristica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CaracteristicaRepository extends JpaRepository<Caracteristica, Integer> {

    //Query FindALL
    @Query(value = "SELECT * FROM ecommerceimobiliaria.caracteristica ", nativeQuery = true)
    List<Caracteristica> findAll();

}
