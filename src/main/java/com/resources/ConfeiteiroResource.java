package com.resources;

import com.domains.Confeiteiro;
import com.dtos.ConfeiteiroDTO;
import com.services.ConfeiteiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/confeiteiros")
public class ConfeiteiroResource {

    @Autowired
    private ConfeiteiroService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ConfeiteiroDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(new ConfeiteiroDTO(service.buscarPorId(id)));
    }

    @GetMapping
    public ResponseEntity<List<ConfeiteiroDTO>> listarTodos() {
        List<Confeiteiro> list = service.listarTodos();
        List<ConfeiteiroDTO> listDto = list.stream().map(obj -> new ConfeiteiroDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public ResponseEntity<ConfeiteiroDTO> insert(@RequestBody ConfeiteiroDTO objDto) {
        Confeiteiro obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(new ConfeiteiroDTO(obj));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ConfeiteiroDTO> update(@PathVariable Long id, @RequestBody ConfeiteiroDTO objDto) {
        Confeiteiro obj = service.fromDTO(objDto);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.ok().body(new ConfeiteiroDTO(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}