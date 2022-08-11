package com.ecommerce.imobiliaria.Models;

import com.ecommerce.imobiliaria.Models.Enums.FinalidadeImovel;
import com.ecommerce.imobiliaria.Models.Enums.TipoImovel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Imovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idImovel;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false)
    private Date dataCriacao;

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

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Caracteristica> caracteristicas = new ArrayList<>();

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idEndereco", unique = true)
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "idVendedor", nullable = false)
    private User userVendedor;

    private Boolean inativo = true;
}
