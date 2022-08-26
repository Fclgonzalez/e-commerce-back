package com.ecommerce.imobiliaria.Models;

import com.ecommerce.imobiliaria.Models.Enums.FinalidadeImovel;
import com.ecommerce.imobiliaria.Models.Enums.TipoImovel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode
@ToString
public class Imovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idImovel;

    @Column
    @NotEmpty
    private String titulo;

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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "imovel_caracteristicas",
            joinColumns = @JoinColumn(name = "imovel_id_imovel"),
            inverseJoinColumns = @JoinColumn(name = "caracteristicas_id"))
    private Set<Caracteristica> caracteristicas = new HashSet<>();

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idEndereco", unique = true)
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "idVendedor", nullable = false)
    private User userVendedor;

    private Boolean inativo = false;
}
