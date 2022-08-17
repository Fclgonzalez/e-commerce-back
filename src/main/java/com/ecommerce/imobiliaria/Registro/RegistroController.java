package com.ecommerce.imobiliaria.Registro;


import com.ecommerce.imobiliaria.Models.User;
import com.ecommerce.imobiliaria.Repositories.UserRepository;
import com.ecommerce.imobiliaria.Services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/imobil")
@AllArgsConstructor
public class RegistroController {
    private RegistroService registroService;

    private AuthenticationManager authenticationManager;
    private RoleService roleService;
    private UserRepository userRepository;

    @PostMapping("/registro/consumidor")
    public ResponseEntity<Integer> registroConsumidor(@RequestBody RegistroRequest request) {
        registroService.registroConsumidor(request);
        Optional<User> user = userRepository.findByUsername(request.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(user.get().getIdUser());
    }

    @GetMapping("/confirmar")
    public String confirmToken(@RequestParam("token") String token){
        return registroService.confirmToken(token);
    }

    @PostMapping("/registro/vendedor")
    public ResponseEntity<Integer> registroVendedor(@RequestBody RegistroRequest request) {
        registroService.registroVendedor(request);
        Optional<User> user = userRepository.findByUsername(request.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(user.get().getIdUser());
    }
}
