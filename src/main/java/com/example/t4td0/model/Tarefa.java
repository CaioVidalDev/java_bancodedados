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

    public Tarefa() {
        this.codigoCriacao = gerarCodigoCriacao();
    }

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

    private String gerarCodigoCriacao() {
        // Aqui você pode implementar a lógica para gerar o código de criação, se necessário
        return "COD-" + System.currentTimeMillis(); // Exemplo simples usando o timestamp atual
    }
}
