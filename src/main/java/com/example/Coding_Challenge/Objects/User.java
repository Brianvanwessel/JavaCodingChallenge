package com.example.Coding_Challenge.Objects;

import javax.persistence.*;


@Entity
@Table

/**
 * Class that can be used to create a User object.
 */
public class User {
    @Id
    @SequenceGenerator(
            name = "user_id",
            sequenceName = "user_id",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "user_id"
    )
    private int user_id;
    private String user_name;

    /**
     * Constructor that uses user_id and user_name to create a User Object.
     *
     * @param user_id   An Integer containing the id of a user.
     * @param user_name A String containing the name of a user.
     */
    public User(int user_id, String user_name) {
        this.user_id = user_id;
        this.user_name = user_name;
    }

    /**
     * Constructor that uses user_name to create a User Object.
     *
     * @param user_name A string containing the name of a user.
     */
    public User(String user_name) {
        this.user_name = user_name;
    }

    /**
     * Empty constructor.
     */
    public User() {

    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    /**
     * ToString function that converts Object to String.
     *
     * @return A String containing Object description.
     */
    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                '}';
    }
}
