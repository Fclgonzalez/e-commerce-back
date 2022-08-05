package com.ecommerce.imobiliaria.Models;

import com.ecommerce.imobiliaria.Models.Enums.FinalidadeImovel;
import com.ecommerce.imobiliaria.Models.Enums.TipoImovel;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;

@Entity
public class Imovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idImovel;

    @NumberFormat(pattern = "#.##0,00")
    @Column(nullable = false)
    private double dataCriacao;

    @Column
    private Boolean contratoAluguel = false;

    @Column
    private Boolean contratoVenda = false;

    @Column(nullable = false)
    private double valorAluguel;

    @Column(nullable = false)
    private double valorVenda;

    @Column(nullable = false)
    private double area;

    @Column(nullable = true)
    private String descricao;

    @Column
    private Integer quartos;

    @Column
    private Integer suite;

    @Column
    private Integer banheiros;

    @Column
    private Integer vagas;

    @Enumerated(EnumType.STRING)
    private FinalidadeImovel finalidadeImovel;

    @Enumerated(EnumType.STRING)
    private TipoImovel tipoImovel;
}
