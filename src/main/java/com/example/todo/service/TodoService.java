package com.example.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.todo.domain.Todo;
import com.example.todo.repository.TodoRepository;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public Todo get(Long id) {
        return todoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Todo not found: " + id));
    }

    public Todo create(Todo todo) {
        todo.setId(null);
        return todoRepository.save(todo);
    }

    public Todo update(Long id, Todo updated) {
        Todo existing = get(id);
        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        existing.setDueDate(updated.getDueDate());
        existing.setCompleted(Boolean.TRUE.equals(updated.getCompleted()));
        return todoRepository.save(existing);
    }

    public void delete(Long id) {
        todoRepository.deleteById(id);
    }
}


