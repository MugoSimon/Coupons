package com.mugosimon.Coupons.repo;

import com.mugosimon.Coupons.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepo extends
        JpaRepository<Role, Long> {

    /**
     * Fetch a role by its name.
     *
     * @param roleName the name of the role to search for
     * @return an optional containing the role, or empty if not found
     */
    @Query(value = "SELECT * FROM roles WHERE name = ?1", nativeQuery = true)
    Optional<Role> findByName(String roleName);

    /**
     * Fetch a list of roles for a given user.
     *
     * @param userId the ID of the user to search for
     * @return a list of roles associated with the user
     */
    @Query(value = "SELECT r.* " +
            "FROM roles r " +
            "JOIN user_role ur ON r.id = ur.role_id " +
            "WHERE ur.user_id = ?1", nativeQuery = true)
    List<Role> findByUserId(Long userId);

    /**
     * Fetch a list of users who have a specific role.
     *
     * @param roleName the name of the role to search for
     * @return a list of users who have the specified role
     */
    @Query(value = "SELECT u.* " +
            "FROM users u " +
            "JOIN user_role ur ON u.id = ur.user_id " +
            "JOIN roles r ON ur.role_id = r.id " +
            "WHERE r.name = ?1", nativeQuery = true)
    List<Role> findUsersByRole(String roleName);

    /**
     * Fetch a list of roles that have a specific user.
     *
     * @param userId the ID of the user to search for
     * @return a list of roles associated with the user
     */
    @Query(value = "SELECT r.* " +
            "FROM roles r " +
            "JOIN user_role ur ON r.id = ur.role_id " +
            "WHERE ur.user_id = ?1", nativeQuery = true)
    List<Role> findRolesByUserId(Long userId);

}
