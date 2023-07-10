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

import br.com.compassuol.pb.challenge.products.model.dto.CreateProductDTO;
import br.com.compassuol.pb.challenge.products.model.dto.ProductDTO;
import br.com.compassuol.pb.challenge.products.model.dto.UpdateProductDTO;
import br.com.compassuol.pb.challenge.products.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @Operation(summary = "Create product")
    @PostMapping
    public ResponseEntity<ProductDTO> save(@Valid @RequestBody CreateProductDTO dto) {
        return ResponseEntity.status(201).body(service.save(dto));
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable("id") String id, @Valid @RequestBody UpdateProductDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(summary = "Get all product")
    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int linesPerPage,
            @RequestParam(defaultValue = "ASC") String direction,
            @RequestParam(defaultValue = "name") String orderBy) {
        Page<ProductDTO> responseDto = service.findAll(
                page,
                linesPerPage,
                orderBy,
                direction);
        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "Get by id product")
    @GetMapping("{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Delete by id product")
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
