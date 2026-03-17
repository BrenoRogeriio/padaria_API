package com.domains;

import com.domains.enums.StatusEncomenda;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Encomenda implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHora;
    private Double valorTotal;


    private Integer status;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "confeiteiro_id")
    private Confeiteiro confeiteiro;


    @ManyToMany
    @JoinTable(name = "ENCOMENDA_PRODUTO",
            joinColumns = @JoinColumn(name = "encomenda_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<Produto> produtos = new ArrayList<>();

    public Encomenda() {
    }

    public Encomenda(Long id, LocalDateTime dataHora, Double valorTotal, StatusEncomenda status, Evento evento, Confeiteiro confeiteiro) {
        this.id = id;
        this.dataHora = dataHora;
        this.valorTotal = valorTotal;
        this.status = (status == null) ? null : status.getCodigo();
        this.evento = evento;
        this.confeiteiro = confeiteiro;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    public Double getValorTotal() { return valorTotal; }
    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }

    public StatusEncomenda getStatus() {
        return StatusEncomenda.toEnum(status);
    }

    public void setStatus(StatusEncomenda status) {
        this.status = status.getCodigo();
    }

    public Evento getEvento() { return evento; }
    public void setEvento(Evento evento) { this.evento = evento; }
    public Confeiteiro getConfeiteiro() { return confeiteiro; }
    public void setConfeiteiro(Confeiteiro confeiteiro) { this.confeiteiro = confeiteiro; }
    public List<Produto> getProdutos() { return produtos; }
    public void setProdutos(List<Produto> produtos) { this.produtos = produtos; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Encomenda encomenda = (Encomenda) o;
        return Objects.equals(id, encomenda.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}