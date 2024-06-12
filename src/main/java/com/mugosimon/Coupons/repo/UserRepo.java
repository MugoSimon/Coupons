package com.mugosimon.Coupons.repo;

import com.mugosimon.Coupons.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    /**
     * Fetch a user by their email address.
     *
     * @param email the email address to search for
     * @return an optional containing the user, or empty if not found
     */
    @Query(value = "SELECT * FROM user WHERE email = ?1", nativeQuery = true)
    Optional<User> findByEmail(String email);

    /**
     * Fetch a list of users by their first and last name.
     *
     * @param firstName the first name to search for
     * @param lastName  the last name to search for
     * @return a list of users matching the first and last name
     */
    @Query(value = "SELECT * FROM users WHERE first_name = ?1 AND last_name = ?2", nativeQuery = true)
    List<User> findByFirstNameAndLastName(String firstName, String lastName);

    /**
     * Fetch a list of users by their first name.
     *
     * @param firstName the first name to search for
     * @return a list of users matching the first name
     */
    @Query(value = "SELECT * FROM users WHERE first_name = ?1", nativeQuery = true)
    List<User> findByFirstName(String firstName);

    /**
     * Fetch a list of users by their first name or last name.
     *
     * @param firstName the first name to search for
     * @param lastName  the last name to search for
     * @return a list of users matching the first or last name
     */
    @Query(value = "SELECT * FROM users WHERE first_name = ?1 OR last_name = ?2", nativeQuery = true)
    List<User> findByFirstNameOrLastName(String firstName, String lastName);

    /**
     * Fetch a list of users by their last name.
     *
     * @param lastName the last name to search for
     * @return a list of users matching the last name
     */
    @Query(value = "SELECT * FROM users WHERE last_name = ?1", nativeQuery = true)
    List<User> findByLastName(String lastName);

    /**
     * Fetch a list of users who have a specific role.
     *
     * @param roleName the name of the role to search for
     * @return a list of users who have the specified role
     */
    @Query(value = "SELECT u.* FROM users u " +
            "JOIN user_role ur ON u.id = ur.user_id " +
            "JOIN roles r ON ur.role_id = r.id " +
            "WHERE r.name = ?1", nativeQuery = true)
    List<User> findByRole(String roleName);

    /**
     * Fetch a list of users who have all the specified roles.
     *
     * @param roleNames the names of the roles to search for
     * @param numRoles  the number of roles the users must have
     * @return a list of users who have all the specified roles
     */
    @Query(value = "SELECT u.* FROM users u " +
            "JOIN user_role ur ON u.id = ur.user_id " +
            "JOIN roles r ON ur.role_id = r.id " +
            "WHERE r.name IN (?1) " +
            "GROUP BY u.id " +
            "HAVING COUNT(DISTINCT r.id) = ?2", nativeQuery = true)
    List<User> findByRoles(List<String> roleNames, int numRoles);
}
