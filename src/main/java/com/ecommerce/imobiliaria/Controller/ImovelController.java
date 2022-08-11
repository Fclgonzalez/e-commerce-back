package com.ecommerce.imobiliaria.Controller;

import com.ecommerce.imobiliaria.Models.Imovel;
import com.ecommerce.imobiliaria.Models.ImovelTemporario;
import com.ecommerce.imobiliaria.Services.ImovelService;
import com.ecommerce.imobiliaria.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping("/imobil")
@RestController
@AllArgsConstructor
public class ImovelController {

    private ImovelService imovelService;

    private UserService userService;

    @GetMapping("/imoveis")
    public List<Imovel> mostrarTodosImoveis(){
        List<Imovel> imoveis = imovelService.mostrarTodosImoveis();
        return imoveis;
    }

    @GetMapping("/imoveis/{idImovel}")
    public ResponseEntity<Imovel> mostrarImovelById(@PathVariable Integer idImovel){
        Imovel imovel = imovelService.mostrarImovelById(idImovel);
        return ResponseEntity.ok().body(imovel);
    }

    @GetMapping("/imoveisVendedor")
    public List<Imovel> mostrarImovelVendedor(@RequestParam("idVendedor") Integer idVendedor){
        List<Imovel> imoveis = imovelService.mostrarImovelVendedor(idVendedor);
        return imoveis;
    }
    @GetMapping("/imoveisContratoAluguel")
    public List<Imovel> mostrarImovelContratoAluguel(@RequestParam("contratoAluguel") Boolean contratoAluguel){
        List<Imovel> imoveis = imovelService.mostrarImovelContratoAluguel(contratoAluguel);
        return imoveis;
    }

    @GetMapping("/imoveisContratoVenda")
    public List<Imovel> mostrarImovelContratoVenda(@RequestParam("contratoVenda") Boolean contratoVenda){
        List<Imovel> imoveis = imovelService.mostrarImovelContratoVenda(contratoVenda);
        return imoveis;
    }

    @GetMapping("/imoveisValorAluguel")
    public List<Imovel> mostrarImovelValorAluguel(@RequestParam("valorAluguelMax") Double valorAluguel){
        List<Imovel> imoveis = imovelService.mostrarImovelValorAluguel(valorAluguel);
        return imoveis;
    }

    @GetMapping("/imoveisValorVenda")
    public List<Imovel> mostrarImovelValorVenda(@RequestParam("valorVendaMax") Double valorVenda){
        List<Imovel> imoveis = imovelService.mostrarImovelValorVenda(valorVenda);
        return imoveis;
    }

    @GetMapping("/imoveisPelaArea")
    public List<Imovel> mostrarImovelPelaArea(@RequestParam("area") Double area){
        List<Imovel> imoveis = imovelService.mostrarImovelPelaArea(area);
        return imoveis;
    }

    @GetMapping("/imoveisPorQuarto")
    public List<Imovel> mostrarImovelQuarto(@RequestParam("quartos") Integer quartos){
        List<Imovel> imoveis = imovelService.mostrarImovelQuarto(quartos);
        return imoveis;
    }

    @GetMapping("/imoveisPorSuite")
    public List<Imovel> mostrarImovelSuite(@RequestParam("suite") Integer suite){
        List<Imovel> imoveis = imovelService.mostrarImovelSuite(suite);
        return imoveis;
    }

    @GetMapping("/imoveisPorBanheiro")
    public List<Imovel> mostrarImovelBanheiro(@RequestParam("banheiros") Integer banheiros){
        List<Imovel> imoveis = imovelService.mostrarImovelBanheiro(banheiros);
        return imoveis;
    }

    @GetMapping("/imoveisPorVaga")
    public List<Imovel> mostrarImovelVaga(@RequestParam("vagas") Integer vagas){
        List<Imovel> imoveis = imovelService.mostrarImovelVaga(vagas);
        return imoveis;
    }

    @GetMapping("/imoveisPorFinalidade")
    public List<Imovel> mostrarImovelFinalidade(@RequestParam("finalidadeImovel") String finalidadeImovel){
        List<Imovel> imoveis = imovelService.mostrarImovelFinalidade(finalidadeImovel);
        return imoveis;
    }

    @GetMapping("/imoveisPorTipo")
    public List<Imovel> mostrarImovelTipo(@RequestParam("tipoImovel") String tipoImovel){
        List<Imovel> imoveis = imovelService.mostrarImovelTipo(tipoImovel);
        return imoveis;
    }

    @PostMapping("/imoveis/{idVendedor}")
    public ResponseEntity<Imovel> cadastrarImovel(@PathVariable Integer idVendedor,
                                                  @RequestBody ImovelTemporario imovelTemporario){
        Imovel imovel = new Imovel();
        imovel = imovel.preencherImovel(imovelTemporario);
//        imovel.setUserVendedor(userService.);
        imovel = imovelService.cadastrarImovel(imovel, idVendedor);
        URI novaURI = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}")
                .buildAndExpand(imovel.getIdImovel()).toUri();
        return ResponseEntity.created(novaURI).body(imovel);
    }

    @DeleteMapping("/imoveis/{idImovel}")
    public ResponseEntity<Void> excluirImovel(@PathVariable Integer idImovel){
        imovelService.excluirImovel(idImovel);
        return ResponseEntity.noContent().build();
    }
}
