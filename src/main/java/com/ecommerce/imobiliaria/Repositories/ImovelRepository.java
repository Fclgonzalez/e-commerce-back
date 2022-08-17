package com.ecommerce.imobiliaria.Repositories;

import com.ecommerce.imobiliaria.Models.Imovel;
import com.ecommerce.imobiliaria.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ImovelRepository extends JpaRepository<Imovel, Integer> {

    List<Imovel> findByContratoAluguel(Boolean contratoAluguel);

    @Query(value = "SELECT * FROM ecommerceimobiliaria.imovel WHERE id_vendedor = :idUser", nativeQuery = true)
    List<Imovel> findByIdUser(Integer idUser);

    List<Imovel> findByContratoVenda(Boolean contratoVenda);

    @Query(value = "SELECT * FROM imovel WHERE contrato_aluguel = 1 AND valor_aluguel BETWEEN 0 AND :valorAluguel", nativeQuery = true)
    List<Imovel> findByValorAluguel(Double valorAluguel);

    @Query(value = "SELECT * FROM imovel WHERE contrato_venda = 1 AND valor_venda BETWEEN 0 AND :valorVenda", nativeQuery = true)
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

    @Query(value = "CALL imoveisPerMonth()", nativeQuery = true)
    List<?> findImoveisPerMOnth();

    @Query(value = "SELECT COUNT(*) FROM ecommerceimobiliaria.imovel", nativeQuery = true)
    Integer countImoveis();
}
