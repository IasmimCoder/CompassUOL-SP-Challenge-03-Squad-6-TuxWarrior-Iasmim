package br.com.compassuol.pb.challenge.products.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compassuol.pb.challenge.products.model.dto.RoleDTO;
import br.com.compassuol.pb.challenge.users.service.RoleService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/roles")
public class RoleController {
    
    @Autowired
    private RoleService service;
    
    @PostMapping
    public ResponseEntity<RoleDTO> save(@Valid @RequestBody RoleDTO dto) {
        return ResponseEntity.status(201).body(service.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/role_id/{id}")
    public ResponseEntity<RoleDTO> findById(@PathVariable("id") String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/role_name/{name}")
    public ResponseEntity<RoleDTO> findByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(service.findByName(name));
    }
}
