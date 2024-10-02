package com.uninassau.Api_spring.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tb_pessoas")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID pessoaId;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "nome")
    private  String nome;

    @Column(name = "idade")
    private int idade;

    @CreationTimestamp
    private Instant dataCriacao;

    @UpdateTimestamp
    private Instant dataAlteracao;

    public Pessoa() {
    }

    public Pessoa(UUID pessoaId, String cpf, String nome, int idade, Instant dataCriacao, Instant dataAlteracao) {
        this.pessoaId = pessoaId;
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
        this.dataCriacao = dataCriacao;
        this.dataAlteracao = dataAlteracao;
    }

    public UUID getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(UUID pessoaId) {
        this.pessoaId = pessoaId;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Instant dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Instant getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Instant dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
}
