package com.example.Coding_Challenge.Repositories;

import com.example.Coding_Challenge.Objects.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * The UserRepository interface handles all database actions for the User table.
 */
@Repository
public interface UserRepository
        extends JpaRepository<User, Integer> {
    /**
     * The function findUserByUser_name collects a User based on its user_name from the User database table.
     *
     * @param user_name A String containing the name of a User.
     * @return A User Object.
     */
    @Query("SELECT u FROM User u WHERE u.user_name = ?1")
    User findUserByUser_name(String user_name);

}
