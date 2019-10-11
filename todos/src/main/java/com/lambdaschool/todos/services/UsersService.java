package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Users;
import java.util.List;

public interface UsersService
{
    // find all
    List<Users> findAll();

    // find by name
    Users findUserByName(String name);

    // find by id
    Users findUserById(long id);

    //save
    Users save(Users users);

    // update by id
    Users update(Users user, long id);

    // delete
    void delete(long id);
}
