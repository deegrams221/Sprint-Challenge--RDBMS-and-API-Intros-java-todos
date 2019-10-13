package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.User;

public interface UserService {

    User findUserByName(String name);

    User findUserById(long id);

    void delete(long id);

    User save(User user);

    User update(User user, long id, boolean isAdmin);
}