package com.example.Coding_Challenge.Objects;

import javax.persistence.*;

/**
 * Class that can be used to create a Document object.
 */
@Entity
@Table
public class Document {

    @Id
    @SequenceGenerator(
            name = "document_id",
            sequenceName = "document_id",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "document_id"
    )
    private int document_id;
    private String document_extension;
    private String document_type;
    private String document_name;
    private int user_id;

    /**
     * Constructor that uses document_id, document_extension, document_name, document_type, and user_id to create a Document Object.
     *
     * @param document_id        Integer containing document_id.
     * @param document_extension String containing document_extension.
     * @param document_name      String containing document_name.
     * @param document_type      String containing document_type.
     * @param user_id            Integer containing user_id.
     */
    public Document(int document_id, String document_extension, String document_name, String document_type, int user_id) {
        this.document_id = document_id;
        this.document_extension = document_extension;
        this.document_name = document_name;
        this.document_type = document_type;
        this.user_id = user_id;
    }

    /**
     * Empty constructor.
     */
    public Document() {
    }

    /**
     * Constructor that uses document_extension, document_name, document_type, and user_id to create a Document Object.
     *
     * @param document_extension String containing document_extension.
     * @param document_name      String containing document_name.
     * @param document_type      String containing document_type.
     * @param user_id            Integer containing user_id.
     */
    public Document(String document_extension, String document_name, String document_type, int user_id) {
        this.document_extension = document_extension;
        this.document_name = document_name;
        this.document_type = document_type;
        this.user_id = user_id;
    }


    public int getDocument_id() {
        return document_id;
    }

    public void setDocument_id(int document_id) {
        this.document_id = document_id;
    }

    public String getDocument_extension() {
        return document_extension;
    }

    public void setDocument_extension(String document_extension) {
        this.document_extension = document_extension;
    }

    public String getDocument_name() {
        return document_name;
    }

    public void setDocument_name(String document_name) {
        this.document_name = document_name;
    }

    public String getDocument_type() {
        return document_type;
    }

    public void setDocument_type(String document_type) {
        this.document_type = document_type;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    /**
     * ToString function that converts Object to String.
     *
     * @return A String containing Object description.
     */
    @Override
    public String toString() {
        return "Document{" +
                "document_id=" + document_id +
                ", document_extension='" + document_extension + '\'' +
                ", document_name='" + document_name + '\'' +
                ", document_type='" + document_type + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}
