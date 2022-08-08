package com.ecommerce.imobiliaria.Services;

import com.ecommerce.imobiliaria.Models.Endereco;
import com.ecommerce.imobiliaria.Repositories.EnderecoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EnderecoService {

    private EnderecoRepository enderecoRepository;

    public List<Endereco> mostrarTodosEnderecos() {
        return enderecoRepository.findAll();
    }

    public Endereco mostrarUmEnderecoPeloId(Integer idEndereco) {
        Optional<Endereco> endereco = enderecoRepository.findById(idEndereco);
        return endereco.orElseThrow();
    }

    public Endereco cadastrarEndereco(Endereco endereco) {
        endereco.setIdEndereco(null);
        return enderecoRepository.save(endereco);
    }

    public Endereco editarEndereco(Endereco endereco, Integer idEndereco) {
        return enderecoRepository.save(endereco);
    }

}
