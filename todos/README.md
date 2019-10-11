# java-todos

A student that completes this project shows that they can
* perform CRUD operations on an RDBMS using JPA and Hibernate.
* implement a data seeding class using JPA and Hibernate
* use JPA and Hibernate to perform advanced query operations on a RDBMS.
* add relationships between tables.
* implement Spring Security and OAuth2. to provide authentication for a project.

Specifically
* Seed Data
* CRUD Operations
* H2 Connections
* Authentication


## Introduction

This is a basic todo database scheme with users and a todo list.

## Instructions

Create a REST api service to store and read data from H2 database. 
* seeddata.java is a sample script that can be modified to populate the database 
* note that all new todos default to completed = false;

The table layouts are as follows:

* All tables should have audit fields / columns - createby createddate modifiedby modifieddate
* Note these are the minimum fields required. More is okay.

* TODO
  * todoid primary key, not null long
  * description string, not null
  * datestarted datetime
  * completed boolean
  * userid foreign key (one user to many todos) not null 

* USERS
  * userid primary key, not null long
  * username string, not null unique
  * primaryemail string, not null unique
  * password string, not null

* ROLES
  * roleid primary key, not null long
  * rolename string not null unique

* USERROLES
  * roleid foreign key to role
  * userid foreign key to user
  

Expose the following end points

* GET /users/mine - return the user and todo based off of the authenticated user. You can only look up your own. It is okay if this also lists the users roles and authorities.
* POST /users/user - adds a user. Can only be done by an admin.
```
{
    "username": "hops",
    "password": "password",
    "primaryemail" : "hops@bunny.hop",
    "userroles": [
        {
            "role": {
                "roleid": 2
            }
        }
    ],
    "todos": [
        {
            "description": "Eat Carrots",
            "datestarted": "2019-08-16T01:44:18.089+0000"
        },
        {
            "description": "Bang on cage until everyone is awake",
            "datestarted": "2019-08-16T01:44:18.089+0000"
        }
    ]
}
```

* POST /users/todo/{userid} - adds a todo to the assigned user. Can be done by any user. You can add this todo
```
{
    "description": "Have Fun",
    "datestarted": "2019-01-01T01:00"
}
```

* PUT /todos/todoid/{todoid} - updates a todo based on todoid. Can be done by any user. Note: null boolean is not a thing - it is false, so just set compeleted to whatever comes across in the RequestBody.
```
{
    "completed": true
}
```

* DELETE /users/userid/{userid} - Deletes a user based off of their userid and deletes all their associated todos. Can only be done by an admin.


* hint - think about taking the project https://github.com/LambdaSchool/java-oauth2.git and modifying it to fit this application


## Stretch goals

* Update the end points below:

  * POST /users/todo/{userid} - adds a todo to the assigned user. Can only be done by the authenticated user. A user can only modify their own data.
  
  * PUT /todos/todoid/{todoid} - updates a todo based on todoid. Can only be done by the authenticated user. A user can only modify their own data.
