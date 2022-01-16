package com.example.Coding_Challenge.Services;

import com.example.Coding_Challenge.Objects.Document;
import com.example.Coding_Challenge.Objects.User;
import com.example.Coding_Challenge.Repositories.DocumentRepository;
import com.example.Coding_Challenge.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

/**
 * The UserService class handles all logic for the User table.
 */
@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final UserRepository userRepository;

    /**
     * Constructor that initiates the DocumentService and UserService.
     *
     * @param documentRepository Instance of the DocumentService
     * @param userRepository     Instance of the UserService
     */
    @Autowired
    public DocumentService(DocumentRepository documentRepository, UserRepository userRepository) {
        this.documentRepository = documentRepository;
        this.userRepository = userRepository;
    }

    /**
     * The function createDocument uploads a file and creates a Document in the database.
     *
     * @param user_name A String that contains a user_name.
     * @param file      A MultipartFile that contains the file that will be saved.
     * @param type      A string that contains the Document type.
     * @return A ResponseEntity that indicates if the Document was uploaded and saved correctly.
     */
    public ResponseEntity createDocument(String user_name, MultipartFile file, String type) {
        String[] parts = file.getOriginalFilename().split("\\.");
        try {
            fileUpload(file);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseEntity.badRequest().body("Can not read file!!"));
        }

        User selectedUser = userRepository.findUserByUser_name(user_name);

        if (documentRepository.findDocumentByUser_idAndName(selectedUser.getUser_id(), parts[0]).size() > 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseEntity.badRequest().body("Document already exists for this user!!"));
        } else {
            Document document = new Document(parts[1], parts[0], type, selectedUser.getUser_id());
            documentRepository.save(document);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

    }

    /**
     * The function getDocumentsForUser collects all documents for a certain user.
     *
     * @param user_name A string that contains a username.
     * @returnA ResponseEntity that either serves the documents for a certain user or a response that indicates the request failed.
     */
    public ResponseEntity getDocumentsForUser(String user_name) {
        User queryUser;
        List<Document> documentByUser;
        try {
            queryUser = userRepository.findUserByUser_name(user_name);
            documentByUser = documentRepository.findDocumentByUser_id(queryUser.getUser_id());
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseEntity.badRequest().body("User does not exist!"));
        }
        return new ResponseEntity<>(documentByUser, HttpStatus.OK);

    }

    @Value("${file.upload-dir}")
    private String uploadDir;

    /**
     * The function fileUpload uploads a file to the server.
     *
     * @param file A MultipartFile that contains the file that will be saved.
     * @return A ResponseEntity that indicates if the file was saved correctly.
     * @throws IOException
     */
    public ResponseEntity<Object> fileUpload(MultipartFile file) throws IOException {
        String destinationFilename = "./" + uploadDir + file.getOriginalFilename();
        try {
            Files.copy(file.getInputStream(), Path.of(destinationFilename), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseEntity.badRequest().body("File was not uploaded correctly!"));
        }
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

    /**
     * The function downloadDocument Downloads a saved Document.
     *
     * @param user_name     A string that contains a users name.
     * @param document_name A string that contains a Document name.
     * @return A ResponseEntity that indicates if the Document was downloaded correctly.
     */
    public ResponseEntity<Resource> downloadDocument(String user_name, String document_name) {
        User currentUser = userRepository.findUserByUser_name(user_name);
        List<Document> selectedDocument = documentRepository.findDocumentByUser_idAndName(currentUser.getUser_id(), document_name);
        ResponseEntity<Resource> resource;
        try {
            resource = fileDownload(selectedDocument.get(0).getDocument_name() + "." + selectedDocument.get(0).getDocument_extension());
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
        return resource;
    }

    /**
     * The function fileDownload downloads a file from the server.
     *
     * @param filename A String containing a file name.
     * @return A ResponseEntity that downloads a file.
     */
    public ResponseEntity<Resource> fileDownload(String filename) {

        File file = new File(uploadDir + filename);

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
        Path path = Paths.get(file.getAbsolutePath());

        ByteArrayResource resource = null;
        try {
            resource = new ByteArrayResource(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

    /**
     * The function updateDocument updates Document info.
     *
     * @param user_name     A String that contains a users name.
     * @param document_name A String that contains a Document name.
     * @param type          A String that contains the Document type.
     * @return A ResponseEntity that describes if the Document was updated correctly.
     */
    public ResponseEntity updateDocument(String user_name, String document_name, String type) {
        User currentUser = userRepository.findUserByUser_name(user_name);
        List<Document> selectedDocument = documentRepository.findDocumentByUser_idAndName(currentUser.getUser_id(), document_name);
        try {
            if (type != null) {
                selectedDocument.get(0).setDocument_type(type);
                documentRepository.save(selectedDocument.get(0));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseEntity.badRequest().body("You can not use null as an property value"));
            }


        } catch (IndexOutOfBoundsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseEntity.badRequest().body("Could not change this property, check if the user and or property is correct!"));
        }
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }
}
