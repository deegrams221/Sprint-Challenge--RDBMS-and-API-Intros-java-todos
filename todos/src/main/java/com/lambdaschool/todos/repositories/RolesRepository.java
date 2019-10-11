package com.lambdaschool.todos.repositories;

import com.lambdaschool.todos.models.Roles;
import com.lambdaschool.todos.views.JustTheCount;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RolesRepository extends CrudRepository<Roles, Long>
{
    @Query(value = "SELECT COUNT(*) as count FROM userroles WHERE userid = :userid AND roleid = :roleid",
            nativeQuery = true)
    JustTheCount checkUserRolesCombo(long userid, long roleid);

    @Transactional
    @Modifying
    @Query(value = "DELETE from UserRoles where userid = :userid")
    void deletUserRolesByUserid(long userid);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO UserRoles(userid, roleid) values (:userid, :roleid)", nativeQuery = true)
    void insertUserRoles(long userid, long roleid);

    @Transactional
    @Modifying
    // user Role instead of roles in order to use Hibernate SQL
    @Query(value = "UPDATE Role SET NAME = :name WHERE roleid = :roleid")
    void updateRoleName(long roleid, String name);
}
