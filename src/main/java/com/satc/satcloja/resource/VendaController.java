package com.satc.satcloja.resource;

import com.satc.satcloja.enterprise.NotFoundException;
import com.satc.satcloja.model.Venda;
import com.satc.satcloja.resource.representation.VendaDTO;
import com.satc.satcloja.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/vendas")
public class VendaController extends AbstractController {

    @Autowired
    private VendaService service;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid Venda entity) {
        Venda save = service.salvar(entity);
        return ResponseEntity.created(URI.create("/api/vendas/" + entity.getId())).body(save);
    }

    @GetMapping
    public ResponseEntity findAll(@RequestParam(required = false) String filter,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {
        Page<Venda> vendas = service.buscaTodos(filter, PageRequest.of(page, size));
        Page<VendaDTO> vendasDTOS = VendaDTO.fromEntity(vendas);
        return ResponseEntity.ok(vendasDTOS);
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        Venda produto = service.buscaPorId(id);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Venda entity) {
        try {
            Venda alterado = service.alterar(id, entity);
            return ResponseEntity.ok().body(alterado);
        } catch (NotFoundException nfe) {
            return ResponseEntity.noContent().build();
        }
    }
}
