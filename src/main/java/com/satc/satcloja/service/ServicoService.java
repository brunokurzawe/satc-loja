package com.satc.satcloja.service;

import com.satc.satcloja.enterprise.NotFoundException;
import com.satc.satcloja.enterprise.ValidationException;
import com.satc.satcloja.model.QServico;
import com.satc.satcloja.model.Servico;
import com.satc.satcloja.repository.ServicoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ServicoRepository repository;

    public Servico salvar(Servico entity) {

        if (!repository.findAll(QServico.servico.descricao.eq(entity.getDescricao())).isEmpty()) {
            throw new ValidationException("Já existe um serviço com essa descrição cadastrado!");
        }

        return repository.save(entity);
    }

    public List<Servico> buscaTodos(String filter) {
        return repository.findAll(filter, Servico.class);
    }

    public Page<Servico> buscaTodos(String filter, Pageable pageable) {
        return repository.findAll(filter, Servico.class, pageable);
    }

    public Servico buscaPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Servico alterar(Long id, Servico entity) {
        Optional<Servico> existingServicoOptional = repository.findById(id);
        if (existingServicoOptional.isEmpty()) {
            throw new NotFoundException("Produto não encontrado!");
        }

        Servico existingServico = existingServicoOptional.get();

        modelMapper.map(entity, existingServico);

        return repository.save(existingServico);
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }
}
