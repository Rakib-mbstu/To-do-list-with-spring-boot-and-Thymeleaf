package com.dsi.todolistwithspringboot.controller;

import com.dsi.todolistwithspringboot.model.Todo;
import com.dsi.todolistwithspringboot.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class todoController {
    private final TodoService service;

    public todoController(TodoService service) {
        this.service = service;
    }

    @GetMapping("/todos")
    public String todosList(Model model){
        List<Todo> todoLists = service.findAll();
        model.addAttribute("todoLists",todoLists);
        return "index";
    }
    @GetMapping("/todos/{id}")
    public String todos(@PathVariable("id") int id, Model model){
        Optional<Todo> todo = service.findById(id);
        model.addAttribute("todoLists",todo);
        return "index";
    }
    @PostMapping("/todos")
    public String saveTodo(Todo todo){
        service.save(todo);
        return "redirect:/todos";
    }
    @PutMapping("/todos")
    public String updateTodo(Todo todo){
        service.update(todo);
        return "redirect:/todos";
    }

}
