package com.ecommerce.imobiliaria.Controller;


import com.ecommerce.imobiliaria.Models.Caracteristica;
import com.ecommerce.imobiliaria.Services.CaracteristicaService;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;

@RequestMapping("/imobil/")
@RestController
@Builder
public class CaracteristicaController {

    private CaracteristicaService caracteristicaService;

    @GetMapping("/caracts")
    public List<Caracteristica> mostrarCaracteristicas(){
        return caracteristicaService.mostrarCaracteristicas();
    }

    @PostMapping("/caracts")
    public ResponseEntity<Caracteristica> save(@RequestBody Caracteristica caracteristica) {
      caracteristica = caracteristicaService.save(caracteristica);
        URI novaUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(caracteristica.getId()).toUri();
            return ResponseEntity.created(novaUri).body(caracteristica);
    }

    @DeleteMapping("/caracts/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        caracteristicaService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/caracts/{idImovel}/{idCarac}")
    public ResponseEntity<Void> addCaracteristicaToImovel(@PathVariable Integer idImovel, @PathVariable Integer idCarac) {
        caracteristicaService.addCaracteristicaToImovel(idImovel, idCarac);
        return ResponseEntity.ok().build();
    }
}
