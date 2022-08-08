package com.ecommerce.imobiliaria.Controller;

import com.ecommerce.imobiliaria.Models.User;
import com.ecommerce.imobiliaria.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/imobil")
@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/usuarios")
    public List<User> usuarios(){
        return userService.listar();
    }

}
