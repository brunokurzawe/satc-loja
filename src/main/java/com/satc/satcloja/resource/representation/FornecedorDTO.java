package com.satc.satcloja.resource.representation;

import com.satc.satcloja.model.Fornecedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

public class FornecedorDTO {
    private Long id;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;

    // Getters e Setters
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Métodos de conversão
    public static FornecedorDTO fromEntity(Fornecedor fornecedor) {
        FornecedorDTO dto = new FornecedorDTO();
        dto.setId(fornecedor.getId());
        dto.setNome(fornecedor.getNome());
        dto.setTelefone(fornecedor.getTelefone());
        dto.setEndereco(fornecedor.getEndereco());
        dto.setEmail(fornecedor.getEmail());
        return dto;
    }

    public Fornecedor toEntity() {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(this.getId());
        fornecedor.setNome(this.getNome());
        fornecedor.setTelefone(this.getTelefone());
        fornecedor.setEndereco(this.getEndereco());
        fornecedor.setEmail(this.getEmail());
        return fornecedor;
    }

    public static List<FornecedorDTO> fromEntity(List<Fornecedor> fornecedores) {
        return fornecedores.stream().map(FornecedorDTO::fromEntity).collect(Collectors.toList());
    }

    public static Page<FornecedorDTO> fromEntity(Page<Fornecedor> fornecedores) {
        List<FornecedorDTO> fornecedoresFind = fornecedores.stream().map(FornecedorDTO::fromEntity).collect(Collectors.toList());
        return new PageImpl<>(fornecedoresFind, fornecedores.getPageable(), fornecedores.getTotalElements());
    }
}