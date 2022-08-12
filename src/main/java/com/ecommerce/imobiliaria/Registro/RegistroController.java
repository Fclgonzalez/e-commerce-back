package com.ecommerce.imobiliaria.Registro;


import com.ecommerce.imobiliaria.Services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/imobil")
@AllArgsConstructor
public class RegistroController {
    private RegistroService registroService;

    private AuthenticationManager authenticationManager;
    private RoleService roleService;

    @PostMapping("/registro/consumidor")
    public ResponseEntity<Void> registroConsumidor(@RequestBody RegistroRequest request) {
        registroService.registroConsumidor(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/confirmar")
    public String confirmToken(@RequestParam("token") String token){
        return registroService.confirmToken(token);
    }

    @PostMapping("/registro/vendedor")
    public ResponseEntity<Void> registroVendedor(@RequestBody RegistroRequest request) {
        registroService.registroVendedor(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
