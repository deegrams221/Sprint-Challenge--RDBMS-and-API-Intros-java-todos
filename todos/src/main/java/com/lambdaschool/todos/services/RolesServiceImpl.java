package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Roles;
import com.lambdaschool.todos.repositories.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "rolesService")
public class RolesServiceImpl implements RolesService
{
    @Autowired
    RolesRepository rolerepos;

    @Override
    public List<Roles> findAll()
    {
        List<Roles> list = new ArrayList<>();
        rolerepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Roles findRoleById(long id)
    {
        return rolerepos.findById(id).orElseThrow(() ->
                new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public Roles save(Roles roles)
    {
        return rolerepos.save(roles);
    }

    @Override
    public void delete(long id)
    {
        rolerepos.findById(id).orElseThrow(() ->
                new EntityNotFoundException(Long.toString(id)));
        rolerepos.deleteById(id);
    }
}
