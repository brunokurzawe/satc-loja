package com.satc.satcloja.service;

import com.satc.satcloja.enterprise.NotFoundException;
import com.satc.satcloja.model.Compra;
import com.satc.satcloja.repository.CompraRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompraService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CompraRepository repository;

    public Compra salvar(Compra entity) {
        return repository.save(entity);
    }

    public List<Compra> buscaTodos(String filter) {
        return repository.findAll(filter, Compra.class);
    }

    public Page<Compra> buscaTodos(String filter, Pageable pageable) {
        return repository.findAll(filter, Compra.class, pageable);
    }

    public Compra buscaPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Compra alterar(Long id, Compra entity) {
        Optional<Compra> existingOptional = repository.findById(id);
        if (existingOptional.isEmpty()) {
            throw new NotFoundException("Compra não encontrado!");
        }

        Compra existingServico = existingOptional.get();

        modelMapper.map(entity, existingServico);

        return repository.save(existingServico);
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }
}