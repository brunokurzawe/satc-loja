package com.satc.satcloja.resource;

import com.satc.satcloja.model.Compra;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

public class CompraDTO {
    private LocalDate dataCompra;
    private FornecedorDTO fornecedor;
    private String observacao;
    private List<ItemCompraDTO> itens;

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public FornecedorDTO getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(FornecedorDTO fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public List<ItemCompraDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemCompraDTO> itens) {
        this.itens = itens;
    }

    // Métodos para conversão
    public static CompraDTO fromEntity(Compra compra) {
        CompraDTO dto = new CompraDTO();
        dto.setDataCompra(compra.getDataCompra());
        dto.setFornecedor(FornecedorDTO.fromEntity(compra.getFornecedor()));
        dto.setObservacao(compra.getObservacao());

        // Converte a lista de ItemCompra para List<ItemCompraDTO>
        dto.setItens(ItemCompraDTO.fromEntity(compra.getItens()));

        return dto;
    }

    public Compra toEntity() {
        Compra compra = new Compra();
        compra.setDataCompra(this.getDataCompra());
        compra.setFornecedor(this.getFornecedor().toEntity());
        compra.setObservacao(this.getObservacao());
        return compra;
    }

    public static List<CompraDTO> fromEntity(List<Compra> compras) {
        return compras.stream().map(CompraDTO::fromEntity).collect(Collectors.toList());
    }

    public static Page<CompraDTO> fromEntity(Page<Compra> compras) {
        List<CompraDTO> comprasFind = compras.stream().map(CompraDTO::fromEntity).collect(Collectors.toList());
        Page<CompraDTO> comprasDTO = new PageImpl<>(comprasFind, compras.getPageable(), compras.getTotalElements());
        return comprasDTO;
    }
}
