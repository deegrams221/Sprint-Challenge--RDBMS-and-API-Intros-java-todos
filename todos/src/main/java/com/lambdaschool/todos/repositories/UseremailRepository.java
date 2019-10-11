package com.lambdaschool.todos.repositories;

import com.lambdaschool.todos.models.Useremail;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UseremailRepository extends CrudRepository<Useremail, Long>
{
    List<Useremail> findAllBy_Username(String name);
}
