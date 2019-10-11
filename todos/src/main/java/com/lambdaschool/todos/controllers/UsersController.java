package com.lambdaschool.todos.controllers;

import com.lambdaschool.todos.models.Users;
import com.lambdaschool.todos.services.UsersService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;

@RestController
public class UsersController
{
    @Autowired
    private UsersService userService;

    // GET /users/mine - return the user and todo based off of the authenticated user.
    // You can only look up your own. It is okay if this also lists the users roles and
    // authorities.
    @GetMapping(value = "/users/mine", produces = {"application/json"})
    public ResponseEntity<?> getUsername(Authentication authentication)
    {
        return new ResponseEntity<>(userService.findUserByName(authentication.name()), HttpStatus.OK);
    }

    // POST /users/user - adds a user. Can only be done by an admin.
    // {
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
    // }
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping(value = "/users/user", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewUser(@Valid @RequestBody Users newuser) throws URISyntaxException {
        newuser = userService.save(newuser);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // PUT /users/todo/{userid} - adds a todo to the assigned user.
    // Can be done by any user. You can add this todo
    // {
    //    "description": "Have Fun",
    //    "datestarted": "2019-01-01T01:00"
    // }
    @PutMapping(value = "/users/todo/{userid}",
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<?> addTodoToUser(@RequestBody Users newUserData,
                                           @PathVariable long userid) throws URISyntaxException
    {
        userService.update(newUserData, userid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // DELETE /users/userid/{userid} - Deletes a user based off of their userid and
    // deletes all their associated todos. Can only be done by an admin.
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping(value = "/users/userid/{userid}",
            produces = {"application/json"})
    public ResponseEntity<?> deleteUser(@PathVariable long userid)
    {
        userService.delete(userid);
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }

}
