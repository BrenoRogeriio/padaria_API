package com.services;

import com.domains.Encomenda;
import com.domains.Produto;
import com.domains.enums.StatusEncomenda;
import com.dtos.EncomendaDTO;
import com.exceptions.ObjectNotFoundException;
import com.repositories.EncomendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EncomendaService {

    @Autowired
    private EncomendaRepository repo;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private ConfeiteiroService confeiteiroService;

    public Encomenda buscarPorId(Long id) {
        return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Encomenda não encontrada! ID: " + id));
    }

    public List<Encomenda> listarTodos() { return repo.findAll(); }

    public Encomenda insert(Encomenda obj) {
        obj.setId(null);
        obj.setDataHora(LocalDateTime.now());
        obj.setStatus(StatusEncomenda.PENDENTE);


        double soma = 0.0;
        if(obj.getProdutos() != null) {
            for(Produto p : obj.getProdutos()) {
                soma += p.getPreco();
            }
        }
        obj.setValorTotal(soma);

        return repo.save(obj);
    }

    public Encomenda update(Encomenda obj) {
        Encomenda newObj = buscarPorId(obj.getId());
        newObj.setStatus(obj.getStatus());
        newObj.setValorTotal(obj.getValorTotal());
        return repo.save(newObj);
    }

    public void delete(Long id) {
        buscarPorId(id);
        repo.deleteById(id);
    }

    public Encomenda fromDTO(EncomendaDTO objDto) {
        Encomenda enc = new Encomenda(objDto.getId(), objDto.getDataHora(), objDto.getValorTotal(), StatusEncomenda.toEnum(objDto.getStatus()), null, null);
        enc.setEvento(eventoService.buscarPorId(objDto.getEventoId()));
        enc.setConfeiteiro(confeiteiroService.buscarPorId(objDto.getConfeiteiroId()));
        return enc;
    }
}