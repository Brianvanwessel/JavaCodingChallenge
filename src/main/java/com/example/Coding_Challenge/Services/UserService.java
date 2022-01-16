package com.example.Coding_Challenge.Services;
import com.example.Coding_Challenge.Objects.User;
import com.example.Coding_Challenge.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * The UserService class handles all logic for the User table.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    /**
     * Constructor that initiates the userRepository.
     *
     * @param userRepository Instance of the userRepository.
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * The function getUsers collects all Users form the database.
     *
     * @return
     */
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * The function createUser adds a user to the database
     *
     * @param user An User object that has to be added to the database.
     * @return ResponseEntity that indicates if the user was saved correct or not.
     */
    public ResponseEntity createUser(User user) {
        if (userRepository.findUserByUser_name(user.getUser_name()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseEntity.badRequest().body("User already exists!!"));
        } else {
            userRepository.save(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

    }
}
