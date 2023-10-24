package com.satc.satcloja.resource;

import com.satc.satcloja.model.FormaPagamento;
import com.satc.satcloja.model.Venda;
import com.satc.satcloja.resource.representation.ClienteDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

public class VendaDTO {
    private LocalDate dataVenda;
    private ClienteDTO cliente;
    private FormaPagamento formaPagamento;
    private String observacao;
    private List<ItemVendaDTO> itens;

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public List<ItemVendaDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemVendaDTO> itens) {
        this.itens = itens;
    }

    // Métodos para conversão
    public static VendaDTO fromEntity(Venda venda) {
        VendaDTO dto = new VendaDTO();
        dto.setDataVenda(venda.getDataVenda());
        dto.setCliente(ClienteDTO.fromEntity(venda.getCliente()));
        dto.setFormaPagamento(venda.getFormaPagamento());
        dto.setObservacao(venda.getObservacao());

        // Converte a lista de ItemVenda para List<ItemVendaDTO>
        dto.setItens(ItemVendaDTO.fromEntity(venda.getItens()));

        return dto;
    }

    public Venda toEntity() {
        Venda venda = new Venda();
        venda.setDataVenda(this.getDataVenda());
        venda.setCliente(this.getCliente().toEntity());
        venda.setFormaPagamento(this.getFormaPagamento());
        venda.setObservacao(this.getObservacao());


        return venda;
    }

    public static List<VendaDTO> fromEntity(List<Venda> vendas) {
        return vendas.stream().map(VendaDTO::fromEntity).collect(Collectors.toList());
    }

    public static Page<VendaDTO> fromEntity(Page<Venda> vendas) {
        List<VendaDTO> vendasFind = vendas.stream().map(VendaDTO::fromEntity).collect(Collectors.toList());
        Page<VendaDTO> vendasDTO = new PageImpl<>(vendasFind, vendas.getPageable(), vendas.getTotalElements());
        return vendasDTO;
    }
}

