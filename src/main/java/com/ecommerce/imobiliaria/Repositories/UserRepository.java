package com.ecommerce.imobiliaria.Repositories;

import com.ecommerce.imobiliaria.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

        Optional<User> findByUsername(String username);

        @Transactional
        @Modifying
        @Query("UPDATE User u SET u.enabled = true WHERE u.username = ?1")
        int enableUser(String email);
}
