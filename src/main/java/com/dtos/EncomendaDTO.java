package com.dtos;

import com.domains.Encomenda;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDateTime;

public class EncomendaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataHora;

    private Double valorTotal;

    // Devolvemos o Integer para o Service não quebrar!
    private Integer status;
    private String statusDescricao;

    private Long eventoId;
    private String nomeEvento;

    private Long confeiteiroId;
    private String nomeConfeiteiro;

    public EncomendaDTO() {
    }

    public EncomendaDTO(Encomenda obj) {
        this.id = obj.getId();
        this.dataHora = obj.getDataHora();
        this.valorTotal = obj.getValorTotal();

        // Pega tanto o número quanto o texto
        this.status = obj.getStatus().getCodigo();
        this.statusDescricao = obj.getStatus().toString();

        this.eventoId = obj.getEvento().getId();
        this.nomeEvento = obj.getEvento().getNome();

        this.confeiteiroId = obj.getConfeiteiro().getId();
        this.nomeConfeiteiro = obj.getConfeiteiro().getNome();
    }

    // GETTERS E SETTERS COMPLETOS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    public Double getValorTotal() { return valorTotal; }
    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }

    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public String getStatusDescricao() { return statusDescricao; }
    public void setStatusDescricao(String statusDescricao) { this.statusDescricao = statusDescricao; }

    public Long getEventoId() { return eventoId; }
    public void setEventoId(Long eventoId) { this.eventoId = eventoId; }
    public String getNomeEvento() { return nomeEvento; }
    public void setNomeEvento(String nomeEvento) { this.nomeEvento = nomeEvento; }

    public Long getConfeiteiroId() { return confeiteiroId; }
    public void setConfeiteiroId(Long confeiteiroId) { this.confeiteiroId = confeiteiroId; }
    public String getNomeConfeiteiro() { return nomeConfeiteiro; }
    public void setNomeConfeiteiro(String nomeConfeiteiro) { this.nomeConfeiteiro = nomeConfeiteiro; }
}