package com.ecommerce.imobiliaria.Registro;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistroRequest {

    private String nome;
    private String username;
    private String password;
    private String identificacao;

}
