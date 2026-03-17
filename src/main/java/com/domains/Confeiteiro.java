package com.domains;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Confeiteiro implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String especialidade;

    public Confeiteiro() {
    }

    public Confeiteiro(Long id, String nome, String especialidade) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Confeiteiro confeiteiro = (Confeiteiro) o;
        return Objects.equals(id, confeiteiro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}