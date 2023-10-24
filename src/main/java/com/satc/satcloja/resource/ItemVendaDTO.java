package com.satc.satcloja.resource;

import com.satc.satcloja.model.ItemVenda;

import java.util.List;
import java.util.stream.Collectors;

public class ItemVendaDTO {
    private ProdutoServicoDTO produtoServico;
    private Double valorUnitario;
    private Double quantidade;
    private Double desconto;

    public ProdutoServicoDTO getProdutoServico() {
        return produtoServico;
    }

    public void setProdutoServico(ProdutoServicoDTO produtoServico) {
        this.produtoServico = produtoServico;
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
    public static ItemVendaDTO fromEntity(ItemVenda itemVenda) {
        ItemVendaDTO dto = new ItemVendaDTO();
        dto.setProdutoServico(ProdutoServicoDTO.fromEntity(itemVenda.getProdutoServico()));
        dto.setValorUnitario(itemVenda.getValorUnitario());
        dto.setQuantidade(itemVenda.getQuantidade());
        dto.setDesconto(itemVenda.getDesconto());
        return dto;
    }

    public ItemVenda toEntity() {
        ItemVenda itemVenda = new ItemVenda();
        itemVenda.setVenda(this.toEntity().getVenda());
        itemVenda.setValorUnitario(this.getValorUnitario());
        itemVenda.setQuantidade(this.getQuantidade());
        itemVenda.setDesconto(this.getDesconto());
        return itemVenda;
    }

    public static List<ItemVendaDTO> fromEntity(List<ItemVenda> itensVenda) {
        return itensVenda.stream().map(ItemVendaDTO::fromEntity).collect(Collectors.toList());
    }

    public static List<ItemVenda> toEntity(List<ItemVendaDTO> dtos) {
        return dtos.stream().map(dto -> dto.toEntity()).collect(Collectors.toList());
    }
}
