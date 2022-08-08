package com.ecommerce.imobiliaria.Controller;

import com.ecommerce.imobiliaria.Models.Endereco;
import com.ecommerce.imobiliaria.Services.EnderecoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping("/imobil")
@RestController
@AllArgsConstructor
public class EnderecoController {

    private EnderecoService enderecoService;

    @GetMapping("/enderecos")
    public List<Endereco> mostrarTodosEnderecos() {
        List<Endereco> enderecos = enderecoService.mostrarTodosEnderecos();
        return enderecos;
    }

    @GetMapping("/enderecos/{idEndereco}")
    public ResponseEntity<Endereco> mostrarUmEnderecoPeloId(@PathVariable Integer idEndereco) {
        Endereco endereco = enderecoService.mostrarUmEnderecoPeloId(idEndereco);
        return ResponseEntity.ok().body(endereco);
    }

    @PostMapping("/enderecos")
    public ResponseEntity<Endereco> cadastrarEndereco(@RequestBody Endereco endereco) {
        endereco = enderecoService.cadastrarEndereco(endereco);
        URI novaURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(endereco.getIdEndereco()).toUri();
        return ResponseEntity.created(novaURI).body(endereco);
    }

    @PutMapping("/enderecos/{idEndereco}")
    public ResponseEntity<Endereco> editarEndereco(@PathVariable Integer idEndereco, @RequestBody Endereco endereco) {
        endereco.setIdEndereco(idEndereco);
        enderecoService.editarEndereco(endereco, idEndereco);
        return ResponseEntity.ok().body(endereco);
    }


}
