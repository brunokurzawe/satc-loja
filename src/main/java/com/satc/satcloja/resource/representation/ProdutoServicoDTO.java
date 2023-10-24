package com.satc.satcloja.resource.representation;

import com.satc.satcloja.model.ItemVendavel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoServicoDTO {
    private String descricao;
    private Double valorUnitario;
    private Boolean estocavel;

    // Getters e Setters para os atributos

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Boolean getEstocavel() {
        return estocavel;
    }

    public void setEstocavel(Boolean estocavel) {
        this.estocavel = estocavel;
    }

    // Métodos para conversão
    public static ProdutoServicoDTO fromEntity(ItemVendavel produtoServico) {
        ProdutoServicoDTO dto = new ProdutoServicoDTO();
        dto.setDescricao(produtoServico.getDescricao());
        dto.setValorUnitario(produtoServico.getValorUnitario());
        dto.setEstocavel(produtoServico.getEstocavel());
        return dto;
    }



    public static List<ProdutoServicoDTO> fromEntity(List<ItemVendavel> produtosServicos) {
        return produtosServicos.stream().map(ProdutoServicoDTO::fromEntity).collect(Collectors.toList());
    }

    public static Page<ProdutoServicoDTO> fromEntity(Page<ItemVendavel> produtosServicos) {
        List<ProdutoServicoDTO> produtosServicosFind = produtosServicos.stream().map(ProdutoServicoDTO::fromEntity).collect(Collectors.toList());
        Page<ProdutoServicoDTO> produtosServicosDTO = new PageImpl<>(produtosServicosFind, produtosServicos.getPageable(), produtosServicos.getTotalElements());
        return produtosServicosDTO;
    }
}
