package com.example.t4td0.model;

import jakarta.persistence.*;


@Entity
public class TarefaExcluida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(name = "codigo_exclusao")
    private String codigoExclusao;

    public TarefaExcluida() {
        this.codigoExclusao = gerarCodigoExclusao();
    }

    public TarefaExcluida(String titulo) {
        this.titulo = titulo;
        this.codigoExclusao = gerarCodigoExclusao();
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

    public String getCodigoExclusao() {
        return codigoExclusao;
    }

    public void setCodigoExclusao(String codigoExclusao) {
        this.codigoExclusao = codigoExclusao;
    }

    private String gerarCodigoExclusao() {
        // Aqui você pode implementar a lógica para gerar o código de exclusão, se necessário
        return "EXC-" + System.currentTimeMillis(); // Exemplo simples usando o timestamp atual
    }
}

