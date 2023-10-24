package com.satc.satcloja.resource;

import com.satc.satcloja.enterprise.NotFoundException;
import com.satc.satcloja.model.Locacao;
import com.satc.satcloja.resource.representation.LocacaoDTO;
import com.satc.satcloja.service.LocacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/locacoes")
public class LocacaoController extends AbstractController {

    @Autowired
    private LocacaoService service;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid Locacao entity) {
        Locacao save = service.salvar(entity);
        return ResponseEntity.created(URI.create("/api/locacoes/" + entity.getId())).body(save);
    }

    @GetMapping
    public ResponseEntity findAll(@RequestParam(required = false) String filter,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {
        Page<Locacao> servicos = service.buscaTodos(filter, PageRequest.of(page, size));
        Page<LocacaoDTO> servicosDTOS = LocacaoDTO.fromEntity(servicos);
        return ResponseEntity.ok(servicosDTOS);
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        Locacao produto = service.buscaPorId(id);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Locacao entity) {
        try {
            Locacao alterado = service.alterar(id, entity);
            return ResponseEntity.ok().body(alterado);
        } catch (NotFoundException nfe) {
            return ResponseEntity.noContent().build();
        }
    }
}



