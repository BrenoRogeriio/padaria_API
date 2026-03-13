package com.services;

import com.domains.Cliente;
import com.dtos.ClienteDTO;
import com.exceptions.ObjectNotFoundException;
import com.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public Cliente buscarPorId(Long id) {
        return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! ID: " + id));
    }

    public List<Cliente> listarTodos() { return repo.findAll(); }

    public Cliente insert(Cliente obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    public Cliente update(Cliente obj) {
        Cliente newObj = buscarPorId(obj.getId());
        newObj.setNome(obj.getNome());
        newObj.setCpf(obj.getCpf());
        newObj.setTelefone(obj.getTelefone());
        return repo.save(newObj);
    }

    public void delete(Long id) {
        buscarPorId(id);
        repo.deleteById(id);
    }

    public Cliente fromDTO(ClienteDTO objDto) {
        return new Cliente(objDto.getId(), objDto.getNome(), objDto.getCpf(), objDto.getTelefone());
    }
}