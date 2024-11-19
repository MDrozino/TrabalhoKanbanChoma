package com.example.Kanban.service;
import com.example.Kanban.model.Kanban;
import com.example.Kanban.model.KanbanPrioridade;
import com.example.Kanban.model.KanbanStatus;
import com.example.Kanban.repository.KanbanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.Kanban.model.KanbanStatus.*;


@Service
public class KanbanService {
    @Autowired
    private KanbanRepository kanbanRepository;

    public List<Kanban> listAll() {
        return kanbanRepository.findAll();
    }


    public Kanban salvar(Kanban kanban) {
        return kanbanRepository.save(kanban);
    }

    public Kanban findById(Long id) {
        return kanbanRepository.findById(id).orElse(null);
    }


    public void delete(Long id) {
        kanbanRepository.deleteById(id);
        System.out.println("Tarefa " + id + "foi deletada com sucesso!");
    }


    public Kanban editar(@PathVariable Long id, @RequestBody Kanban novaTarefa) {
        Kanban tarefa = kanbanRepository.findById(id).orElse(null);

        assert tarefa != null;
        tarefa.setTitulo(novaTarefa.getTitulo());
        tarefa.setDescricao(novaTarefa.getDescricao());
        tarefa.setPrioridade(novaTarefa.getPrioridade());
        tarefa.setDataLimite(novaTarefa.getDataLimite());


        return kanbanRepository.save(tarefa);
    }

    public void mover(Long id) {
        Kanban tarefa = kanbanRepository.findById(id).orElse(null);
        if (tarefa == null) {
            throw new RuntimeException("Tarefa não encontrada");
        }

        if (tarefa.getStatus() == A_FAZER) {
            tarefa.setStatus(EM_PROGRESSO);
            kanbanRepository.save(tarefa);
            System.out.println(tarefa.getTitulo() + "atualizado com sucesso para 'Executando'!");

        } else if (tarefa.getStatus() == EM_PROGRESSO) {
            tarefa.setStatus(CONCLUIDO);
            kanbanRepository.save(tarefa);
            System.out.println(tarefa.getTitulo() + "atualizado com sucesso para 'Pronto'!");

        } else {
            System.out.println("A tarefa não pode trocar seu status...");
        }

    }


    public List<Kanban> listarPorStatus(int status) {
        List<Kanban> tarefas = kanbanRepository.findAll();
        List<Kanban> tarefasOrdenadas = new ArrayList<Kanban>();
        for (Kanban tarefa : tarefas) {
            switch (status) {
                case 1 -> {
                    if (tarefa.getStatus() == KanbanStatus.A_FAZER) {
                        tarefasOrdenadas.add(tarefa);
                    }
                }
                case 2 -> {
                    if (tarefa.getStatus() == KanbanStatus.EM_PROGRESSO) {
                        tarefasOrdenadas.add(tarefa);
                    }
                }
                case 3 -> {
                    if (tarefa.getStatus() == KanbanStatus.CONCLUIDO) {
                        tarefasOrdenadas.add(tarefa);
                    }
                }
            }
        }
        tarefasOrdenadas.sort((t1, t2) -> KanbanPrioridade.comparadorDePrioridade.compare(t1.getPrioridade(), t2.getPrioridade()));
        return tarefasOrdenadas.isEmpty() ? null : tarefasOrdenadas;
    }

    public List<Kanban> listarPorPrioridade(int prioridade) {
        List<Kanban> tarefas = kanbanRepository.findAll();
        List<Kanban> tarefasOrdenadas = new ArrayList<Kanban>();
        for (Kanban tarefa : tarefas) {
            switch (prioridade) {
                case 0 -> {
                    if (tarefa.getPrioridade() == KanbanPrioridade.baixa) {
                        tarefasOrdenadas.add(tarefa);
                    }
                }
                case 1 -> {
                    if (tarefa.getPrioridade() == KanbanPrioridade.media) {
                        tarefasOrdenadas.add(tarefa);
                    }
                }
                case 2 -> {
                    if (tarefa.getPrioridade() == KanbanPrioridade.alta) {
                        tarefasOrdenadas.add(tarefa);
                    }
                }
            }
        }
        tarefasOrdenadas.sort((t1, t2) -> KanbanPrioridade.comparadorDePrioridade.compare(t1.getPrioridade(), t2.getPrioridade()));
        return tarefasOrdenadas.isEmpty() ? null : tarefasOrdenadas;

    }

}
