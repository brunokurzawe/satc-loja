package com.satc.satcloja.resource;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilder;
import com.satc.satcloja.enterprise.NotFoundException;
import com.satc.satcloja.model.Cliente;
import com.satc.satcloja.repository.ClienteRepository;
import com.satc.satcloja.resource.representation.ClienteDTO;
import com.satc.satcloja.resource.representation.Filter;
import com.satc.satcloja.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController extends AbstractController {

    @Autowired
    private ClienteService service;

    @Autowired
    private ClienteRepository repository;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid Cliente entity) {
        Cliente save = service.salvar(entity);
        return ResponseEntity.created(URI.create("/api/clientes/" + entity.getId())).body(save);
    }

    @GetMapping
    public ResponseEntity findAll(@RequestParam(required = false) String filter,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {

        List<Filter> filters = Filter.fromQueryString(filter);

        System.out.println("filters: " + filters);


        BooleanBuilder predicate = new BooleanBuilder();

        filters.stream().forEach(fil -> {
            ClienteDTO dto = new ClienteDTO();
            Path path = dto.expressions().get(fil.getPropriedade());

            PathBuilder<Object> campoPath = new PathBuilder<>(path.getType(), path.getMetadata());

            System.out.println(campoPath);
            System.out.println(campoPath.getType());

            predicate.and(Expressions.booleanTemplate("{0} = {1}", campoPath, getTipo(campoPath.getType(), fil.getValor())));


        });

        System.out.println(predicate);

        List<Cliente> all = repository.findAll(predicate);



        return ResponseEntity.ok(all);
    }

    public static Expression getTipo(Class<?> tipoCampo, String parte) {
        if (tipoCampo == Integer.class || tipoCampo == int.class) {
            return Expressions.constant(Integer.parseInt(parte));
        } else if (tipoCampo == Double.class || tipoCampo == double.class) {
            return Expressions.constant(Double.parseDouble(parte));
        } else if (tipoCampo == LocalDate.class) {
            return Expressions.constant(LocalDate.parse(parte));
        } else if (tipoCampo.isEnum()) {
            return Expressions.constant(Enum.valueOf((Class<Enum>) tipoCampo, parte));
        }
        return Expressions.constant(parte);
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        Cliente produto = service.buscaPorId(id);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Cliente entity) {
        try {
            Cliente alterado = service.alterar(id, entity);
            return ResponseEntity.ok().body(alterado);
        } catch (NotFoundException nfe) {
            return ResponseEntity.noContent().build();
        }
    }
}




