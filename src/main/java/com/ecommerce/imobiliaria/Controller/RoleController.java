package com.ecommerce.imobiliaria.Controller;


import com.ecommerce.imobiliaria.Models.Role;
import com.ecommerce.imobiliaria.Services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping("/imobil")
@RestController
@AllArgsConstructor
public class RoleController {

    private RoleService roleService;

    @RequestMapping("/roles")
    public List<Role> roles(){
        return roleService.listarRole();
    }

    @RequestMapping("/roles/{id}")
    public ResponseEntity<Role> roleById(@PathVariable Long id){
         roleService.findById(id);
        return ResponseEntity.ok().body(roleService.findById(id));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CONSUMIDOR','VENDEDOR')")
    @PostMapping("/roles")
    public ResponseEntity<Role> novaRole(@RequestBody Role role){
         roleService.novaRole(role);
        URI novaUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(role.getId()).toUri();
        return ResponseEntity.created(novaUri).body(role);
    }

    //Salvar role no user
    @PreAuthorize("hasAnyAuthority('ADMIN','CONSUMIDOR','VENDEDOR')")
    @PostMapping("/roles/{roleName}/{username}")
    public ResponseEntity<Void> salvarRoleNoUser(@PathVariable String roleName, @PathVariable String username){
         roleService.salvarRoleNoUser(roleName, username);
            return ResponseEntity.ok().build();
    }

}
