package com.example.t4td0.controller;

// Importa as classes necessárias
import com.example.t4td0.dtos.RecordDto;
import com.example.t4td0.model.Tarefa;
import com.example.t4td0.model.TarefaExcluida;
import com.example.t4td0.repository.TarefaRepository;
import com.example.t4td0.repository.TarefaExcluidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

// Declara a classe como um controlador Spring MVC
@Controller
@RequestMapping("/tarefas")
public class HomeController {

    // Injeta automaticamente as dependências dos repositórios
    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private TarefaExcluidaRepository tarefaExcluidaRepository;

    // Mapeia a rota GET "/tarefas" para listar todas as tarefas
    @GetMapping
    public String listarTarefas(Model model) {
        List<Tarefa> tarefas = tarefaRepository.findAll();
        model.addAttribute("tarefas", tarefas);
        return "home/projeto-tarefas/index";
    }

    // Mapeia a rota GET "/tarefas/new" para exibir a página de cadastro de uma nova tarefa
    @GetMapping("/new")
    public String cadastrarTarefaPage(Model model) {
        model.addAttribute("tarefa", new RecordDto(""));
        return "home/projeto-tarefas/cadastrar-tarefa";
    }

    // Mapeia a rota POST "/tarefas/new" para cadastrar uma nova tarefa
    @PostMapping("/new")
    public String cadastrarTarefa(@Validated @ModelAttribute("tarefa") RecordDto tarefaDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("tituloVazio", true);
            return "redirect:/tarefas/new";
        }
        Tarefa tarefa = new Tarefa(tarefaDto.titulo());
        tarefaRepository.save(tarefa);
        redirectAttributes.addFlashAttribute("cadastroSucesso", true);
        return "redirect:/tarefas/new";
    }

    // Mapeia a rota GET "/tarefas/{id}/edit" para exibir a página de edição de uma tarefa
    @GetMapping("/{id}/edit")
    public String preencherFormularioEdicao(@PathVariable("id") Long id, Model model) {
        Optional<Tarefa> optionalTarefa = tarefaRepository.findById(id);
        if (optionalTarefa.isPresent()) {
            Tarefa tarefa = optionalTarefa.get();
            model.addAttribute("tarefa", new RecordDto(tarefa.getTitulo()));
            model.addAttribute("id", tarefa.getId());
            return "home/projeto-tarefas/editar-tarefa";
        } else {
            return "redirect:/tarefas";
        }
    }

    // Mapeia a rota PUT "/tarefas/{id}/edit" para editar uma tarefa existente
    @PutMapping("/{id}/edit")
    public String editarTarefa(@PathVariable("id") Long id, @Validated @ModelAttribute("tarefa") RecordDto tarefaDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("tituloVazio", true);
            return "redirect:/tarefas/" + id + "/edit";
        }
        Optional<Tarefa> optionalTarefa = tarefaRepository.findById(id);
        if (optionalTarefa.isPresent()) {
            Tarefa tarefa = optionalTarefa.get();
            tarefa.setTitulo(tarefaDto.titulo());
            tarefaRepository.save(tarefa);
            redirectAttributes.addFlashAttribute("edicaoSucesso", true);
        }
        return "redirect:/tarefas/" + id + "/edit";
    }

    // Mapeia a rota GET "/tarefas/{id}/destroy" para exibir a página de confirmação de exclusão de uma tarefa
    @GetMapping("/{id}/destroy")
    public String preencherFormularioExclusao(@PathVariable("id") Long id, Model model) {
        Optional<Tarefa> optionalTarefa = tarefaRepository.findById(id);
        if (optionalTarefa.isPresent()) {
            model.addAttribute("tarefa", optionalTarefa.get());
            return "home/projeto-tarefas/excluir-tarefa";
        } else {
            return "redirect:/tarefas";
        }
    }

    // Mapeia a rota POST "/tarefas/{id}/destroy" para excluir uma tarefa
    @DeleteMapping("/{id}/destroy")
    public String excluirTarefa(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Optional<Tarefa> optionalTarefa = tarefaRepository.findById(id);
        if (optionalTarefa.isPresent()) {
            Tarefa tarefa = optionalTarefa.get();
            TarefaExcluida tarefaExcluida = new TarefaExcluida(tarefa.getTitulo());
            tarefaExcluidaRepository.save(tarefaExcluida);
            tarefaRepository.delete(tarefa);
            redirectAttributes.addFlashAttribute("exclusaoSucesso", true);
        } else {
            redirectAttributes.addFlashAttribute("exclusaoFalha", true);
        }
        return "redirect:/tarefas";
    }

    // Mapeia a rota GET "/tarefas/{id}" para exibir a página de visualização de uma tarefa
    @GetMapping("/{id}")
    public String preencherFormularioVisualizacao(@PathVariable("id") Long id, Model model) {
        Optional<Tarefa> optionalTarefa = tarefaRepository.findById(id);
        if (optionalTarefa.isPresent()) {
            model.addAttribute("tarefa", optionalTarefa.get());
            return "home/projeto-tarefas/visualizar-tarefa";
        } else {
            return "redirect:/tarefas";
        }
    }
}