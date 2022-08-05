package com.ecommerce.imobiliaria.Repositories;

import com.ecommerce.imobiliaria.Models.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImovelRepository extends JpaRepository<Imovel, Integer> {
}
