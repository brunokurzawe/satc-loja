package com.satc.satcloja.service;

import com.satc.satcloja.enterprise.NotFoundException;
import com.satc.satcloja.enterprise.ValidationException;
import com.satc.satcloja.model.Locacao;
import com.satc.satcloja.model.QServico;
import com.satc.satcloja.model.Servico;
import com.satc.satcloja.repository.LocacaoRepository;
import com.satc.satcloja.repository.ServicoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocacaoService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LocacaoRepository repository;

    public Locacao salvar(Locacao entity) {
        return repository.save(entity);
    }

    public List<Locacao> buscaTodos(String filter) {
        return repository.findAll(filter, Locacao.class);
    }

    public Page<Locacao> buscaTodos(String filter, Pageable pageable) {
        return repository.findAll(filter, Locacao.class, pageable);
    }

    public Locacao buscaPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Locacao alterar(Long id, Locacao entity) {
        Optional<Locacao> existingOptional = repository.findById(id);
        if (existingOptional.isEmpty()) {
            throw new NotFoundException("Produto não encontrado!");
        }

        Locacao existingServico = existingOptional.get();

        modelMapper.map(entity, existingServico);

        return repository.save(existingServico);
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }
}

