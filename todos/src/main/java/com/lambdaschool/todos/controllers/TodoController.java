package com.lambdaschool.todos.controllers;

public class TodoController
{
    // Expose the following end points

    // GET /users/mine - return the user and todo based off of the authenticated user.
    // You can only look up your own. It is okay if this also lists the users roles and
    // authorities.

    // POST /users/todo/{userid} - adds a todo to the assigned user.
    // Can be done by any user. You can add this todo
    // {
    //    "description": "Have Fun",
    //    "datestarted": "2019-01-01T01:00"
    // }

    // PUT /todos/todoid/{todoid} - updates a todo based on todoid.
    //  Can be done by any user. Note: null boolean is not a thing - it is false,
    //  so just set compeleted to whatever comes across in the RequestBody.
    // {
    //    "completed": true
    // }

    // DELETE /users/userid/{userid} - Deletes a user based off of their userid and
    // deletes all their associated todos. Can only be done by an admin.
}
