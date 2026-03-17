package com.dtos;

import com.domains.Evento;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDate;

public class EventoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;
    private String tipo;
    private Long clienteId;

    public EventoDTO() {
    }

    public EventoDTO(Evento obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.data = obj.getData();
        this.tipo = obj.getTipo();
        this.clienteId = obj.getCliente().getId();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
}