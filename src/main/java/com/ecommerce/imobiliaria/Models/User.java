package com.ecommerce.imobiliaria.Models;

import com.ecommerce.imobiliaria.Models.Enums.TipoRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 20)
    private String identificacao;

    @Column(length = 20)
    private String celular;

    @Column(length = 20)
    private String telefone;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false)
    private LocalDateTime dataAtualizacao;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco", unique = true)
    private Endereco endereco;

    @Enumerated(EnumType.STRING)
    private TipoRole tipoRole;

//    @OneToMany(mappedBy = "user")
//    private List<Visitas> visitas = new ArrayList<>();

}
