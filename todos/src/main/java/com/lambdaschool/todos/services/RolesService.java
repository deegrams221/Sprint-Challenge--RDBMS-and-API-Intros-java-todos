package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Roles;
import java.util.List;

public interface RolesService
{
    // find all
    List<Roles> findAll();

    // find by id
    Roles findRoleById(long id);

    //save
    Roles save(Roles roles);

    // delete
    void delete(long id);
}
