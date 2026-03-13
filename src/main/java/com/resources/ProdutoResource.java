package com.resources;

import com.domains.Produto;
import com.dtos.ProdutoDTO;
import com.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(new ProdutoDTO(service.buscarPorId(id)));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarTodos() {
        List<Produto> list = service.listarTodos();
        List<ProdutoDTO> listDto = list.stream().map(obj -> new ProdutoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> insert(@RequestBody ProdutoDTO objDto) {
        Produto obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProdutoDTO(obj));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProdutoDTO> update(@PathVariable Long id, @RequestBody ProdutoDTO objDto) {
        Produto obj = service.fromDTO(objDto);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.ok().body(new ProdutoDTO(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}