package com.example.Kanban.model;
import jakarta.persistence.*;
import java.time.LocalDate;

import static com.example.Kanban.model.KanbanStatus.A_FAZER;


@Entity
public class Kanban {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    @Column(nullable = false)
    private String titulo;

    private String descricao;
    private KanbanPrioridade prioridade;
    private LocalDate dataCriacao;
    private String dataLimite;
    private KanbanStatus status;

    public Kanban(){
        this.status = A_FAZER;
        this.prioridade = KanbanPrioridade.baixa;
    }

    @PrePersist
    protected void naCriacao(){
        this.dataCriacao = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String nome) {
        this.titulo = nome;
    }

    public String getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(String dataLimite) {
        this.dataLimite = dataLimite;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }


    public KanbanPrioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(KanbanPrioridade prioridade) {
        this.prioridade = prioridade;
    }

    public KanbanStatus getStatus() {
        return status;
    }

    public void setStatus(KanbanStatus status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Kanban{" +
                "\nid=" + id +
                "\n, titulo='" + titulo + '\'' +
                "\n, descricao='" + descricao + '\'' +
                "\n, prioridade=" + prioridade +
                "\n, dataCriacao=" + dataCriacao +
                "\n, dataLimite='" + dataLimite + '\'' +
                "\n, status=" + status +
                '}';
    }
}
