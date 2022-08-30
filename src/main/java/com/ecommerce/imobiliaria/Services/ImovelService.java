package com.ecommerce.imobiliaria.Services;

import com.ecommerce.imobiliaria.Models.*;
import com.ecommerce.imobiliaria.Models.Enums.FinalidadeImovel;
import com.ecommerce.imobiliaria.Models.Enums.TipoImovel;
import com.ecommerce.imobiliaria.Models.Imovel;
import com.ecommerce.imobiliaria.Models.ImovelTemporario;
import com.ecommerce.imobiliaria.Models.User;
import com.ecommerce.imobiliaria.Repositories.ImovelRepository;
import com.ecommerce.imobiliaria.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ImovelService {

    ImovelRepository imovelRepository;
    UserRepository userRepository;
    private RoleService roleService;
    @PersistenceContext
    private EntityManager entityManager;

    /**
     *  Aprendemos Spring Data JPA (declara a query diretamente no metodo do repository atraves da annotation @Query)
     *
     *  O Hibernate fornece diferentes metodos para acessar os dados.
     *       Um desses metodos é a Criteria API, que  nos permite construir um objeto de consulta de critérios programaticamente
     *       onde podemos aplicar regras de filtragem e condições lógicas.
     *
     *   Usaremos convenções em vez de parametros:
     *          1.nunca enviará propriedade com valor nulo
     *          2.propriedades não usadas virão com zero ou ""
     *
     * @param imovel
     * @return
     */
    public List<Imovel> filtrar(Imovel imovel) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Imovel> query = builder.createQuery(Imovel.class);

        Root<Imovel> r = query.from(Imovel.class);
        Join<Imovel, Endereco> joinEnd = r.join("endereco", JoinType.LEFT);

        //-----------------------------------------------------------------
        // crio as listas de predicados ANDs e ORs
        //-----------------------------------------------------------------
        ArrayList<Predicate> predicadosPropEnderecoAND = new ArrayList();
        ArrayList<Predicate> predicadosCaracteristicasOR = new ArrayList();

        //-----------------------------------------------------------------
        //PROPRIEDADES DO IMOVEL
        //-----------------------------------------------------------------
        if (imovel.getValorAluguel() > 0)
            predicadosPropEnderecoAND.add(builder.greaterThanOrEqualTo(r.get("valorAluguel"), imovel.getValorAluguel()));
        if (imovel.getValorVenda() > 0)
            predicadosPropEnderecoAND.add(builder.greaterThanOrEqualTo(r.get("valorVenda"), imovel.getValorVenda()));
        if (imovel.getArea() > 0)
            predicadosPropEnderecoAND.add(builder.greaterThanOrEqualTo(r.get("area"), imovel.getArea()));
        if (imovel.getQuartos() > 0)
            predicadosPropEnderecoAND.add(builder.greaterThanOrEqualTo(r.get("quartos"), imovel.getQuartos()));
        if (imovel.getSuite() > 0)
            predicadosPropEnderecoAND.add(builder.greaterThanOrEqualTo(r.get("suite"), imovel.getSuite()));
        if (imovel.getBanheiros() > 0)
            predicadosPropEnderecoAND.add(builder.greaterThanOrEqualTo(r.get("banheiros"), imovel.getBanheiros()));
        if (imovel.getVagas() > 0)
            predicadosPropEnderecoAND.add(builder.greaterThanOrEqualTo(r.get("vagas"), imovel.getVagas()));

        predicadosPropEnderecoAND.add(builder.equal(r.get("finalidadeImovel"), imovel.getFinalidadeImovel()));
        predicadosPropEnderecoAND.add(builder.equal(r.get("tipoImovel"), imovel.getTipoImovel()));

        //-----------------------------------------------------------------
        //ENDERECO    OBS: UF é filtrada no front
        //-----------------------------------------------------------------
        if(imovel.getEndereco().getCidade().trim().length() > 0)
            predicadosPropEnderecoAND.add(builder.like(joinEnd.get("cidade"),  imovel.getEndereco().getCidade()));
        if(imovel.getEndereco().getBairro().trim().length() > 0)
            predicadosPropEnderecoAND.add(builder.like(joinEnd.get("bairro"),  imovel.getEndereco().getBairro()));

        //-----------------------------------------------------------------
        // CARACTERISTICAS
        //-----------------------------------------------------------------
        if (imovel.getCaracteristicas().size() > 0) {
            Join<Imovel, Caracteristica> joinCaracteristicas = r.join("caracteristicas", JoinType.LEFT);

            imovel.getCaracteristicas().forEach((Caracteristica c) -> {
                predicadosCaracteristicasOR.add(builder.equal(joinCaracteristicas.get("caracteristica"), c.getCaracteristica()));
            });

            // adiciono os predicados das caracteristicas na lista principal de predicados
            predicadosPropEnderecoAND.add(builder.or(predicadosCaracteristicasOR.toArray(new Predicate[0])));
        }

        //-----------------------------------------------------------------
        // Preparacao da Consulta
        //-----------------------------------------------------------------
        Predicate[] allPredicates = new Predicate[predicadosPropEnderecoAND.size()];
        predicadosPropEnderecoAND.toArray(allPredicates);

        // consulto
        query = query.where(allPredicates).distinct(true);

        return entityManager.createQuery(query).getResultList();
    }

    public Imovel preencherImovel(ImovelTemporario imovelTemporario){
        Imovel imovel = new Imovel();
        imovel.setIdImovel(imovelTemporario.getIdImovel());
        imovel.setTitulo(imovelTemporario.getTitulo());


        if(imovelTemporario.isContratoAluguel()){
            imovel.setContratoAluguel(imovelTemporario.isContratoAluguel());

        }
        if(imovelTemporario.isContratoVenda()){
            imovel.setContratoVenda(imovelTemporario.isContratoVenda());

        }
        imovel.setValorAluguel(imovelTemporario.getValorAluguel());
        imovel.setValorVenda(imovelTemporario.getValorVenda());
        imovel.setArea(imovelTemporario.getArea());
        if(imovelTemporario.getDescricao() != null){
            imovel.setDescricao(imovelTemporario.getDescricao());
        }
        imovel.setQuartos(imovelTemporario.getQuartos());
        imovel.setSuite(imovelTemporario.getSuite());
        imovel.setBanheiros(imovelTemporario.getBanheiros());
        imovel.setVagas(imovelTemporario.getVagas());
        if(imovelTemporario.getFinalidadeImovel() != null) {
            imovel.setFinalidadeImovel(FinalidadeImovel.valueOf(imovelTemporario.getFinalidadeImovel()));
        }
        if(imovelTemporario.getTipoImovel() != null) {
            imovel.setTipoImovel(TipoImovel.valueOf(imovelTemporario.getTipoImovel()));
        }
        imovel.setCaracteristicas(new HashSet<>(imovelTemporario.getCaracteristicas()));
        if(imovelTemporario.getEndereco() != null) {
            imovel.setEndereco(imovelTemporario.getEndereco());
        }
        imovel.setUserVendedor(imovelTemporario.getUserVendedor());
        return imovel;
    }

    //GETS

    public List<Imovel> mostrarTodosImoveis() {
        return imovelRepository.findAll();
    }

    public Imovel mostrarImovelById(Integer idImovel) {
        Optional<Imovel> imovel = imovelRepository.findById(idImovel);
        return imovel.orElseThrow();
    }
    public List<Imovel> mostrarImovelAtivo(Integer idVendedor){
        return imovelRepository.findByIdImovelAtivo(idVendedor);
    }
    public List<Imovel> mostrarImovelInativo(Integer idVendedor){
        return imovelRepository.findByIdImovelInativo(idVendedor);
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

   //findImoveisPerMOnth
    public List<Imovel> findImoveisPerMOnth(){
         return (List<Imovel>) imovelRepository.findImoveisPerMOnth();
    }
    //countImoveis
    public Integer countImoveis(){
        return imovelRepository.countImoveis();
    }

   //POST
   public Imovel cadastrarImovel(Imovel imovel, Integer idVendedor) {
       imovel.setDataCriacao(new Date());

       Optional<User> user = userRepository.findById(idVendedor);
       if (user.get().getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("CONSUMIDOR"))) {
           roleService.salvarRoleNoUser("VENDEDOR", user.get().getUsername());
       }
       imovel.setUserVendedor(user.get());
       return imovelRepository.save(imovel);
   }

   //DELETE
   public void excluirImovel(Integer idImovel) {
        imovelRepository.deleteById(idImovel);
   }

   //PUT
   public Imovel editarImovel(Imovel imovel, Integer idImovel){
        Imovel imovelSemAsAlteracoes = mostrarImovelById(idImovel);
        User user = imovelSemAsAlteracoes.getUserVendedor();
        Endereco endereco = imovelSemAsAlteracoes.getEndereco();
        imovel.setDataCriacao(imovelSemAsAlteracoes.getDataCriacao());
        imovel.setEndereco(endereco);
        imovel.setUserVendedor(user);
        return imovelRepository.save(imovel);
   }

   public Imovel inativarImovel(Integer idImovel, Boolean inativo){
        Imovel imovel = mostrarImovelById(idImovel);
        imovel.setInativo(inativo);
        return imovelRepository.save(imovel);
   }
}
