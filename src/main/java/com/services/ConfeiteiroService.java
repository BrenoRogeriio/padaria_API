package com.services;

import com.domains.Confeiteiro;
import com.dtos.ConfeiteiroDTO;
import com.exceptions.ObjectNotFoundException;
import com.repositories.ConfeiteiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfeiteiroService {

    @Autowired
    private ConfeiteiroRepository repo;

    public Confeiteiro buscarPorId(Long id) {
        return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Confeiteiro não encontrado! ID: " + id));
    }

    public List<Confeiteiro> listarTodos() { return repo.findAll(); }

    public Confeiteiro insert(Confeiteiro obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    public Confeiteiro update(Confeiteiro obj) {
        Confeiteiro newObj = buscarPorId(obj.getId());
        newObj.setNome(obj.getNome());
        newObj.setEspecialidade(obj.getEspecialidade());
        return repo.save(newObj);
    }

    public void delete(Long id) {
        buscarPorId(id);
        repo.deleteById(id);
    }

    public Confeiteiro fromDTO(ConfeiteiroDTO objDto) {
        return new Confeiteiro(objDto.getId(), objDto.getNome(), objDto.getEspecialidade());
    }
}