package com.ecommerce.imobiliaria.Controller;

import com.ecommerce.imobiliaria.Models.User;
import com.ecommerce.imobiliaria.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/imobil")
@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;


    @PreAuthorize("permitAll()")
    @GetMapping("/usuarios")
    public List<User> usuarios(){
        return userService.listar();
    }

}
