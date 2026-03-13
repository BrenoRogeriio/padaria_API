package com.dtos;

import com.domains.Confeiteiro;
import java.io.Serializable;

public class ConfeiteiroDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String especialidade;

    public ConfeiteiroDTO() {
    }

    public ConfeiteiroDTO(Confeiteiro obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.especialidade = obj.getEspecialidade();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }
}