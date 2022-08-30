package com.ecommerce.imobiliaria.Controller;

import com.ecommerce.imobiliaria.Models.User;
import com.ecommerce.imobiliaria.Repositories.UserRepository;
import com.ecommerce.imobiliaria.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RequestMapping("/imobil")
@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private UserRepository userRepository;

    @GetMapping("/usuarios")
    public List<User> usuarios() {
        return userService.listar();
    }

    @GetMapping("/usuarios/username")
    public String verificaUsername(@RequestParam("username") String name){
        return userService.verificaUsername(name);
    }

    @GetMapping("/usuarios/identificacao")
    public String verificaIdentificacao(@RequestParam("identificacao") String identificacao){
        return  userService.verificaIdentificacao(identificacao);
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

    @GetMapping("/usuarios/username/{username}")
    public User usuarioPorUsername(@PathVariable String username) {
        return userService.findByUsername(username);
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
        Optional<User> userprincipal = userRepository.findByUsername(principal.getName());
        if (principal.getName().equals(username)) {
            User userUpdated = userService.updateUser(user);
            return new ResponseEntity<>(userUpdated, HttpStatus.OK);
        } else if (userprincipal.get().getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("ADMIN"))) {
            User userUpdated = userService.updateUser(user);
            return new ResponseEntity<>(userUpdated, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    //disabilitarConta
    @PreAuthorize("hasAnyAuthority('ADMIN','CONSUMIDOR','VENDEDOR')")
    @PatchMapping("/usuarios/{username}/disable")
    public ResponseEntity<User> disableUser(@PathVariable String username,  Principal principal) {
        if (principal.getName().equals(username)) {
            User userUpdated = userService.disabilitarHabilitarConta(username);
            return new ResponseEntity<>(userUpdated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PatchMapping("/usuarios/{username}/admin")
    public ResponseEntity<User> adminDisableUser(@PathVariable String username ) {

            User userUpdated = userService.disabilitarHabilitarConta(username);
            return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }

    //findUsersRegistradoPorMes
    @GetMapping("/usuarios/registrados/{roleId}")
    public List<User> findUsersRegistradoPorMes(@PathVariable Integer roleId) {
        return userService.findUsersRegistradoPorMes(roleId);
    }

    //findTotalSignedUpByRole
    @GetMapping("/usuarios/total/{roleId}")
    public Integer findTotalSignedUpByRole(@PathVariable Integer roleId) {
        return userService.findTotalSignedUpByRole(roleId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PatchMapping("/usuarios/{id}/promote")
    public ResponseEntity<User> promoverParaAdmin(@PathVariable Integer id) {
        User userUpdated = userService.promoverParaAdmin(id);
        return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }

}