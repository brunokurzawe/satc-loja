package com.satc.satcloja.resource.representation;

import com.satc.satcloja.model.Locacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class LocacaoDTO {
    private LocalDate dataLocacao;
    private LocalDate dataDevolucao;
    private ClienteDTO cliente;
    private String endereco;
    private String observacao;
    private List<ItemLocacaoDTO> itens;

    // Getters e Setters para os atributos

    public LocalDate getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(LocalDate dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public List<ItemLocacaoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemLocacaoDTO> itens) {
        this.itens = itens;
    }

    // Métodos para conversão
    public static LocacaoDTO fromEntity(Locacao locacao) {
        LocacaoDTO dto = new LocacaoDTO();
        dto.setDataLocacao(locacao.getDataLocacao());
        dto.setDataDevolucao(locacao.getDataDevolucao());
        dto.setCliente(ClienteDTO.fromEntity(locacao.getCliente()));
        dto.setEndereco(locacao.getEndereco());
        dto.setObservacao(locacao.getObservacao());

        // Converte a lista de ItemLocacao para List<ItemLocacaoDTO>
        dto.setItens(ItemLocacaoDTO.fromEntity(locacao.getItens()));

        return dto;
    }

    public Locacao toEntity() {
        Locacao locacao = new Locacao();
        locacao.setDataLocacao(this.getDataLocacao());
        locacao.setDataDevolucao(this.getDataDevolucao());
        locacao.setCliente(this.getCliente().toEntity());
        locacao.setEndereco(this.getEndereco());
        locacao.setObservacao(this.getObservacao());

        return locacao;
    }

    public static List<LocacaoDTO> fromEntity(List<Locacao> locacoes) {
        return locacoes.stream().map(LocacaoDTO::fromEntity).collect(Collectors.toList());
    }

    public static Page<LocacaoDTO> fromEntity(Page<Locacao> locacoes) {
        List<LocacaoDTO> locacoesFind = locacoes.stream().map(servico -> fromEntity(servico)).collect(Collectors.toList());
        Page<LocacaoDTO> locacoesDTO = new PageImpl<>(locacoesFind, locacoes.getPageable(), locacoes.getTotalElements());
        return locacoesDTO;
    }
}
