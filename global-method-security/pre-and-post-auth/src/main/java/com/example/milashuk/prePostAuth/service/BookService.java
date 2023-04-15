package com.example.milashuk.prePostAuth.service;

import com.example.milashuk.prePostAuth.domain.Employee;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookService {
    private final Map<String, Employee> records =
            Map.of(
                    "Dima", new Employee(
                            "Milashuk Dima",
                            List.of("Karamazov Brothers"),
                            List.of("accountant", "reader")),
                    "Artem", new Employee(
                            "Milashuk Artem",
                            List.of("Beautiful Paris"),
                            List.of("researcher")
                            )
            );

    @PostAuthorize("returnObject.roles.contains('reader')")
    public Employee getBookDetails(String name) {
        return records.get(name);
    }
}
