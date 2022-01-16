package com.example.Coding_Challenge.Controllers;

import com.example.Coding_Challenge.Services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * The class DocumentController defines all API mapping for the Document table and file upload and downloads.
 */
@RestController
@RequestMapping(path = "api/v1/Document")
public class DocumentController {

    private final DocumentService documentService;

    /**
     * Constructor that initiates the DocumentService.
     *
     * @param documentService Instance of the DocumentService
     */
    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    /**
     * The function createDocument is a Post mapping that first parses needed parameters and after that calls the createDocument function from the DocumentService.
     *
     * @param user_name A Path variable that contains a user_name.
     * @param file      A form variable that contains the file that will be saved as a Document.
     * @param type      A form variable that contains A String that describes the file type.
     * @return A ResponseEntity that indicates if the Document was created correct or not.
     */
    @PostMapping("/{user_name}")
    public ResponseEntity createDocument(@PathVariable(value = "user_name") String user_name, MultipartFile file, String type) {
        try {
            return documentService.createDocument(user_name, file, type);
        } catch (ArrayIndexOutOfBoundsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseEntity.badRequest().body("Can not find file!!"));
        }
    }

    /**
     * The function getDocumentsForUser is a Get mapping that first parses needed variables and after that calls the getDocumentsForUser function from the DocumentService.
     *
     * @param user_name A Path variable that contains the user_name.
     * @return A ResponseEntity that either serves the documents for a certain user or a response that indicates the request failed.
     */
    @GetMapping("/{user_name}")
    public ResponseEntity getDocumentsForUser(@PathVariable(value = "user_name") String user_name) {
        return documentService.getDocumentsForUser(user_name);
    }

    /**
     * The function downloadDocument is a get mapping that first parses needed variables and after that downloads a certain document.
     *
     * @param user_name     A Path variable that contains a user_name.
     * @param document_name A Path variable that contains a document_name.
     * @return A ResponseEntity that either downloads a Document or sends a response that indicates the request failed.
     */
    @GetMapping("/{user_name}/{document_name}")
    public ResponseEntity<Resource> downloadDocument(@PathVariable(value = "user_name") String user_name, @PathVariable(value = "document_name") String document_name) {
        return documentService.downloadDocument(user_name, document_name);
    }

    /**
     * The function editDocumentInfo is a Put mapping that first parses needed variables and after that changes Document information for a certain Document.
     *
     * @param user_name     A Path variable that contains a user_name.
     * @param document_name A Path variable that contains a document_name.
     * @param type          A form variable that contains a String that describes the Document type.
     * @return A ResponseEntity that either edits a Documents info or sends a response that indicates the request failed.
     */
    @PutMapping("/{user_name}/{document_name}")
    public ResponseEntity editDocumentInfo(@PathVariable(value = "user_name") String user_name, @PathVariable(value = "document_name") String document_name, String type) {
        return documentService.updateDocument(user_name, document_name, type);
    }
}
