package com.lambdaschool.todos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "todo")
public class Todo extends Auditable
{
    // TODOs
    //  todoid primary key, not null long
    //  description string, not null
    //  datestarted datetime
    //  completed boolean
    //  userid foreign key (one user to many todos) not null

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long todoid;

    @Column(nullable = false)
    private String description;

    private Date datestrated;
    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    @JsonIgnoreProperties("todos")
    private Users users;

    // default constructor
    public Todo()
    {

    }

    // constructors
    public Todo(String description,
                Date datestrated,
                Users users)
    {
        this.description = description;
        this.datestrated = datestrated;
        this.users = users;
    }

    // getters and setters
    public long getTodoid()
    {
        return todoid;
    }

    public void setTodoid(long todoid)
    {
        this.todoid = todoid;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Date getDatestrated()
    {
        return datestrated;
    }

    public void setDatestrated(Date datestrated)
    {
        this.datestrated = datestrated;
    }

    public boolean isCompleted()
    {
        return completed;
    }

    public void setCompleted(boolean completed)
    {
        this.completed = completed;
    }

    public Users getUsers()
    {
        return users;
    }

    public void setUsers(Users users)
    {
        this.users = users;
    }
}
