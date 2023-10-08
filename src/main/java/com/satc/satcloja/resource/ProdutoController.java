package com.satc.satcloja.resource;

import com.querydsl.core.BooleanBuilder;
import com.satc.satcloja.enterprise.BooleanBuilderUtil;
import com.satc.satcloja.enterprise.NotFoundException;
import com.satc.satcloja.model.Produto;
import com.satc.satcloja.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/produtos")
public class ProdutoController extends AbstractController {

    @Autowired
    private ProdutoService service;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid Produto entity) {
        Produto save = service.salvar(entity);
        return ResponseEntity.created(URI.create("/api/produtos/" + entity.getId())).body(save);
    }

    @GetMapping
    public ResponseEntity findAll(@RequestParam(required = false) String filter) {
        BooleanBuilder booleanBuilder = BooleanBuilderUtil.buildPredicateFromFilter(filter, Produto.class);
        List<Produto> produtos = service.buscaTodos(booleanBuilder);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        Produto produto = service.buscaPorId(id);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Produto entity) {
        try {
            Produto alterado = service.alterar(id, entity);
            return ResponseEntity.ok().body(alterado);
        } catch (NotFoundException nfe) {
            return ResponseEntity.noContent().build();
        }
    }
}



