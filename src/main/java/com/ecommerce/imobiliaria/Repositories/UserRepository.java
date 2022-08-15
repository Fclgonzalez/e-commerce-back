package com.ecommerce.imobiliaria.Repositories;

import com.ecommerce.imobiliaria.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

        Optional<User> findByUsername(String username);

        @Transactional
        @Modifying
        @Query("UPDATE User u SET u.enabled = true WHERE u.username = ?1")
        int enableUser(String email);

        @Query(value = "SELECT * FROM user, user_role WHERE user_role.id_role = :id AND user.id_user = user_role.id_user", nativeQuery = true)
        List<User> findUserByRoleId(Integer id);

}
