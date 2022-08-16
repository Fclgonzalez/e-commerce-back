package com.ecommerce.imobiliaria.Services;

import com.ecommerce.imobiliaria.Models.Endereco;
import com.ecommerce.imobiliaria.Models.Imovel;
import com.ecommerce.imobiliaria.Models.User;
import com.ecommerce.imobiliaria.Repositories.EnderecoRepository;
import com.ecommerce.imobiliaria.Repositories.ImovelRepository;
import com.ecommerce.imobiliaria.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EnderecoService {

    private EnderecoRepository enderecoRepository;
    private UserRepository userRepository;
    private ImovelRepository imovelRepository;

    public List<Endereco> mostrarTodosEnderecos() {
        return enderecoRepository.findAll();
    }

    public Endereco mostrarUmEnderecoPeloId(Integer idEndereco) {
        Optional<Endereco> endereco = enderecoRepository.findById(idEndereco);
        return endereco.orElseThrow( () -> new EntityNotFoundException("Endereco não encontrado"));
    }

    public Endereco cadastrarEnderecoUsuario(Endereco endereco, Integer idUser) {
        endereco.setIdEndereco(null);
        endereco = enderecoRepository.save(endereco);
        Optional<User> user = userRepository.findById(idUser);
        user.get().setEndereco(endereco);
        userRepository.save(user.get());
        return endereco;
    }
    public Endereco cadastrarEnderecoImovel(Endereco endereco, Integer idImovel) {
        endereco.setIdEndereco(null);
        endereco = enderecoRepository.save(endereco);
        Optional<Imovel> imovel = imovelRepository.findById(idImovel);
        imovel.get().setEndereco(endereco);
        imovelRepository.save(imovel.get());
        return endereco;
    }

    public Endereco editarEndereco(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

}
