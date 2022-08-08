package com.ecommerce.imobiliaria.Models.Enums;

public enum StatusVisita {

    PENDENTE("Pendente"),
    CONFIRMADO("Confirmado"),
    CONCLUIDO("Concluido"),
    CANCELADO("Cancelado");

    private String status;

    StatusVisita(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
