package com.lambdaschool.todos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "useremails", uniqueConstraints =
        {@UniqueConstraint(columnNames = {"userid", "useremail"})})
public class Useremail extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long useremailid;

    @Column(nullable = false)
    @Email
    private String useremail;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    @JsonIgnoreProperties("useremails")
    private Users users;

    // default constructor
    public Useremail()
    {

    }

    // constructors
    public Useremail(@Email String useremail, Users users)
    {
        this.useremail = useremail;
        this.users = users;
    }

    public Useremail(Users newUser, String useremail) {
        super();
    }

    // getters and setters
    public long getUseremailid()
    {
        return useremailid;
    }

    public void setUseremailid(long useremailid)
    {
        this.useremailid = useremailid;
    }

    public String getUseremail()
    {
        return useremail.toLowerCase();
    }

    public void setUseremail(String useremail)
    {
        this.useremail = useremail.toLowerCase();
    }

    public Users getUsers()
    {
        return users;
    }

    public void setUsers(Users users)
    {
        this.users = users;
    }

    // toString
    @Override
    public String toString()
    {
        return "Useremail{" +
                "useremailid=" + useremailid +
                ", useremail='" + useremail + '\'' +
                ", users=" + users +
                '}';
    }
}
