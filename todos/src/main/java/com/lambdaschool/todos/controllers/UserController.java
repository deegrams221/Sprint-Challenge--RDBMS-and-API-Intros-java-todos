package com.lambdaschool.todos.controllers;


import com.lambdaschool.todos.models.Todo;
import com.lambdaschool.todos.models.User;
import com.lambdaschool.todos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    // localhost:2019/
    // GET /users/mine - return the user and todo based off of the authenticated user.
    //  You can only look up your own. It is okay if this also lists the users roles and authorities.
    @GetMapping(value = "/mine", produces = {"application/json"})
    public ResponseEntity<?> getCurrentUserInfo(Authentication authentication) {
        User u = userService.findByName(authentication.getName());
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    // GET /users/users/
    // lists all users
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/users", produces = {"application/json"})
    public ResponseEntity<?> listAllUsers() {
        List<User> myUsers = userService.findAll();
        return new ResponseEntity<>(myUsers, HttpStatus.OK);
    }

    // POST /users/user - adds a user. Can only be done by an admin.
    //{
    //    "username": "hops",
    //    "password": "password",
    //    "primaryemail" : "hops@bunny.hop",
    //    "userroles": [
    //        {
    //            "role": {
    //                "roleid": 2
    //            }
    //        }
    //    ],
    //    "todos": [
    //        {
    //            "description": "Eat Carrots",
    //            "datestarted": "2019-08-16T01:44:18.089+0000"
    //        },
    //        {
    //            "description": "Bang on cage until everyone is awake",
    //            "datestarted": "2019-08-16T01:44:18.089+0000"
    //        }
    //    ]
    //}
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = "/user", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewUser(@Valid @RequestBody User newuser) throws URISyntaxException {
        newuser = userService.save(newuser);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userid}").buildAndExpand(newuser.getUserid()).toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    //    POST /users/todo/{userid} - adds a todo to the assigned user. Can be done by any user.
//     You can add this todo:
//    {
//        "description": "Have Fun",
//            "datestarted": "2019-01-01T01:00"
//    }
    @PostMapping(value = "/todo/{userid}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addTodo(@Valid @RequestBody Todo todo, @PathVariable long userid) {
        userService.addTodo(todo, userid);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //    DELETE /users/userid/{userid} - Deletes a user based off of their userid and deletes
//    all their associated todos. Can only be done by an admin.
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/userid/{userid}")
    public ResponseEntity<?> deleteUserById(@PathVariable long userid) {
        userService.delete(userid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}