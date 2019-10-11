package com.lambdaschool.todos;

import com.lambdaschool.todos.models.*;
import com.lambdaschool.todos.services.RolesService;
import com.lambdaschool.todos.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Date;



@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    @Autowired
    RolesService roleService;

    @Autowired
    UsersService userService;


    @Override
    public void run(String[] args) throws Exception
    {
        Roles r1 = new Roles("admin");
        Roles r2 = new Roles("user");
        Roles r3 = new Roles("data");

        roleService.save(r1);
        roleService.save(r2);
        roleService.save(r3);

        // admin, data, user
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new Users(),
                                 r1));
        admins.add(new UserRoles(new Users(),
                                 r2));
        admins.add(new UserRoles(new Users(),
                                 r3));
        Users u1 = new Users("admin",
                           "password",
                           "admin@lambdaschool.local",
                           admins);
        u1.getUseremails()
          .add(new Useremail("admin@email.local",
                  u1));
        u1.getUseremails()
          .add(new Useremail("admin@mymail.local",
                  u1));
        u1.getTodos().add(new Todo("Finish java-orders-swagger", new Date(), u1));
        u1.getTodos().add(new Todo("Feed the turtles", new Date(), u1));
        u1.getTodos().add(new Todo("Complete the sprint challenge", new Date(), u1));

        userService.save(u1);

        // data, user
        ArrayList<UserRoles> datas = new ArrayList<>();
        datas.add(new UserRoles(new Users(),
                                r3));
        datas.add(new UserRoles(new Users(),
                                r2));
        Users u2 = new Users("cinnamon",
                           "1234567",
                           "cinnamon@lambdaschool.local",
                           datas);
        u2.getUseremails()
          .add(new Useremail("cinnamon@mymail.local",
                  u2));
        u2.getUseremails()
          .add(new Useremail("hops@mymail.local",
                  u2));
        u2.getUseremails()
          .add(new Useremail("bunny@email.local",
                  u2));
        u2.getTodos().add(new Todo("Walk the dogs", new Date(), u2));
        u2.getTodos().add(new Todo("provide feedback to my instructor", new Date(), u2));
        userService.save(u2);

        // user
        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new Users(),
                                r2));
        Users u3 = new Users("barnbarn",
                           "ILuvM4th!",
                           "barnbarn@lambdaschool.local",
                           users);
        u3.getUseremails()
          .add(new Useremail("barnbarn@email.local",
                  u3));
        userService.save(u3);

        users = new ArrayList<>();
        users.add(new UserRoles(new Users(),
                                r2));
        Users u4 = new Users("puttat",
                           "password",
                           "puttat@school.lambda",
                           users);
        userService.save(u4);

        users = new ArrayList<>();
        users.add(new UserRoles(new Users(),
                                r2));
        Users u5 = new Users("misskitty",
                           "password",
                           "misskitty@school.lambda",
                           users);
        userService.save(u5);
    }
}
