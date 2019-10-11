package com.lambdaschool.todos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
public class Roles extends Auditable
{
    // ROLES
    //  roleid primary key, not null long
    //  rolename string not null unique

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roleid;

    @Column(nullable = false, unique = true)
    String rolename;

    // map 'role' to 'userRoles'
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("role")
    private List<UserRoles> userRoles = new ArrayList<>();

    // default constructor
    public Roles()
    {

    }

    // constructors
    public Roles(String rolename)
    {
        this.rolename = rolename;
    }

    // getters and setters
    public long getRoleid() {
        return roleid;
    }

    public void setRoleid(long roleid)
    {
        this.roleid = roleid;
    }

    public String getRolename()
    {
        return rolename;
    }

    public void setName(String name)
    {
        this.rolename = name;
    }

    public List<UserRoles> getUserRoles()
    {
        return userRoles;
    }

    public void setUserRoles(List<UserRoles> userRoles)
    {
        this.userRoles = userRoles;
    }
}
