package com.lambdaschool.todos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "userroles")
public class UserRoles extends Auditable implements Serializable
{
    // USERROLES
    //  roleid foreign key to role
    //  userid foreign key to user

    @Id
    @ManyToOne
    @JsonIgnoreProperties({"userRoles", "hibernateLazyInitializer"})
    @JoinColumn(name = "userid")
    private Users users;

    @Id
    @ManyToOne
    @JoinColumn(name = "roleid")
    @JsonIgnoreProperties({"userRoles", "hibernateLazyInitializer"})
    private Roles roles;

    // default constructor
    public UserRoles()
    {

    }

    // constructors
    public UserRoles(Users users, Roles roles)
    {
        this.users = users;
        this.roles = roles;
    }

    // getters and setters
    public Users getUsers()
    {
        return users;
    }

    public void setUsers(Users users)
    {
        this.users = users;
    }

    public Roles getRoles()
    {
        return roles;
    }

    public void setRoles(Roles roles)
    {
        this.roles = roles;
    }

    // override methods - hashcode and boolean
    @Override
    public int hashCode()
    {
        return Objects.hash(getUsers(), getRoles());
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
        {
            return true;
        }
        if(!(obj instanceof UserRoles))
        {
            return false;
        }
        UserRoles userRoles = (UserRoles) obj;
        return getUsers().equals(userRoles.getUsers()) &&
                getRoles().equals(userRoles.getRoles());
    }
}
