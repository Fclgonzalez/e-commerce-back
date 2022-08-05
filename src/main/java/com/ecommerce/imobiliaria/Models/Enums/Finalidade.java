package com.ecommerce.imobiliaria.Models.Enums;

public enum Finalidade {

    COMERCIAL("Comercial"),
    CORPORATIVA("Corporativa"),
    INDUSTRIAL("Industrial"),
    RESIDENCIAL("Residencial"),
    RURAL("Rural"),
    TEMPORADA("Temporada");

    private String opcao;

     Finalidade (String opcao){
        this.opcao = opcao;
    }

    public String getFinalidade() {
        return opcao;
    }


}
