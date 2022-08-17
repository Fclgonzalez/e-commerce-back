package com.ecommerce.imobiliaria.Controller;


import com.ecommerce.imobiliaria.Models.Visita;
import com.ecommerce.imobiliaria.Repositories.VisitaRepository;
import com.ecommerce.imobiliaria.Services.VisitaService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/imobil")
@RestController
@AllArgsConstructor
public class VisitaController {

    private final VisitaService visitaService;
    private final VisitaRepository visitaRepository;



    @GetMapping("/visitas")
    public List<Visita> getAllVisitas(){
        return visitaService.getAllVisitas();
    }

    //getVisitaById
    @GetMapping("/visitas/{id}")
    public Visita getVisitaById(@PathVariable Integer id){
        return visitaService.getVisitaById(id);
    }

    //getVisitasByImovelId
    @GetMapping("/visitas/imovel/{id}")
    public List<Visita> getVisitasByImovelId(@PathVariable Integer id){
        return visitaService.getVisitaByImovelId(id);
    }

    //getVisitasByUserId
    @GetMapping("/visitas/consumidor/{id}")
    public List<Visita> getByIdConsumidor(@PathVariable Integer id){
        return visitaService.findByidConsumidor(id);
    }

    //getByIdVendedor
    @GetMapping("/visitas/vendedor/{id}")
    public List<Visita> getByIdVendedor(@PathVariable Integer id){
        return visitaService.findByidVendedor(id);
    }

    //findByStatus
    @GetMapping("/visitas/status")
    public List<Visita> findByStatus(@RequestParam String status){
        return visitaService.findByStatus(status);
    }

    //findByStatusAndIdVendedor
    @GetMapping("/visitas/status/vendedor/{id}")
    public List<Visita> findByStatusAndIdVendedor(@RequestParam String status, @PathVariable Integer id){
        return visitaService.findByStatusAndIdVendedor(status, id);
    }

    //findByStatusAndIdConsumidor
    @GetMapping("/visitas/status/consumidor/{id}")
    public List<Visita> findByStatusAndIdConsumidor(@RequestParam String status, @PathVariable Integer id){
        return visitaService.findByStatusAndIdConsumidor(status, id);
    }
    //findVisitasPorMes
    @GetMapping("/visitas/mes")
    public List<Visita> findVisitasPorMes(){
        return visitaService.findVisitasPorMes();
    }

    //findTotalVisitas
    @GetMapping("/visitas/total")
    public Integer findTotalVisitas(){
        return visitaService.findTotalVisitas();
    }


    //saveVisita
    @PostMapping("/visitas/{idConsumidor}/{idImovel}")
    public ResponseEntity<Visita> saveVisita(@RequestBody Visita visita, @PathVariable Integer idConsumidor, @PathVariable Integer idImovel){
         visitaService.saveVisita(visita, idConsumidor, idImovel);
            return new ResponseEntity<>(visita, HttpStatus.CREATED);
    }


    //deleteVisita
    @DeleteMapping("/visitas/{id}")
    public ResponseEntity<?> deleteVisita(@PathVariable Integer id){
        visitaService.deleteVisita(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //updateVisita
    @PatchMapping("/visitas/{idVisita}")
    public ResponseEntity<Visita> updateVisita(@RequestBody Visita visita, @PathVariable Integer idVisita){
        visitaService.updateVisita(visita, idVisita);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @PatchMapping("/visitas/status/{id}")
    public ResponseEntity<Visita> setStatusVisita(@PathVariable Integer id, @RequestParam("status") String status){
        Optional<Visita> visitaUpdated = visitaRepository.findById(id);
          if(visitaUpdated.isPresent()){
                visitaService.setStatus(id, status);
                return new ResponseEntity<>(visitaUpdated.get(), HttpStatus.OK);
          } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
    }

}
