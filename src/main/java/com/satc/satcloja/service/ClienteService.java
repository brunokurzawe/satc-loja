package com.satc.satcloja.service;

import com.satc.satcloja.enterprise.NotFoundException;
import com.satc.satcloja.model.Cliente;
import com.satc.satcloja.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ClienteRepository repository;

    public Cliente salvar(Cliente entity) {
        return repository.save(entity);
    }

    public List<Cliente> buscaTodos(String filter) {
        return repository.findAll(filter, Cliente.class);
    }

    public Page<Cliente> buscaTodos(String filter, Pageable pageable) {
        return repository.findAll(filter, Cliente.class, pageable);
    }

    public Cliente buscaPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Cliente alterar(Long id, Cliente entity) {
        Optional<Cliente> existingProdutoOptional = repository.findById(id);
        if (existingProdutoOptional.isEmpty()) {
            throw new NotFoundException("Cliente não encontrado!");
        }

        Cliente existingProduto = existingProdutoOptional.get();

        modelMapper.map(entity, existingProduto);

        return repository.save(existingProduto);
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }
}
