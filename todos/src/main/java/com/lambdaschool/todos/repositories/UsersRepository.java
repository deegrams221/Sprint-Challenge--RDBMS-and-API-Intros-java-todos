package com.lambdaschool.todos.repositories;

import com.lambdaschool.todos.models.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, Long>
{
    Users findByUsername(String username);
}