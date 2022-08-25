package com.ecommerce.imobiliaria.Repositories;

import com.ecommerce.imobiliaria.Models.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface FotoRepository extends JpaRepository<Foto, Integer> {

    @Query(value = "SELECT * FROM foto WHERE id_imovel =:idImovel", nativeQuery = true)
    List<Foto> findByImovel(Integer idImovel);
}
