package com.ecommerce.imobiliaria.Controller;

import com.ecommerce.imobiliaria.Models.User;
import com.ecommerce.imobiliaria.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequestMapping("/imobil")
@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;


    @GetMapping("/usuarios")
    public List<User> usuarios() {
        return userService.listar();
    }

    //findUserByRoleId
    @GetMapping("/usuarios/role/{roleId}")
    public List<User> findUserByRoleId(@PathVariable Integer roleId) {
        return userService.findUsersByRoleId(roleId);
    }

    @GetMapping("/usuarios/{id}")
    public User usuario(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CONSUMIDOR','VENDEDOR')")
    @DeleteMapping("/usuarios/{id}")
    public void deletar(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    //update user
    @PreAuthorize("hasAnyAuthority('ADMIN','CONSUMIDOR','VENDEDOR')")
    @PatchMapping("/usuarios/{username}")
    public ResponseEntity<User> updateUser(@PathVariable String username, @RequestBody User user, Principal principal) {
        if (principal.getName().equals(username)) {
            User userUpdated = userService.updateUser(user);
            return new ResponseEntity<>(userUpdated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    //disabilitarConta
    @PreAuthorize("hasAnyAuthority('ADMIN','CONSUMIDOR','VENDEDOR')")
    @PatchMapping("/usuarios/{username}/disable")
    public ResponseEntity<User> disableUser(@PathVariable String username,  Principal principal) {
        if (principal.getName().equals(username)) {
            User userUpdated = userService.disabilitarConta(username);
            return new ResponseEntity<>(userUpdated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PatchMapping("/usuarios/{username}/admin")
    public ResponseEntity<User> adminDisableUser(@PathVariable String username ) {

            User userUpdated = userService.disabilitarConta(username);
            return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }
}