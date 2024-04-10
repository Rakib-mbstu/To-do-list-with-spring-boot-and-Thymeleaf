package com.dsi.todolistwithspringboot.service;
import com.dsi.todolistwithspringboot.model.Todo;
import com.dsi.todolistwithspringboot.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepo;

    public List<Todo> findAll(){
        return todoRepo.findAll();
    }

    public Optional<Todo> findById(int id) {
        return todoRepo.findById(id);
    }

    public void save(Todo todo) {
        todoRepo.save(todo);
    }

    public void update(Todo todo) {
        Optional<Todo> savedTodo = todoRepo.findById(todo.getId());
        if(savedTodo.isPresent()){
            todoRepo.save(todo);
        }
    }

    public void delete(int id) {
        todoRepo.deleteById(id);
    }
}
