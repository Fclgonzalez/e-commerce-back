package com.ecommerce.imobiliaria.Models;

import com.ecommerce.imobiliaria.Models.Enums.FinalidadeImovel;
import com.ecommerce.imobiliaria.Models.Enums.TipoImovel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Imovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idImovel;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false)
    private LocalDate dataCriacao;

    @Column
    private boolean contratoAluguel = false;

    @Column
    private boolean contratoVenda = false;

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
