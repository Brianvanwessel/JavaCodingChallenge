-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2022-01-08 13:00:18.197

-- tables
-- Table: document

CREATE TABLE IF NOT EXISTS document (
                          document_id int  NOT NULL AUTO_INCREMENT,
                          document_type varchar(100),
                          document_extension varchar(100)  NOT NULL,
                          user_id int  NOT NULL,
                          document_name varchar(100)  NOT NULL,
                          CONSTRAINT document_pk PRIMARY KEY (document_id)
);

-- Table: user
CREATE TABLE IF NOT EXISTS user (
                        user_id int  NOT NULL AUTO_INCREMENT,
                        user_name varchar(100)  NOT NULL,
                        CONSTRAINT user_pk PRIMARY KEY (user_id)
);



-- foreign keys
-- Reference: Documents_User (table: document)

ALTER TABLE document ADD CONSTRAINT IF NOT Exists Documents_User
    FOREIGN KEY (user_id)
        REFERENCES user (user_id);

-- End of file.

