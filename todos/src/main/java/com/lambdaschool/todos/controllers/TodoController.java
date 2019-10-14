package com.lambdaschool.todos.controller;

import com.lambdaschool.todos.models.Todo;
import com.lambdaschool.todos.models.User;
import com.lambdaschool.todos.services.TodoService;
import com.lambdaschool.todos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodoController
{

    @Autowired
    private TodoService todoService;

//    PUT /todos/todoid/{todoid} - updates a todo based on todoid. Can be done by any user.
//    Note: null boolean is not a thing - it is false, so just set compeleted to whatever comes across in the RequestBody.
//    {
//        "completed": true
//    }
    @PutMapping(value = "/todoid/{todoid}",
        consumes = {"application/json"})
    public ResponseEntity<?> updateTodo(Authentication authentication,
                                    @RequestBody Todo todo,
                                    @PathVariable long todoid) {
        todoService.updateTodo(todo, todoid);
        return new ResponseEntity<>("UPDATE SUCCESS", HttpStatus.OK);
    }

    // GET /todos/todos
    // get all todos
    @GetMapping(value = "/todos",
            produces = {"application/json"})
    public ResponseEntity<?> getAllTodos()
    {
        return new ResponseEntity<>(todoService.findAllTodos(), HttpStatus.OK);
    }
}