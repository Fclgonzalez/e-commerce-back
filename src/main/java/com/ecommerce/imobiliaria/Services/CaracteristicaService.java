package com.ecommerce.imobiliaria.Services;


import com.ecommerce.imobiliaria.Models.Caracteristica;
import com.ecommerce.imobiliaria.Models.Imovel;
import com.ecommerce.imobiliaria.Repositories.CaracteristicaRepository;
import com.ecommerce.imobiliaria.Repositories.ImovelRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Builder
public class CaracteristicaService {

    private CaracteristicaRepository caracteristicaRepository;

    private ImovelRepository imovelRepository;



    public List<Caracteristica> mostrarCaracteristicas() {
        return caracteristicaRepository.findAll();
    }


    public Caracteristica save(Caracteristica caracteristica) {
        caracteristica.setId(null);
        return caracteristicaRepository.save(caracteristica);
    }


    public void delete(Integer id) {
        caracteristicaRepository.deleteById(id);
    }


    public void addCaracteristicaToImovel(Integer idImovel, Integer idCarac) {
        Imovel imovel = imovelRepository.findById(idImovel).get();
        Caracteristica caracteristica = caracteristicaRepository.findById(idCarac).get();
        imovel.getCaracteristicas().add(caracteristica);
    }

}