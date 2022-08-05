package com.ecommerce.imobiliaria.Models.Enums;

public enum TipoRole {
    ADMIN("Admin"),
    CONSUMIDOR("Consumidor"),
    VENDEDOR("Vendedor");

    private String opcao;

    TipoRole(String opcao){
        this.opcao = opcao;
    }

    public String getOpcao() {
        return opcao;
    }
}
