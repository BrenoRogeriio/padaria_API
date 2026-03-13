package com.domains.enums;

public enum StatusEncomenda {

    PENDENTE(0, "Pendente"),
    PREPARANDO(1, "Preparando"),
    PRONTA(2, "Pronta para Retirada"),
    ENTREGUE(3, "Entregue");

    private Integer codigo;
    private String descricao;

    private StatusEncomenda(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static StatusEncomenda toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (StatusEncomenda x : StatusEncomenda.values()) {
            if (cod.equals(x.getCodigo())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Status inválido! Código: " + cod);
    }
}