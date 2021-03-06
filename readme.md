# Java Coding Challenge API Documentation


This is my take on the Java Coding challenge for Dynamic Web Marketing.

This application is a simple microservice running on the JVM that manages the storage and retrieval
of documents for users.

# Design

A basic visual overview of the different components of the API.

[Design](/images/CodeChallenge.drawio.png)

A visual overview of the Database design.

[Database_Design](/images/DatabaseDesign.PNG)

# Dependencies

Dependencies used for this project are:

- Spring Boot 2.6.2
- Java 17.0.1
- Spring initializr was used to generate the project.

# How to Run

If the correct dependencies are installed, use the following steps to run the application:
- Pull the code from this repo.
- Make sure ```C:/uploads/``` exists, this location is currently used as file storage. (If you want to change this edit file.upload-dir in application.properties and rebuild the spring boot app) 
- Navigate to the target folder using your terminal
- Run the following command: ```java -jar .\Coding_Challenge-0.0.1-SNAPSHOT.jar```

# Improvements

Due to time restrictions some improvements are still needed for this project:

- Exception handling. Add custom exceptions.
- Concurrency. At this moment, Documents are not shared which means that the same Documents can not be changed at the same time. However, no security is implemented so multiple people can use the same User and change the same resource which will result in a concurrency problem. This can be fixed by adding security to a User like adding passwords.
- File storage location, I don't think this is the most ideal solution. I did not have time to find a better one.
# API Communication protocols

---
**'GET'** 'api/v1/User'

Gets all existing Users

_parameters_

none
 
_returns_

```json
{
    "user_id" : Integer,
    "user_name": String
}
```
---
**'POST'** 'api/v1/User'

Creates a User

_parameters_

none

_body_

```json
{
    "user_name": String
}
```

_retuns_

none

---

**'GET'** 'api/v1/Document/{user_name}'

Collects all documents for a certain user

_parameters_

user_name

_body_

none

_retuns_

```json
{
    "document_id": Integer,
    "document_extension": String,
    "document_type": String,
    "document_name": String,
    "user_id": Integer
}
```

---

**'GET'** 'api/v1/Document/{user_name}/{document_name}'

Downloads a saved Document

_parameters_

user_name and document_name

_body_

none

_retuns_

Downloaded file

---

**'POST'** 'api/v1/Document/{user_name}'

Uploads a file and creates a Document in the database

_parameters_

user_name

_body_

Form-data: File and String for the Document type

_retuns_

none

---

**'PUT'** 'api/v1/Document/{user_name}/{document_name}'

updates Document info.

_parameters_

user_name and document_name

_body_

Form-data: Document type

_retuns_

none

---






