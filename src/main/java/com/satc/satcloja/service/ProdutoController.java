package com.satc.satcloja.service;

import com.satc.satcloja.model.Produto;
import com.satc.satcloja.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @PostMapping
    public ResponseEntity create(@RequestBody Produto entity) {
        Produto save = repository.save(entity);
        return ResponseEntity.created(URI.create("/api/produtos/"+entity.getId())).body(save);
    }

    @GetMapping
    public List<Produto> lista() {
        return repository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}



