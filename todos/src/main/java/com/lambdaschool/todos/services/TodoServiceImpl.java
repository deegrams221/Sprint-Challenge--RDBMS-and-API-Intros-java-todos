package com.lambdaschool.todos.services;


import com.lambdaschool.todos.models.Todo;
import com.lambdaschool.todos.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "todoService")
public class TodoServiceImpl implements TodoService
{
    @Autowired
    private TodoRepository todorepos;

    @Override
    public Todo updateTodo(Todo todo, long todoid)
    {
        Todo currentTodo = todorepos.findById(todoid).orElseThrow(() -> new EntityNotFoundException(Long.toString(todoid)));

        if (todo.getDatestarted() != null)
        {
            currentTodo.setDatestarted(todo.getDatestarted());
        }

        if (todo.getDescription() != null)
        {
            currentTodo.setDescription(todo.getDescription());
        }

        if (todo.getUser() != null)
        {
            currentTodo.setUser(todo.getUser());
        }

        currentTodo.setCompleted(todo.isCompleted());

        return todorepos.save(todo);
    }

    @Override
    public List<Todo> findAllTodos()
    {
        List<Todo> rtnList = new ArrayList<>();

        todorepos.findAll().iterator().forEachRemaining(rtnList::add);

        return rtnList;
    }

    @Override
    public Todo findtodoById(long id) {
        return null;
    }

    @Override
    public Todo findtodoByName(String name) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Todo save(Todo todo) {
        return null;
    }

    @Override
    public Todo update(Todo todo, long id) {
        return null;
    }
}