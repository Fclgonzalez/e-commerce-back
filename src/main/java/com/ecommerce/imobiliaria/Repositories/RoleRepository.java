package com.ecommerce.imobiliaria.Repositories;

import com.ecommerce.imobiliaria.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
