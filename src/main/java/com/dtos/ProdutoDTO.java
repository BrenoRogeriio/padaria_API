package com.dtos;

import com.domains.Produto;
import java.io.Serializable;

public class ProdutoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String descricao;
    private Double preco;

    public ProdutoDTO() {
    }

    public ProdutoDTO(Produto obj) {
        this.id = obj.getId();
        this.descricao = obj.getDescricao();
        this.preco = obj.getPreco();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }
}