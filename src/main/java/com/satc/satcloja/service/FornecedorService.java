package com.satc.satcloja.service;

import com.satc.satcloja.enterprise.NotFoundException;
import com.satc.satcloja.model.Fornecedor;
import com.satc.satcloja.repository.FornecedorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FornecedorRepository repository;

    public Fornecedor salvar(Fornecedor entity) {
        return repository.save(entity);
    }

    public List<Fornecedor> buscaTodos(String filter) {
        return repository.findAll(filter, Fornecedor.class);
    }

    public Page<Fornecedor> buscaTodos(String filter, Pageable pageable) {
        return repository.findAll(filter, Fornecedor.class, pageable);
    }

    public Fornecedor buscaPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Fornecedor alterar(Long id, Fornecedor entity) {
        Optional<Fornecedor> existingProdutoOptional = repository.findById(id);
        if (existingProdutoOptional.isEmpty()) {
            throw new NotFoundException("Cliente não encontrado!");
        }

        Fornecedor existingProduto = existingProdutoOptional.get();

        modelMapper.map(entity, existingProduto);

        return repository.save(existingProduto);
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }
}
