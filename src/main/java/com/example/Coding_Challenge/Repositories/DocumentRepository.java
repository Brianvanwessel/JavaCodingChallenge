package com.example.Coding_Challenge.Repositories;

import com.example.Coding_Challenge.Objects.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The DocumentRepository interface handles all database actions for the Document table.
 */
@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {
    /**
     * The function findDocumentByUser_id collects all Documents for a certain user_id from the Document database table.
     *
     * @param user_id An Integer that indicates a user_id.
     * @return A List of Document Objects.
     */
    @Query("SELECT new Document(d.document_id,d.document_extension,d.document_name,d.document_type,d.user_id) FROM Document d WHERE d.user_id = ?1")
    List<Document> findDocumentByUser_id(Integer user_id);

    /**
     * The function findDocumentByUser_idAndName collects all Documents for a certain user_id and with a certain Document name from the Document database table.
     *
     * @param user_id       An Integer that indicates a user_id.
     * @param document_name A String containing the document name.
     * @return A List of Document Objects.
     */
    @Query("SELECT new Document(d.document_id,d.document_extension,d.document_name,d.document_type,d.user_id) FROM Document d WHERE d.user_id = ?1 AND d.document_name = ?2")
    List<Document> findDocumentByUser_idAndName(Integer user_id, String document_name);


}
