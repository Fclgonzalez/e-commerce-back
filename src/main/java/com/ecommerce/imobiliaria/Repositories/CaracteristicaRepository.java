package com.ecommerce.imobiliaria.Repositories;

import com.ecommerce.imobiliaria.Models.Caracteristica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CaracteristicaRepository extends JpaRepository<Caracteristica, Integer> {
    @Query(value = "SELECT *\n" +
            "FROM ecommerceimobiliaria.caracteristica\n" +
            "INNER JOIN imovel_caracteristicas\n" +
            "ON caracteristica.id = imovel_caracteristicas.caracteristicas_id\n" +
            "WHERE imovel_id_imovel=:idImovel", nativeQuery = true)
    List<Caracteristica> MostrarCaracteristicaPeloIdImovel(Integer idImovel);




}
