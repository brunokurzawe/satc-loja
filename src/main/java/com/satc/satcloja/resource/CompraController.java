package com.satc.satcloja.resource;

import com.satc.satcloja.enterprise.NotFoundException;
import com.satc.satcloja.model.Compra;
import com.satc.satcloja.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/compras")
public class CompraController extends AbstractController {

    @Autowired
    private CompraService service;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid Compra entity) {
        Compra save = service.salvar(entity);
        return ResponseEntity.created(URI.create("/api/compras/" + entity.getId())).body(save);
    }

    @GetMapping
    public ResponseEntity findAll(@RequestParam(required = false) String filter,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {
        Page<Compra> clientes = service.buscaTodos(filter, PageRequest.of(page, size));
        Page<CompraDTO> clienteDTOS = CompraDTO.fromEntity(clientes);
        return ResponseEntity.ok(clienteDTOS);
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        Compra produto = service.buscaPorId(id);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Compra entity) {
        try {
            Compra alterado = service.alterar(id, entity);
            return ResponseEntity.ok().body(alterado);
        } catch (NotFoundException nfe) {
            return ResponseEntity.noContent().build();
        }
    }
}




