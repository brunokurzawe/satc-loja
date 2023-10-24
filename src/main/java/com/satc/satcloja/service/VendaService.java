package com.satc.satcloja.service;

import com.satc.satcloja.enterprise.NotFoundException;
import com.satc.satcloja.model.Venda;
import com.satc.satcloja.repository.VendaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private VendaRepository repository;

    public Venda salvar(Venda entity) {
        return repository.save(entity);
    }

    public List<Venda> buscaTodos(String filter) {
        return repository.findAll(filter, Venda.class);
    }

    public Page<Venda> buscaTodos(String filter, Pageable pageable) {
        return repository.findAll(filter, Venda.class, pageable);
    }

    public Venda buscaPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Venda alterar(Long id, Venda entity) {
        Optional<Venda> existingOptional = repository.findById(id);
        if (existingOptional.isEmpty()) {
            throw new NotFoundException("Venda não encontrado!");
        }

        Venda existingServico = existingOptional.get();

        modelMapper.map(entity, existingServico);

        return repository.save(existingServico);
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }
}
