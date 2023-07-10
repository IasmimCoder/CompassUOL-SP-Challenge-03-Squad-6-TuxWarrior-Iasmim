package br.com.compassuol.pb.challenge.products.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.compassuol.pb.challenge.products.model.dto.CreateUserDTO;
import br.com.compassuol.pb.challenge.products.model.dto.UpdateUserDTO;
import br.com.compassuol.pb.challenge.products.model.dto.UserDTO;
import br.com.compassuol.pb.challenge.products.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService service;

    @Operation(summary = "create user")
    @PostMapping
    public ResponseEntity<UserDTO> save(@Valid @RequestBody CreateUserDTO dto) {
        return ResponseEntity.status(201).body(service.save(dto));
    }

    @Operation(summary = "update by id user")
    @PutMapping("{id}")
    public ResponseEntity<UserDTO> update(@PathVariable("id") String id, @Valid @RequestBody UpdateUserDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(summary = "get all user")
    @GetMapping
    public ResponseEntity<Page<UserDTO>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int linesPerPage,
            @RequestParam(defaultValue = "ASC") String direction,
            @RequestParam(defaultValue = "firstName") String orderBy) {
        Page<UserDTO> responseDto = service.findAll(
                page,
                linesPerPage,
                orderBy,
                direction);
        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "get by id user")
    @GetMapping("{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "delete user")
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
