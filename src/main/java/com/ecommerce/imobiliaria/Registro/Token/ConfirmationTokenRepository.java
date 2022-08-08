package com.ecommerce.imobiliaria.Registro.Token;


import com.ecommerce.imobiliaria.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    Optional<ConfirmationToken> findByToken(String token);


    @Transactional
    @Modifying
    @Query("UPDATE ConfirmationToken c " +
            "SET c.confirmadoEm = ?2 " +
            "WHERE c.token = ?1")
    int updateConfirmedAt(String token,
                          LocalDateTime confirmadoEm);



    //findByUserId
    @Query(value = "SELECT * FROM confirmation_token WHERE user_id = :id", nativeQuery = true)
    Optional<ConfirmationToken> findByUserId(Integer id);


}

