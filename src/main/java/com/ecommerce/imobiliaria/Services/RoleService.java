package com.ecommerce.imobiliaria.Services;

import com.ecommerce.imobiliaria.Models.Role;
import com.ecommerce.imobiliaria.Models.User;
import com.ecommerce.imobiliaria.Repositories.RoleRepository;
import com.ecommerce.imobiliaria.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleService {
 private final RoleRepository roleRepository;
 private final UserRepository userRepository;

    //SaveRole
    public Role novaRole(Role role) {
        return roleRepository.save(role);
    }

    //AddRoleToUser
    public void salvarRoleNoUser(String roleName, String username) {
        Role role = roleRepository.findByName(roleName);
        User user = userRepository.findByUsername(username).orElse(null);

        if(role != null && user != null) {
            user.getRoles().add(role);
            userRepository.save(user);
        }
    }
}
