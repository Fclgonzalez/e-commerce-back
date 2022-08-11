package com.ecommerce.imobiliaria.Services;

import com.ecommerce.imobiliaria.Models.Role;
import com.ecommerce.imobiliaria.Models.User;
import com.ecommerce.imobiliaria.Repositories.RoleRepository;
import com.ecommerce.imobiliaria.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleService {
 private final RoleRepository roleRepository;
 private final UserRepository userRepository;

    //SaveRole
    public Role novaRole(Role role) {
        role.setId(role.getId());
        return roleRepository.save(role);
    }

    public List<Role> listarRole() {
        return roleRepository.findAll();
    }

    public Role findById(Long id) {
        return roleRepository.findById(id).get();
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
