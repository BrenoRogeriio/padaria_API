package com.resources;

import com.domains.Evento;
import com.dtos.EventoDTO;
import com.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/eventos")
public class EventoResource {

    @Autowired
    private EventoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<EventoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(new EventoDTO(service.buscarPorId(id)));
    }

    @GetMapping
    public ResponseEntity<List<EventoDTO>> listarTodos() {
        List<Evento> list = service.listarTodos();
        List<EventoDTO> listDto = list.stream().map(obj -> new EventoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public ResponseEntity<EventoDTO> insert(@RequestBody EventoDTO objDto) {
        Evento obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(new EventoDTO(obj));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EventoDTO> update(@PathVariable Long id, @RequestBody EventoDTO objDto) {
        Evento obj = service.fromDTO(objDto);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.ok().body(new EventoDTO(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}