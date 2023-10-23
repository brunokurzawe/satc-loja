package com.satc.satcloja.resource;

import com.satc.satcloja.enterprise.NotFoundException;
import com.satc.satcloja.model.Fornecedor;
import com.satc.satcloja.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorController extends AbstractController {

    @Autowired
    private FornecedorService service;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid Fornecedor entity) {
        Fornecedor save = service.salvar(entity);
        return ResponseEntity.created(URI.create("/api/fornecedores/" + entity.getId())).body(save);
    }

    @GetMapping
    public ResponseEntity findAll(@RequestParam(required = false) String filter,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {
        Page<Fornecedor> fornecedores = service.buscaTodos(filter, PageRequest.of(page, size));
        Page<FornecedorDTO> fornecedoreDTOS = FornecedorDTO.fromEntity(fornecedores);
        return ResponseEntity.ok(fornecedoreDTOS);
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        Fornecedor fornecedor = service.buscaPorId(id);
        return ResponseEntity.ok(fornecedor);
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Fornecedor entity) {
        try {
            Fornecedor alterado = service.alterar(id, entity);
            return ResponseEntity.ok().body(alterado);
        } catch (NotFoundException nfe) {
            return ResponseEntity.noContent().build();
        }
    }
}
