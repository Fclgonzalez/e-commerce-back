package com.ecommerce.imobiliaria.Controller;

import com.ecommerce.imobiliaria.Models.Foto;
import com.ecommerce.imobiliaria.Services.FotoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping("/imobil")
@RestController
@AllArgsConstructor
public class FotoController {

    private FotoService fotoService;


    @GetMapping("/fotos")
    public List<Foto> mostrarFotos(){
        return fotoService.mostrarFotos();
    }

    @GetMapping("/fotos/{idFoto}")
    public ResponseEntity<Foto> mostrarFotoPeloId(@PathVariable Integer idFoto){
        Foto foto = fotoService.mostrarFotoPeloId(idFoto);
        return ResponseEntity.ok().body(foto);
    }

    @GetMapping("/fotosImovel")
    public List<Foto> buscarFotoPorImovel(@RequestParam("idVendedor") Integer idImovel){
        return fotoService.buscarFotoPorImovel(idImovel);
    }

    @PostMapping("/foto/{idImovel}")
    public ResponseEntity<Foto> salvarFoto(@RequestBody Foto foto,
                                           @PathVariable Integer idImovel){
        foto.setIdImovel(idImovel);
        foto = fotoService.salvarFoto(foto, idImovel);
        URI novaURI = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}")
                .buildAndExpand(foto.getId()).toUri();
        return ResponseEntity.created(novaURI).body(foto);
    }

    @DeleteMapping("/foto/{idFoto}")
    public ResponseEntity<Void> excluirFoto(@PathVariable Integer idFoto){
        fotoService.excluirFoto(idFoto);
        return ResponseEntity.noContent().build();
    }
}
