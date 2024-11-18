package com.example.Kanban.model;

import java.util.Comparator;

public enum KanbanPrioridade {
    baixa(0),
    media(1),
    alta(2);

    private int valor;
    private KanbanPrioridade(int valor) {
        this.valor = valor;
    }
    public int getValor() {
        return valor;
    }

    public static Comparator<KanbanPrioridade> comparadorDePrioridade = new Comparator<>() {

        public int compare(KanbanPrioridade o1, KanbanPrioridade o2) {
            return o1.getValor() - o2.getValor();
        }

    };
}
