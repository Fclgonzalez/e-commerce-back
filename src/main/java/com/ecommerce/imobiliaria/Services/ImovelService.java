package com.ecommerce.imobiliaria.Services;

import com.ecommerce.imobiliaria.Models.Imovel;
import com.ecommerce.imobiliaria.Models.User;
import com.ecommerce.imobiliaria.Repositories.ImovelRepository;
import com.ecommerce.imobiliaria.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ImovelService {

    ImovelRepository imovelRepository;

    UserRepository userRepository;

    //GETS

    public List<Imovel> mostrarTodosImoveis() {
        return imovelRepository.findAll();
    }

    public Imovel mostrarImovelById(Integer idImovel) {
        Optional<Imovel> imovel = imovelRepository.findById(idImovel);
        return imovel.orElseThrow();
    }

    public List<Imovel> mostrarImovelVendedor(Integer idVendedor) {
        Optional<User> user = userRepository.findById(idVendedor);
        return imovelRepository.findByIdUser(user.get().getIdUser());
    }

    public List<Imovel> mostrarImovelContratoAluguel(Boolean contratoAluguel){
        return imovelRepository.findByContratoAluguel(contratoAluguel = true);
    }

    public List<Imovel> mostrarImovelContratoVenda(Boolean contratoVenda){
        return imovelRepository.findByContratoVenda(contratoVenda = true);
    }

    public List<Imovel> mostrarImovelValorAluguel(Double valorAluguel){
        return imovelRepository.findByValorAluguel(valorAluguel);
    }

    public List<Imovel> mostrarImovelValorVenda(Double valorVenda){
        return imovelRepository.findByValorVenda(valorVenda);
    }

    public List<Imovel> mostrarImovelPelaArea(Double area){
        return imovelRepository.findByArea(area);
    }

    public List<Imovel> mostrarImovelQuarto(Integer quartos){
        return imovelRepository.findByQuartos(quartos);
    }

    public List<Imovel> mostrarImovelSuite(Integer suite){
        return imovelRepository.findBySuite(suite);
    }

    public List<Imovel> mostrarImovelBanheiro(Integer banheiros){
        return imovelRepository.findByBanheiros(banheiros);
    }

   public List<Imovel> mostrarImovelVaga(Integer vagas){
        return imovelRepository.findByVagas(vagas);
   }

   public List<Imovel> mostrarImovelFinalidade(String finalidadeImovel){
        return imovelRepository.findByFinalidadeImovel(finalidadeImovel);
   }

   public List<Imovel> mostrarImovelTipo(String tipoImovel){
        return imovelRepository.findByTipoImovel(tipoImovel);
   }

   //POST
   public Imovel cadastrarImovel(Imovel imovel, Integer idVendedor){
        imovel.setDataCriacao(new Date());
        Optional<User> user = userRepository.findById(idVendedor);
        imovel.setUserVendedor(user.get());
        return imovelRepository.save(imovel);
   }

   //DELETE
   public void excluirImovel(Integer idImovel) {
        imovelRepository.deleteById(idImovel);
   }


}
