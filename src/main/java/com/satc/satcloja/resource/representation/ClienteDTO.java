package com.satc.satcloja.resource.representation;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;
import com.satc.satcloja.model.Cliente;
import com.satc.satcloja.model.QCliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ClienteDTO extends AbstractDTO {
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
    public static ClienteDTO fromEntity(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setTelefone(cliente.getTelefone());
        dto.setEndereco(cliente.getEndereco());
        dto.setEmail(cliente.getEmail());
        return dto;
    }

    public Cliente toEntity() {
        Cliente cliente = new Cliente();
        cliente.setId(this.getId());
        cliente.setNome(this.getNome());
        cliente.setTelefone(this.getTelefone());
        cliente.setEndereco(this.getEndereco());
        cliente.setEmail(this.getEmail());
        return cliente;
    }

    public static List<ClienteDTO> fromEntity(List<Cliente> clientes) {
        return clientes.stream().map(ClienteDTO::fromEntity).collect(Collectors.toList());
    }

    public static Page<ClienteDTO> fromEntity(Page<Cliente> clientes) {
        List<ClienteDTO> clientesFind = clientes.stream().map(ClienteDTO::fromEntity).collect(Collectors.toList());
        return new PageImpl<>(clientesFind, clientes.getPageable(), clientes.getTotalElements());
    }

//    @Override
//    public Map<String, Path> expressions() {
//        Map<String, Path> expressions = new HashMap<>();
//        expressions.put("id", QCliente.cliente.id);
//        expressions.put("nome", QCliente.cliente.nome);
//        expressions.put("cidade.id", QCliente.cliente.id);
//        expressions.put("telefone", QCliente.cliente.telefone);
//
//        expressions.putAll(super.getDefaultExpressions(Cliente.class));
//
//        System.out.println(expressions);
//
//        return expressions;
//    }
}
