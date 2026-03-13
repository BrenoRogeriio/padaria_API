package com.services;

import com.domains.Evento;
import com.dtos.EventoDTO;
import com.exceptions.ObjectNotFoundException;
import com.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository repo;

    @Autowired
    private ClienteService clienteService;

    public Evento buscarPorId(Long id) {
        return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Evento não encontrado! ID: " + id));
    }

    public List<Evento> listarTodos() { return repo.findAll(); }

    public Evento insert(Evento obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    public Evento update(Evento obj) {
        Evento newObj = buscarPorId(obj.getId());
        newObj.setNome(obj.getNome());
        newObj.setData(obj.getData());
        newObj.setTipo(obj.getTipo());
        return repo.save(newObj);
    }

    public void delete(Long id) {
        buscarPorId(id);
        repo.deleteById(id);
    }

    public Evento fromDTO(EventoDTO objDto) {
        Evento ev = new Evento(objDto.getId(), objDto.getNome(), objDto.getData(), objDto.getTipo(), null);
        ev.setCliente(clienteService.buscarPorId(objDto.getClienteId()));
        return ev;
    }
}