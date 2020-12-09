package com.essence.api.repositories;

import com.essence.api.entities.security.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionsRepository extends JpaRepository<Permission, Integer> {

    @Query(value = "SELECT p.* FROM roles_permissions rp " +
            "INNER JOIN permissions p ON p.id = rp.permission_id " +
            "WHERE rp.role_id = :roleId",
            nativeQuery = true)
    List<Permission> findPermissionsByRoleId(@Param("roleId") Integer roleId);
}
