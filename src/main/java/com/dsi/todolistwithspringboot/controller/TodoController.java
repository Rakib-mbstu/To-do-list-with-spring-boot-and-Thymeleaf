package com.dsi.todolistwithspringboot.controller;

import com.dsi.todolistwithspringboot.model.Todo;
import com.dsi.todolistwithspringboot.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;
import java.util.Optional;


@Controller
@EnableWebMvc
public class TodoController {
    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }


    @RequestMapping(value = "/todos",method = RequestMethod.GET)
    public String todosList(Model model) {
        List<Todo> todoLists = service.findAll();
        model.addAttribute("todoLists", todoLists);
        return "index";
    }

    @GetMapping("/todos/{id}")
    public String todos(@PathVariable("id") int id, Model model) {
        Optional<Todo> todo = service.findById(id);
        model.addAttribute("todoLists", todo);
        return "index";
    }

    @RequestMapping (value = "/todos",method = RequestMethod.POST)
    public String saveTodo(Todo todo, Model model) {
        service.save(todo);
        System.out.println("POST");
       return "redirect:/todos";
    }
    @PutMapping("/todos")
    public String updateTodo(Todo todo) {
        System.out.println("PUT");
        service.update(todo);
        return "redirect:/todos";
    }

    @DeleteMapping("/todos/{id}")
    public String delete(@PathVariable("id") int id) {
        System.out.println("delete");
        service.delete(id);
        return "redirect:/todos";
    }
}
