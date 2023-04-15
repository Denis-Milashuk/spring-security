package com.example.milashuk.prePostAuth.repository;

import com.example.milashuk.prePostAuth.domain.Document;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class DocumentRepository {

    private Map<String, Document> documents =
            Map.of("abc123", new Document("Dima"),
                    "qwe123", new Document("Dima"),
                    "asd555", new Document("Artem"));

    public Document findDocument(String code) {
        return documents.get(code);
    }

}
