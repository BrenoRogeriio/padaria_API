package com.services;

import com.domains.Produto;
import com.dtos.ProdutoDTO;
import com.exceptions.ObjectNotFoundException;
import com.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repo;

    public Produto buscarPorId(Long id) {
        return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado! ID: " + id));
    }

    public List<Produto> listarTodos() { return repo.findAll(); }

    public Produto insert(Produto obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    public Produto update(Produto obj) {
        Produto newObj = buscarPorId(obj.getId());
        newObj.setDescricao(obj.getDescricao());
        newObj.setPreco(obj.getPreco());
        return repo.save(newObj);
    }

    public void delete(Long id) {
        buscarPorId(id);
        repo.deleteById(id);
    }

    public Produto fromDTO(ProdutoDTO objDto) {
        return new Produto(objDto.getId(), objDto.getDescricao(), objDto.getPreco());
    }
}