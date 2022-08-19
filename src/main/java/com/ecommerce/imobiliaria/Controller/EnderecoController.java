package com.ecommerce.imobiliaria.Controller;

import com.ecommerce.imobiliaria.Models.Endereco;
import com.ecommerce.imobiliaria.Services.EnderecoService;
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
public class EnderecoController {

    private EnderecoService enderecoService;

    @PreAuthorize("hasAnyAuthority('CONSUMIDOR')")
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

    @PreAuthorize("hasAnyAuthority('ADMIN','CONSUMIDOR','VENDEDOR')")
    @PostMapping("/enderecos/usuario/{idUser}")
    public ResponseEntity<Endereco> cadastrarEnderecoUsuario(@RequestBody Endereco endereco, @PathVariable Integer idUser) {
        endereco = enderecoService.cadastrarEnderecoUsuario(endereco, idUser);
        URI novaURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(endereco.getIdEndereco()).toUri();
        return ResponseEntity.created(novaURI).body(endereco);
    }

//    @PreAuthorize("hasAnyAuthority('ADMIN','CONSUMIDOR','VENDEDOR')")
    @PostMapping("/enderecos/imovel/{idImovel}")
    public ResponseEntity<Endereco> cadastrarEnderecoImovel(@RequestBody Endereco endereco, @PathVariable Integer idImovel) {
        endereco = enderecoService.cadastrarEnderecoImovel(endereco, idImovel);
        URI novaURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(endereco.getIdEndereco()).toUri();
        return ResponseEntity.created(novaURI).body(endereco);
    }

    @PreAuthorize("hasAnyAuthority('CONSUMIDOR')")
    @PutMapping("/enderecos/{idEndereco}")
    public ResponseEntity<Endereco> editarEndereco(@PathVariable Integer idEndereco, @RequestBody Endereco endereco) {
        endereco.setIdEndereco(idEndereco);
        enderecoService.editarEndereco(endereco);
        return ResponseEntity.ok().body(endereco);
    }


}
