package com.satc.satcloja.resource;

import com.satc.satcloja.model.ItemCompra;


import java.util.List;
import java.util.stream.Collectors;


public class ItemCompraDTO {
    private ProdutoDTO produto;
    private Double valorUnitario;
    private Double quantidade;

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

    // Métodos para conversão
    public static ItemCompraDTO fromEntity(ItemCompra itemCompra) {
        ItemCompraDTO dto = new ItemCompraDTO();
        dto.setProduto(ProdutoDTO.fromEntity(itemCompra.getProduto()));
        dto.setValorUnitario(itemCompra.getValorUnitario());
        dto.setQuantidade(itemCompra.getQuantidade());
        return dto;
    }

    public ItemCompra toEntity() {
        ItemCompra itemCompra = new ItemCompra();
        itemCompra.setCompra(this.toEntity().getCompra());
        itemCompra.setProduto(this.getProduto().toEntity());
        itemCompra.setValorUnitario(this.getValorUnitario());
        itemCompra.setQuantidade(this.getQuantidade());
        return itemCompra;
    }

    public static List<ItemCompraDTO> fromEntity(List<ItemCompra> itensCompra) {
        return itensCompra.stream().map(ItemCompraDTO::fromEntity).collect(Collectors.toList());
    }

    public static List<ItemCompra> toEntity(List<ItemCompraDTO> dtos) {
        return dtos.stream().map(dto -> dto.toEntity()).collect(Collectors.toList());
    }
}
