package com.resources;

import com.domains.Encomenda;
import com.dtos.EncomendaDTO;
import com.services.EncomendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/encomendas")
public class EncomendaResource {

    @Autowired
    private EncomendaService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<EncomendaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(new EncomendaDTO(service.buscarPorId(id)));
    }

    @GetMapping
    public ResponseEntity<List<EncomendaDTO>> listarTodos() {
        List<Encomenda> list = service.listarTodos();
        List<EncomendaDTO> listDto = list.stream().map(obj -> new EncomendaDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public ResponseEntity<EncomendaDTO> insert(@RequestBody EncomendaDTO objDto) {
        Encomenda obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(new EncomendaDTO(obj));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EncomendaDTO> update(@PathVariable Long id, @RequestBody EncomendaDTO objDto) {
        Encomenda obj = service.fromDTO(objDto);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.ok().body(new EncomendaDTO(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}