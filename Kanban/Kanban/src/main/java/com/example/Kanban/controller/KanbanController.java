package com.example.Kanban.controller;
import com.example.Kanban.model.Kanban;
import com.example.Kanban.service.KanbanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/tasks")
public class KanbanController {
    @Autowired
    private KanbanService kanbanService;


    @GetMapping
    public List<Kanban> listAll() {
        return kanbanService.listAll();
    }

    @GetMapping("/status/{status}")
    public List<Kanban> listarPorStatus(@PathVariable int status){
        return kanbanService.listarPorStatus(status);
    }


    @GetMapping("/prioridade/{prioridade}")
    public List<Kanban> listarPrioridade(@PathVariable int prioridade){
        return kanbanService.listarPorPrioridade(prioridade);
    }


    @PostMapping
    public Kanban add(@RequestBody Kanban kanban) {
        return kanbanService.salvar(kanban);
    }


    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Long id){
        Kanban tarefa = kanbanService.findById(id);
        kanbanService.delete(id);
        return "Tarefa deletada com sucesso: \n\n" + tarefa.toString();
    }


    @PutMapping("/{id}")
    public Kanban edit(@PathVariable Long id, @RequestBody Kanban tarefa) {
        return kanbanService.editar(id, tarefa);
    }


    @PutMapping("/{id}/move")
    public Kanban mover(@PathVariable Long id) {
        kanbanService.mover(id);
        return kanbanService.findById(id);
    }


}
