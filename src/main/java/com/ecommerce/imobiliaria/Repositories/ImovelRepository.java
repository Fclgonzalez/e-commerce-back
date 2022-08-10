package com.ecommerce.imobiliaria.Repositories;

import com.ecommerce.imobiliaria.Models.Imovel;
import com.ecommerce.imobiliaria.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ImovelRepository extends JpaRepository<Imovel, Integer> {

//    List<Imovel> findByVendedor(Optional<User> user);

    List<Imovel> findByContratoAluguel(Boolean contratoAluguel);

    List<Imovel> findByContratoVenda(Boolean contratoVenda);

    List<Imovel> findByValorAluguel(Double valorAluguel);

    List<Imovel> findByValorVenda(Double valorVenda);

    List<Imovel> findByArea(Double area);

    List<Imovel> findByQuartos(Integer quartos);

    List<Imovel> findBySuite(Integer suite);

    List<Imovel> findByBanheiros(Integer banheiros);

    List<Imovel> findByVagas(Integer vagas);

    @Query(value = "SELECT * FROM imovel WHERE finalidade_imovel =:finalidadeImovel",nativeQuery = true )
    List<Imovel> findByFinalidadeImovel(String finalidadeImovel);

    @Query(value = "SELECT * FROM imovel WHERE tipo_imovel =:tipoImovel",nativeQuery = true )
    List<Imovel> findByTipoImovel(String tipoImovel);
}
