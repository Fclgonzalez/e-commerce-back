package com.ecommerce.imobiliaria.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ImovelTemporario {

    private Integer idImovel;

    private Date dataCriacao;

    private boolean contratoAluguel = false;

    private boolean contratoVenda = false;

    private double valorAluguel;

    private double valorVenda;

    private double area;

    private String descricao;

    private Integer quartos;

    private Integer suite;

    private Integer banheiros;

    private Integer vagas;

    private String finalidadeImovel;

    private String tipoImovel;

    private Collection<Caracteristica> caracteristicas = new ArrayList<>();

    private Endereco endereco;

    private User userVendedor;
}
