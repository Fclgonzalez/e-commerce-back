package com.ecommerce.imobiliaria.Controller;

import com.ecommerce.imobiliaria.Models.Imovel;
import com.ecommerce.imobiliaria.Services.ImovelService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/imobil")
@RestController
@AllArgsConstructor
public class ImovelController {

    private ImovelService imovelService;

    @GetMapping("/imoveis")
    public List<Imovel> mostrarTodosImoveis(){
        List<Imovel> imoveis = imovelService.mostrarTodosImoveis();
        return imoveis;
    }

    @GetMapping("/imoveis/{idImove}")
    public ResponseEntity<Imovel> mostrarImovelById(@PathVariable Integer idImovel){
        Imovel imovel = imovelService.mostrarImovelById(idImovel);
        return ResponseEntity.ok().body(imovel);
    }

    @GetMapping("/imoveisContratoAluguel")
    public List<Imovel> mostrarImovelContratoAluguel(@RequestParam("contratoAluguel") Boolean contratoAluguel){
        List<Imovel> imoveis = imovelService.mostrarImovelContratoAluguel(contratoAluguel);
        return imoveis;
    }
}
