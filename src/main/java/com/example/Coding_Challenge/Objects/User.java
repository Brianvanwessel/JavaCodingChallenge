package com.example.Coding_Challenge.Objects;
import javax.persistence.*;


@Entity
@Table

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

    public User(int user_id, String user_name) {
        this.user_id = user_id;
        this.user_name = user_name;
    }

    public User(String user_name) {
        this.user_name = user_name;
    }

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

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                '}';
    }
}
