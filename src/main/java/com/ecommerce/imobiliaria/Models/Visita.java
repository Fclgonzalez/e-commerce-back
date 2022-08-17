package com.ecommerce.imobiliaria.Models;

import com.ecommerce.imobiliaria.Models.Enums.StatusVisita;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Visita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVisita;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false)
    private LocalDate dataVisita;

    @JsonFormat(pattern = "HH:mm")
    @Column(nullable = false)
    private LocalTime horarioVisita;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private StatusVisita statusVisita;

    @ManyToOne
    @JoinColumn(name = "idImovel", nullable = false)
    private Imovel imovel;

    @ManyToOne
    @JoinColumn(name = "idConsumidor", nullable = false)
    private User userConsumidor;

    @ManyToOne
    @JoinColumn(name = "idVendedor", nullable = false)
    private User userVendedor;

}
