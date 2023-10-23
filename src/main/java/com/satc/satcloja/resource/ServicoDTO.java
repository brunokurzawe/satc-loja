package com.satc.satcloja.resource;

import com.satc.satcloja.model.Produto;
import com.satc.satcloja.model.Servico;
import com.satc.satcloja.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

public class ServicoDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static ServicoDTO fromEntity(Servico servico) {
        ServicoDTO dto = new ServicoDTO();
        dto.setId(servico.getId());
        dto.setDescricao(servico.getDescricao());
        return dto;
    }

    public Servico toEntity() {
        Servico servico = new Servico();
        servico.setId(this.getId());
        servico.setDescricao(this.getDescricao());
        return servico;
    }

    public static List<ServicoDTO> fromEntity(List<Servico> servicos) {
        return servicos.stream().map(produto -> fromEntity(produto)).collect(Collectors.toList());
    }

    public static Page<ServicoDTO> fromEntity(Page<Servico> servicos) {
        List<ServicoDTO> servicosFind = servicos.stream().map(servico -> fromEntity(servico)).collect(Collectors.toList());
        Page<ServicoDTO> produtosDTO = new PageImpl<>(servicosFind, servicos.getPageable(), servicos.getTotalElements());
        return produtosDTO;
    }

}