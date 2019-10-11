package com.lambdaschool.todos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

// Users in considered the parent entity
@Entity
@Table(name = "users")
public class Users extends Auditable
{
    // USERS
    //  userid primary key, not null long
    //  username string, not null unique
    //  primaryemail string, not null unique
    //  password string, not null

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false) // doesn't need 'unique = true', bc multiple users can have the same password
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(nullable = false, unique = true)
    @Email
    private String primaryemail;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("users")
    private List<UserRoles> userRoles = new ArrayList<>();

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("users")
    private List<Useremail> useremails = new ArrayList<>();

    // default constructor
    public Users()
    {

    }

    // constructors
    public Users(String username,
                String password,
                String primaryemail,
                List<UserRoles> userRoles)
    {
        setUsername(username);
        setPassword(password);
        this.primaryemail = primaryemail;
        for (UserRoles ur : userRoles)
        {
            ur.setUsers(this);
        }
        this.userRoles = userRoles;
    }

    // getters and setters
    public long getUserid()
    {
        return userid;
    }

    public void setUserid(long userid)
    {
        this.userid = userid;
    }

    public String getUsername()
    {
        if (username == null) // this is possible when updating a user
        {
            return null;
        } else
        {
            return username.toLowerCase();
        }
    }

    public void setUsername(String username)
    {
        this.username = username.toLowerCase();
    }

    public String getPrimaryemail()
    {
        return primaryemail;
    }

    public void setPrimaryemail(String primaryemail)
    {
        this.primaryemail = primaryemail;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    public void setPasswordNoEncrypt(String password)
    {
        this.password = password;
    }

    public List<UserRoles> getUserRoles()
    {
        return userRoles;
    }

    public void setUserRoles(List<UserRoles> userRoles)
    {
        this.userRoles = userRoles;
    }

    public List<Useremail> getUseremails()
    {
        return useremails;
    }

    public void setUseremails(List<Useremail> useremails)
    {
        this.useremails = useremails;
    }

    @JsonIgnore
    public List<SimpleGrantedAuthority> getAuthority()
    {
        List<SimpleGrantedAuthority> rtnList = new ArrayList<>();

        for (UserRoles r : this.userRoles)
        {
            String myRole = "ROLE_" + r.getRoles()
                    .getRolename()
                    .toUpperCase();
            rtnList.add(new SimpleGrantedAuthority(myRole));
        }

        return rtnList;
    }

    // toString
    @Override
    public String toString()
    {
        return "User{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", primaryEmail='" + primaryemail +
                '\'' + ", userroles=" + userRoles +
                ", useremails=" + useremails + '}';
    }
}
