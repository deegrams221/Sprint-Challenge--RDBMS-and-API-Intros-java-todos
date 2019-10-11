package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todo;
import com.lambdaschool.todos.models.UserRoles;
import com.lambdaschool.todos.models.Useremail;
import com.lambdaschool.todos.models.Users;
import com.lambdaschool.todos.repositories.RolesRepository;
import com.lambdaschool.todos.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "usersService")
public class UsersServiceImpl implements UsersService, UserDetailsService
{
    @Autowired
    private UsersRepository userrepos;

    @Autowired
    private RolesRepository rolerepos;

    @Transactional
    public Users findUserById(long id) throws EntityNotFoundException
    {
        return userrepos.findById(id).orElseThrow(() ->
                new EntityNotFoundException(Long.toString(id)));
    }

    public List<Users> findAll()
    {
        List<Users> list = new ArrayList<>();
        userrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Users findUserByName(String name)
    {
        System.out.println("HERE");
        Users currentUser = userrepos.findByUsername(name);

        if (currentUser != null)
        {
            return currentUser;
        } else
        {
            throw new EntityNotFoundException(name);
        }
    }

    @Transactional
    @Override
    public Users save(Users users)
    {
        Users newUser = new Users();
        newUser.setUsername(users.getUsername());
        newUser.setPasswordNoEncrypt(users.getPassword());
        newUser.setPrimaryemail(users.getPrimaryemail().toLowerCase());

        ArrayList<UserRoles> newRoles = new ArrayList<>();
        for (UserRoles ur : users.getUserRoles())
        {
            System.out.println(ur.getRoles().getRolename());
            newRoles.add(new UserRoles(newUser, ur.getRoles()));
        }
        newUser.setUserRoles(newRoles);

        for (Useremail ue : users.getUseremails())
        {
            newUser.getUseremails()
                    .add(new Useremail(newUser, ue.getUseremail()));
        }

        for (Todo t : users.getTodos())
        {
            newUser.getTodos().add(new Todo(t.getDescription(), t.getDatestrated(), newUser));
        }

        return userrepos.save(newUser);
    }

    @Override
    public Users update(Users user, long id)
    {
        Users currentUser = userrepos.findById(id).orElseThrow(() ->
                new EntityNotFoundException(Long.toString(id)));

        if (user.getUsername() != null)
        {
            currentUser.setUsername(user.getUsername());
        }

        if (user.getPassword() != null)
        {
            currentUser.setPasswordNoEncrypt(user.getPassword());
        }

        if (user.getUserRoles().size() > 0)
        {
            rolerepos.deletUserRolesByUserid(currentUser.getUserid());

            for (UserRoles ur : user.getUserRoles())
            {
                rolerepos.insertUserRoles(id, ur.getRoles().getRoleid());
            }
        }
        return userrepos.save(currentUser);
    }

    @Override
    public void delete(long id)
    {
        if (userrepos.findById(id).isPresent())
        {
            userrepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users user = userrepos.findByUsername(s);
        if (user == null)
        {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), user.getAuthority());
                // getAuthority returns a list of roles that user has access to
    }
}
