// Classe Tarefa representa uma entidade no banco de dados
package com.example.t4td0.model;

import jakarta.persistence.*;

@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(name = "codigo_criacao")
    private String codigoCriacao;

    // Construtor padrão que gera um código de criação
    public Tarefa() {
        this.codigoCriacao = gerarCodigoCriacao();
    }

    // Construtor que inicializa a tarefa com um título
    public Tarefa(String titulo) {
        this.titulo = titulo;
        this.codigoCriacao = gerarCodigoCriacao();
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCodigoCriacao() {
        return codigoCriacao;
    }

    public void setCodigoCriacao(String codigoCriacao) {
        this.codigoCriacao = codigoCriacao;
    }

    // Método para gerar um código de criação
    private String gerarCodigoCriacao() {
        return "COD-" + System.currentTimeMillis();
    }
}