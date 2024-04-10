package com.dsi.todolistwithspringboot.controller;

import com.dsi.todolistwithspringboot.model.Todo;
import com.dsi.todolistwithspringboot.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;
import java.util.Optional;


@Controller
@EnableWebMvc
@RequiredArgsConstructor
public class TodoController {
    private final TodoService service;


    @RequestMapping(value = {"/","/todos"},method = RequestMethod.GET)
    public String todosList(Model model) {
        List<Todo> todoLists = service.findAll();
        model.addAttribute("todoLists", todoLists);
        return "index";
    }

    @GetMapping("/todos/{id}")
    public String todos(@PathVariable("id") int id, Model model) {
        Optional<Todo> todo = service.findById(id);
        Todo todo1 = todo.orElseThrow();
        model.addAttribute("todo", todo1);
        return "edit";
    }

    @RequestMapping (value = "/save",method = RequestMethod.POST)
    public String saveTodo(@Valid  Todo todo, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "error";
        }
        service.save(todo);
       return "redirect:/todos";
    }
   @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String updateTodo(Todo todo) {
        System.out.println("ok");
        service.update(todo);
        return "redirect:/todos";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        service.delete(id);
        return "redirect:/todos";
    }
}
