package com.ecommerce.imobiliaria.Repositories;

import com.ecommerce.imobiliaria.Models.User;
import com.ecommerce.imobiliaria.Models.Visita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VisitaRepository extends JpaRepository<Visita, Integer> {

    @Query(value = "SELECT * FROM ecommerceimobiliaria.visita WHERE visita.id_imovel = :id", nativeQuery = true)
    List<Visita> findByImovelId(Integer id);

    @Query(value = "SELECT * FROM ecommerceimobiliaria.visita WHERE visita.id_consumidor = :id", nativeQuery = true)
    List<Visita> findByIdConsumidor(Integer id);


    @Query(value = "SELECT * FROM ecommerceimobiliaria.visita WHERE visita.id_vendedor = :id", nativeQuery = true)
    List<Visita> findByIdVendedor(Integer id);

    @Query(value = "SELECT * FROM ecommerceimobiliaria.visita WHERE visita.status_visita = :status", nativeQuery = true)
    List<Visita> findByStatus(String status);

    @Query(value = "SELECT * FROM ecommerceimobiliaria.visita WHERE visita.status_visita = :status AND visita.id_vendedor = :id", nativeQuery = true)
    List<Visita> findByStatusAndIdVendedor(String status, Integer id);

    @Query(value = "SELECT * FROM ecommerceimobiliaria.visita WHERE visita.status_visita = :status AND visita.id_consumidor = :id", nativeQuery = true)
    List<Visita> findByStatusAndIdConsumidor(String status, Integer id);
}
