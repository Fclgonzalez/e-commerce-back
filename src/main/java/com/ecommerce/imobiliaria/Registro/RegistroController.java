package com.ecommerce.imobiliaria.Registro;


import com.ecommerce.imobiliaria.Models.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/imobil")
@AllArgsConstructor
public class RegistroController {
    private RegistroService registroService;

    private AuthenticationManager authenticationManager;

    @PostMapping("/registro")
    public String registro(@RequestBody RegistroRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(registroService.registro(request)).getBody();
    }

    @GetMapping("/confirmar")
    public String confirmToken(@RequestParam("token") String token){
        return registroService.confirmToken(token);
    }


}
