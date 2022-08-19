package com.ecommerce.imobiliaria.Controller;


import com.ecommerce.imobiliaria.Models.Caracteristica;
import com.ecommerce.imobiliaria.Services.CaracteristicaService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;

@RequestMapping("/imobil")
@RestController
@AllArgsConstructor
public class CaracteristicaController {

    private CaracteristicaService caracteristicaService;

    //GET habilitado para ser acessado por todos

    @GetMapping("/caracts")
    public List<Caracteristica> mostrarCaracteristicas(){
        return caracteristicaService.mostrarCaracteristicas();
    }

    @GetMapping("/caracts/{idCarac}")
    public ResponseEntity<Caracteristica> mostrarCaracteristicaPeloId(@PathVariable Integer idCarac){
        Caracteristica caracteristica = caracteristicaService.mostrarCaracteristicaPeloId(idCarac);
        return ResponseEntity.ok().body(caracteristica);
    }


    @PreAuthorize("hasAnyAuthority('ADMIN','CONSUMIDOR','VENDEDOR')")
    @PostMapping("/caracts")
    public ResponseEntity<Caracteristica> saveNovaCaracteristica(@RequestBody Caracteristica caracteristica) {
      caracteristica = caracteristicaService.save(caracteristica);
        URI novaUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(caracteristica.getId()).toUri();
            return ResponseEntity.created(novaUri).body(caracteristica);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','CONSUMIDOR','VENDEDOR')")
    @DeleteMapping("/caracts/{id}")
    public ResponseEntity<Void> deleteCaracteristica(@PathVariable Integer id) {
        caracteristicaService.delete(id);
        return ResponseEntity.ok().build();
    }
//    @PreAuthorize("hasAnyAuthority('ADMIN','CONSUMIDOR','VENDEDOR')")
    @PostMapping("/caracts/{idImovel}/{idCarac}")
    public ResponseEntity<Void> addCaracteristicaToImovel(@PathVariable Integer idImovel, @PathVariable Integer idCarac) {
        caracteristicaService.addCaracteristicaToImovel(idImovel, idCarac);
        return ResponseEntity.ok().build();
    }
}
