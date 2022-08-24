package com.ecommerce.imobiliaria.Repositories;

import com.ecommerce.imobiliaria.Models.Foto;
import com.ecommerce.imobiliaria.Models.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FotoRepository extends JpaRepository<Foto, Integer> {

    List<Foto> findByImovel(Optional<Imovel> imovel);
}
