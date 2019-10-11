package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todo;
import com.lambdaschool.todos.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service(value = "todoService")
public class TodoServiceImpl implements TodoService
{
    @Autowired
    private TodoRepository todorepos;

    @Override
    public List<Todo> findAll()
    {
        return null;
    }

    @Override
    public Todo findTodoById(long id)
    {
        return null;
    }

    @Override
    public Todo save(Todo todo)
    {
        return null;
    }

    @Override
    public Todo update(Todo todo, long id) {
        Todo currentTodo = todorepos.findById(id)
                .orElseThrow(()->new EntityNotFoundException(Long.toString(id)));
        System.out.println("todo found");
        if(todo.getDescription() != null)
        {
            currentTodo.setDescription(todo.getDescription());
        }
        if(todo.getDatestrated() != null)
        {
            currentTodo.setDatestrated(todo.getDatestrated());
        }
        if(todo.isCompleted() != false)
        {
            currentTodo.setCompleted(true);
        }
        else {
            currentTodo.setCompleted(false);
        }
        if(todo.getUsers() != null)
        {
            currentTodo.setUsers(todo.getUsers());
        }
        return todorepos.save(currentTodo);
    }

    @Override
    public void delete(long id)
    {

    }
}
