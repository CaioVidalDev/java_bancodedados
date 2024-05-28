// Classe TarefaExcluida representa uma entidade no banco de dados
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

    // Construtor padrão que gera um código de exclusão
    public TarefaExcluida() {
        this.codigoExclusao = gerarCodigoExclusao();
    }

    // Construtor que inicializa a tarefa excluída com um título
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

    // Método para gerar um código de exclusão
    private String gerarCodigoExclusao() {
        return "EXC-" + System.currentTimeMillis();
    }
}