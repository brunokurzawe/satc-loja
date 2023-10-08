package com.satc.satcloja.service;

import com.querydsl.core.types.Predicate;
import com.satc.satcloja.enterprise.NotFoundException;
import com.satc.satcloja.enterprise.ValidationException;
import com.satc.satcloja.model.Produto;
import com.satc.satcloja.model.QProduto;
import com.satc.satcloja.repository.ProdutoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProdutoRepository repository;

    public Produto salvar(Produto entity) {

        if (entity.getValorUnitario() < entity.getPrecoCompra()) {
            throw new ValidationException("O valor unitário não pode ser menor que o preço de compra do produto!");
        }

        if (!repository.findAll(QProduto.produto.descricao.eq(entity.getDescricao())).isEmpty()) {
            throw new ValidationException("Já existe um produto com essa descrição cadastrado!");
        }

        return repository.save(entity);
    }

    public List<Produto> buscaTodos(Predicate predicate) {
        return repository.findAll(predicate);
    }

    public Produto buscaPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Produto alterar(Long id, Produto entity) {
        Optional<Produto> existingProdutoOptional = repository.findById(id);
        if (existingProdutoOptional.isEmpty()) {
            throw new NotFoundException("Produto não encontrado");
        }

        Produto existingProduto = existingProdutoOptional.get();

        modelMapper.map(entity, existingProduto);

        return repository.save(existingProduto);
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }
}


