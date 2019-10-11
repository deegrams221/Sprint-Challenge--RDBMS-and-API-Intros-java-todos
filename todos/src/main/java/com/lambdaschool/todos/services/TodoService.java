package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todo;
import java.util.List;

public interface TodoService
{
    // find all
    List<Todo> findAll();

    // find by id
    Todo findTodoById(long id);

    //save
    Todo save(Todo todo);

    // delete
    void delete(long id);
}
