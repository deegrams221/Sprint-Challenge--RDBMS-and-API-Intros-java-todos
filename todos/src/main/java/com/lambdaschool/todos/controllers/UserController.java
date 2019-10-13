package com.lambdaschool.todos.controllers;

import com.lambdaschool.todos.models.User;
import com.lambdaschool.todos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    // localhost:2019/
    // GET /users/mine - return the user and todo based off of the authenticated user.
    //  You can only look up your own. It is okay if this also lists the users roles and authorities.
    @GetMapping(value = "/mine", produces = {"application/json"})
    public ResponseEntity<?> getUsername(Authentication authentication)
    {
        return new ResponseEntity<>(userService.findUserByName(authentication.getName()), HttpStatus.OK);
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
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping(value = "/user", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewUser(@Valid @RequestBody User newuser) throws URISyntaxException
    {
        newuser =  userService.save(newuser);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    POST /users/todo/{userid} - adds a todo to the assigned user. Can be done by any user.
//     You can add this todo:
//    {
//        "description": "Have Fun",
//            "datestarted": "2019-01-01T01:00"
//    }
    @PutMapping(value = "/todo/{userid}",
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<?> addTodoToUser(HttpServletRequest request,
                                           @RequestBody User newUserData,
                                           @PathVariable long userid) throws URISyntaxException
    {
        userService.update(newUserData, userid, request.isUserInRole("ADMIN"));
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    DELETE /users/userid/{userid} - Deletes a user based off of their userid and deletes
//    all their associated todos. Can only be done by an admin.
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping(value = "/userid/{userid}",
            produces = {"application/json"})
    public ResponseEntity<?> deleteUser(@PathVariable long userid)
    {
        userService.delete(userid);
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }

}