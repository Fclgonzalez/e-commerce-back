package com.ecommerce.imobiliaria.Models.Enums;

public enum FinalidadeImovel {

    COMERCIAL("Comercial"),
    CORPORATIVA("Corporativa"),
    INDUSTRIAL("Industrial"),
    RESIDENCIAL("Residencial"),
    RURAL("Rural"),
    TEMPORADA("Temporada");

    private String opcao;

     FinalidadeImovel(String opcao){
        this.opcao = opcao;
    }

    public String getFinalidade() {
        return opcao;
    }


}
