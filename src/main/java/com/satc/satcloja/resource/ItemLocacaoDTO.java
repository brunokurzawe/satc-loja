package com.satc.satcloja.resource;

import com.satc.satcloja.model.ItemLocacao;

import java.util.List;
import java.util.stream.Collectors;

public class ItemLocacaoDTO {
    private ProdutoDTO produto;
    private Double valorUnitario;
    private Double quantidade;
    private Double desconto;

    // Getters e Setters para os atributos

    public ProdutoDTO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoDTO produto) {
        this.produto = produto;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    // Métodos para conversão
    public static ItemLocacaoDTO fromEntity(ItemLocacao itemLocacao) {
        ItemLocacaoDTO dto = new ItemLocacaoDTO();
        dto.setProduto(ProdutoDTO.fromEntity(itemLocacao.getProduto()));
        dto.setValorUnitario(itemLocacao.getValorUnitario());
        dto.setQuantidade(itemLocacao.getQuantidade());
        dto.setDesconto(itemLocacao.getDesconto());
        return dto;
    }

    public ItemLocacao toEntity() {
        ItemLocacao itemLocacao = new ItemLocacao();
        itemLocacao.setProduto(this.getProduto().toEntity());
        itemLocacao.setValorUnitario(this.getValorUnitario());
        itemLocacao.setQuantidade(this.getQuantidade());
        itemLocacao.setDesconto(this.getDesconto());
        return itemLocacao;
    }

    public static List<ItemLocacaoDTO> fromEntity(List<ItemLocacao> itensLocacao) {
        return itensLocacao.stream().map(ItemLocacaoDTO::fromEntity).collect(Collectors.toList());
    }
}
