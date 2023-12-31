package com.example.ubsconnect.resource;

import com.example.ubsconnect.model.Agendamentos;
import com.example.ubsconnect.model.Paciente;
import com.example.ubsconnect.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoController extends AbstractController{

    @Autowired
    private AgendamentoService service;

    @PostMapping
    public ResponseEntity create(@RequestBody Agendamentos entity) {
        Agendamentos save = service.salvar(entity);
        return ResponseEntity.created(URI.create("/api/agendamentos/" + entity.getId())).body(save);
    }
    @GetMapping
    public ResponseEntity findAll() {
        List<Agendamentos> agendamentos = service.buscaTodos();
        return ResponseEntity.ok(agendamentos);
    }
    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        Agendamentos agendamentos = service.buscaPorId(id);
        return ResponseEntity.ok(agendamentos);
    }
    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Agendamentos entity) {
        Agendamentos alterado = service.alterar(id, entity);
        return ResponseEntity.ok().body(alterado);
    }
}
