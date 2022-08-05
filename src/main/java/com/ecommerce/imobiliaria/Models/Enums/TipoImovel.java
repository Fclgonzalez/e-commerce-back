package com.ecommerce.imobiliaria.Models.Enums;

public enum TipoImovel {

    ANDAR_CORPORATIVO("Andar Corporativo"),
    APARTAMENTO("Apartamento"),
    ARMAZEM("Armazém"),
    CASA("Casa"),
    CASA_COMERCIAL("Casa Comercial"),
    CASA_DE_VILA("Casa de Vila"),
    CHACARA("Chácara"),
    COBERTURA("Cobertura"),
    CONDOMINIO("Condomínio"),
    DEPOSITO("Depósito"),
    FAZENDA("Fazenda"),
    FLAT("Flat"),
    GALPAO("Galpão"),
    GARAGEM("Garagem"),
    HOTEL("Hotel"),
    LAJE_CORPORATIVA("Laje Corporativa"),
    LOJA("Loja"),
    LOTE("Lote"),
    MOTEL("Motel"),
    PREDIO_INTEIRO("Prédio Inteiro"),
    PONTO_COMERCIAL("Ponto Comercial"),
    POUSADA("Pousada"),
    SALA("Sala"),
    SALAO("Salão"),
    SITIO("Sítio"),
    TERRENO("Terreno");



    private String opcao;

    TipoImovel(String opcao){
        this.opcao = opcao;
    }

    public String getOpcao() {
        return opcao;
    }
}
