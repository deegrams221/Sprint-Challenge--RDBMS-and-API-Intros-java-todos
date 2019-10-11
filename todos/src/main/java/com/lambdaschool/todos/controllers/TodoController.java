package com.lambdaschool.todos.controllers;

import com.lambdaschool.todos.models.Todo;
import com.lambdaschool.todos.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

    @Autowired
    private TodoService todoService;

//    PUT /todos/todoid/{todoid} - updates a todo based on todoid. Can be done by any user.
//    Note: null boolean is not a thing - it is false, so just set compeleted to whatever comes across in the RequestBody.
//    {
//        "completed": true
//    }
    @PutMapping(value = "/todos/todoid/{todoid}",
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<?> updateTodo(@RequestBody Todo updatedTodo, @PathVariable long todoid)
    {
        todoService.update(updatedTodo, todoid);
        return new ResponseEntity<>("UPDATE SUCCESS", HttpStatus.OK);
    }

}