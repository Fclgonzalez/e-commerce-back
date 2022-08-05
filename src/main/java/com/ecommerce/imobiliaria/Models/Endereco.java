package com.ecommerce.imobiliaria.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="endereco")
public class Endereco {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY.IDENTITY)
    private Integer idEndereco;
    @Column (nullable = false, length = 100)
    private String logradouro;
    @Column (nullable = false)
    private Integer numero;

    @Column (nullable = true)
    private String complemento;
    @Column (nullable = false, length = 100)
    private String bairro;

    @Column (nullable = false,length = 8)
    private String cep;

    @Column (nullable = false, length = 100)
    private String cidade;
    @Column (nullable = false, length = 2)
    private String uf;


}
